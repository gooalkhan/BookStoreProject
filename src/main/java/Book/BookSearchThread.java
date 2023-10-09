package Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.mysql.cj.jdbc.MysqlDataSource;

import common.PoisonPill;
import org.h2.jdbcx.JdbcDataSource;

public class BookSearchThread implements Runnable {
	private JdbcDataSource dataSource;
	private String sql;
	private String sqlGoal;
	private BookTask task;
	private int limit;


	public BookSearchThread(BookTask task, String condition, String searchWord) {
		this(task, condition, searchWord, Integer.MAX_VALUE);
	}
	
	public BookSearchThread(BookTask task, String condition, String searchWord, int limit) {
		this.limit = limit;
		this.dataSource = BookDB.getInstance().getDataSource();
		String sqlWhere = "from booktbl where %s like '%%%s%%' limit %d;".formatted(condition, searchWord, limit);
		this.sql = "select " + String.join(", ", BookDB.columns) + " " + sqlWhere;
		this.sqlGoal = "select count(*) " + sqlWhere;
		this.task = task;
	}

	@Override
	public void run() {
		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {

            //System.out.println(sqlGoal);
			ResultSet rs = stmt.executeQuery(sqlGoal);
			
			while (rs.next()) {
				
				int goal = rs.getInt(1);
				
				task.setGoal(goal < limit ? goal: limit);
			}
			rs.close();
			
            //System.out.println(sql);
			rs = stmt.executeQuery(sql);
			
			LinkedBlockingQueue<Object[]> queue = task.getQueue();
			
			Object[] temp;
			while (rs.next()) {
				temp = new Object[BookDB.columns.length];
				for (int i = 1; i <= BookDB.columns.length; i++) {
					temp[i-1] = rs.getObject(i);
				}
				queue.offer(temp, 1, TimeUnit.SECONDS);
				//System.out.println("offering row %b".formatted(hasInserted));
				
			}
			//System.out.println("offering done, sending poison pill");
			queue.offer(new Object[] {new PoisonPill()});
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
