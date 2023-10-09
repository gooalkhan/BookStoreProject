package common;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class MyDB {
	protected JdbcDataSource dataSource;
	protected String sqlToUse;
	protected MyTable tableToUse;
	
	protected MyDB() {
		dataSource = new JdbcDataSource();
		dataSource.setUrl("jdbc:h2:tcp://localhost:9092/~/default");
//		dataSource.setUser("sa");
//		dataSource.setPassword("");
    }

	public JdbcDataSource getDataSource() {
		return this.dataSource;
	}
}
