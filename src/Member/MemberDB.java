package Member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import com.mysql.cj.jdbc.MysqlDataSource;

import Book.BookDB;
import common.MyDB;

public class MemberDB extends MyDB {
	private static MemberDB db = new MemberDB();
	
	private MemberDB() {};
	
	public static MemberDB getInstance() {
		return db;
	} 

	public Object[] searchMemberByID(String searchID) throws SQLException {
		Object[] result = new Object[11];

		String sql = "select id, pw, name, mobile, email, address, birth, slt, joindate, grad, pic from bookmembertbl where id='%s';".formatted(searchID);

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String address = rs.getString("address");
				Date birth = rs.getDate("birth");
				boolean slt = rs.getBoolean("slt");
				Date joindate = rs.getDate("joindate");
				String grad = rs.getString("grad");
				String pic = rs.getString("pic");
				
				result[0] = id;
				result[1] = pw;
				result[2] = name;
				result[3] = mobile;
				result[4] = email;
				result[5] = address;
				result[6] = birth;
				result[7] = slt;
				result[8] = joindate;
				result[9] = grad;
				result[10] = pic;
			}
			return result;
		}
		
	}
	
	public Vector<Object[]> searchMembersByID(String id) throws SQLException {
		Vector<Object[]> result = searchMembers("id", id, Integer.MAX_VALUE);
		return result;
		
	}
	public Vector<Object[]> searchMembersByID(String id, int limit) throws SQLException {
		Vector<Object[]> result = searchMembers("id", id, limit);
		return result;
		
	}
	public Vector<Object[]> searchMembersByName(String name) throws SQLException {
		Vector<Object[]> result = searchMembers("name", name, Integer.MAX_VALUE);
		return result;
		
	}
	public Vector<Object[]> searchMembersByName(String name, int limit) throws SQLException {
		Vector<Object[]> result = searchMembers("name", name, limit);
		return result;
		
	}
	public Vector<Object[]> searchMembersByMobile(String mobile) throws SQLException {
		Vector<Object[]> result = searchMembers("mobile", mobile, Integer.MAX_VALUE);
		return result;
		
	}
	public Vector<Object[]> searchMembersByMobile(String mobile, int limit) throws SQLException {
		Vector<Object[]> result = searchMembers("mobile", mobile, limit);
		return result;
		
	}
	public Vector<Object[]> searchMembersByAddress(String address) throws SQLException {
		Vector<Object[]> result = searchMembers("address", address, Integer.MAX_VALUE);
		return result;
		
	}
	public Vector<Object[]> searchMembersByAddress(String address, int limit) throws SQLException {
		Vector<Object[]> result = searchMembers("address", address, limit);
		return result;
		
	}
	
	public Vector<Object[]> searchMembers(String condition, String toSearch) throws SQLException {
		return searchMembers(condition, toSearch, Integer.MAX_VALUE);
	}
	
	public Vector<Object[]> searchMembers(String condition, String toSearch, int limit) throws SQLException {
		Vector<Object[]> result = new Vector<>();

		String sql = "select id, pw, name, mobile, email, address, birth, slt, joindate, grad, pic from bookmembertbl where %s like '%%%s%%' limit %d;".formatted(condition, toSearch, limit);
		System.out.println(sql);

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String address = rs.getString("address");
				Date birth = rs.getDate("birth");
				boolean slt = rs.getBoolean("slt");
				Date joindate = rs.getDate("joindate");
				String grad = rs.getString("grad");
				String pic = rs.getString("pic");
				
				result.add(new Object[] {id, pw, name, mobile, email, address, birth, slt, joindate, grad, pic});
			}
			return result;
		}
		
	}
	
	public Vector<Object[]> searchMembersByMultipleConditions(HashMap<String, String> map, int limit) throws SQLException {
		Vector<Object[]> result = new Vector<>();

		String sqlColumn = "select id, pw, name, mobile, email, address, birth, slt, joindate, grad, pic from bookmembertbl ";
		String sqlCondition = "where ";
		
		for (Entry<String, String> set : map.entrySet()) {
			if (sqlCondition.equals("where "))
			sqlCondition = sqlCondition + set.getKey() + " like '%" + set.getValue() + "%'";
			else sqlCondition = sqlCondition  + " and " + set.getKey() + " like '%" + set.getValue() + "%'";
		}
		
		String sqlLimit = " limit %d;".formatted(limit);
		String sql = sqlColumn + sqlCondition + sqlLimit;
		
		System.out.println(sql);

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String address = rs.getString("address");
				Date birth = rs.getDate("birth");
				boolean slt = rs.getBoolean("slt");
				Date joindate = rs.getDate("joindate");
				String grad = rs.getString("grad");
				String pic = rs.getString("pic");
				
				result.add(new Object[] {id, pw, name, mobile, email, address, birth, slt, joindate, grad, pic});
			}
			return result;
		}
		
	}
	
	public void addMember(Object[] row) throws SQLException {
		String sql = "insert into bookmembertbl values (?,?,?,?,?,?,?,?,?,?,?);";
		
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, (String)row[0]);
			pstmt.setString(2, (String)row[1]);
			pstmt.setString(3, (String)row[2]);
			pstmt.setString(4, (String)row[3]);
			pstmt.setString(5, (String)row[4]);
			pstmt.setString(6, (String)row[5]);
			pstmt.setDate(7, (Date) row[6]);
			pstmt.setBoolean(8, (boolean) row[7]);
			pstmt.setDate(9, (Date)row[8]);
			pstmt.setString(10, (String)row[9]);
			pstmt.setString(11, (String)row[10]);
			
			pstmt.execute();
		}
	}
}
