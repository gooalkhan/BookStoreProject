package Book;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import common.MyDB;

public class BookDB extends MyDB {
	public static final String[] columns = new String[] {"isbn", "title", "author", "publisher", "pdate", "description", "discount", "pic"};
	private static BookDB db = new BookDB();

	private BookDB() {
	};

	public static BookDB getInstance() {
		return db;
	}

	public Vector<Object[]> searchBooksByISBN(String isbn) throws SQLException, InterruptedException {
		Vector<Object[]> result = searchBooks("isbn", isbn);
		return result;
	}

	public Vector<Object[]> searchBooksByISBN(String isbn, int limit) throws SQLException, InterruptedException {
		Vector<Object[]> result = searchBooks("isbn", isbn, limit);
		return result;
	}

	public Vector<Object[]> searchBooksByTitle(String title) throws SQLException {
		Vector<Object[]> result = searchBooks("title", title);
		return result;
	}

	public Vector<Object[]> searchBooksByTitle(String title, int limit) throws SQLException {
		Vector<Object[]> result = searchBooks("title", title, limit);
		return result;
	}

	public Vector<Object[]> searchBooksByAuthor(String author) throws SQLException {
		Vector<Object[]> result = searchBooks("author", author);
		return result;
	}

	public Vector<Object[]> searchBooksByAuthor(String author, int limit) throws SQLException {
		Vector<Object[]> result = searchBooks("author", author, limit);
		return result;
	}

	public Vector<Object[]> searchBooksByPublisher(String publisher) throws SQLException {
		Vector<Object[]> result = searchBooks("publisher", publisher);
		return result;
	}

	public Vector<Object[]> searchBooksByPublisher(String publisher, int limit) throws SQLException {
		Vector<Object[]> result = searchBooks("publisher", publisher, limit);
		return result;
	}

	public Vector<Object[]> searchBooks(String condition, String toSearch) throws SQLException {
		Vector<Object[]> result = searchBooks(condition, toSearch, Integer.MAX_VALUE);
		return result;
	}

	public Vector<Object[]> searchBooks(String condition, String toSearch, int limit) throws SQLException {
		Vector<Object[]> result = new Vector<>();

		String sql = "select isbn, title, author, publisher, pdate, description, discount, pic from booktbl where %s like '%%%s%%' limit %d;"
				.formatted(condition, toSearch, limit);

		System.out.println(sql);

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date pdate = rs.getDate("pdate");
				String description = rs.getString("description");
				int discount = rs.getInt("discount");
				String pic = rs.getString("pic");

				result.add(new Object[] { isbn, title, author, publisher, pdate, description, discount, pic });
			}
			return result;
		}
	}

	public Object[] searchBook(String isbnToSearch) throws SQLException {
		Object[] result;

		String sql = "select isbn, title, author, publisher, pdate, description, discount, pic from booktbl where isbn like '%s';"
				.formatted(isbnToSearch);

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date pdate = rs.getDate("pdate");
				String description = rs.getString("description");
				int discount = rs.getInt("discount");
				String pic = rs.getString("pic");

				result = new Object[] { isbn, title, author, publisher, pdate, description, discount, pic };
			} else {
				result = null;
			}
			return result;
		}
	}

	public void deleteBookByISBN(String sISBN) throws SQLException {
		String sql = "delete from booktbl where isbn='%s';".formatted(sISBN);

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
			if (stmt.executeUpdate(sql) != 0) {

			} else {
				
			}
		}
	}

	public void addBook(Object[] row) throws SQLException {
		String sql = "insert into booktbl values(?,?,?,?,?,?,?,?);";

		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, row[0].toString());
			pstmt.setString(2, row[1].toString());
			pstmt.setString(3, row[2].toString());
			pstmt.setString(4, row[3].toString());
			pstmt.setDate(5, (Date) row[4]);
			pstmt.setString(6, row[5].toString());
			pstmt.setInt(7, (int) row[6]);
			pstmt.setString(8, row[7].toString());

			pstmt.execute();
		}
	}

	public void updateBook(Object[] row) throws SQLException {
		String sql = "update booktbl set title=?, author=?, publisher=?, pdate=?, description=?, discount=?, pic=? where isbn='%s';"
				.formatted(row[0].toString());

		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, row[1].toString());
			pstmt.setString(2, row[2].toString());
			pstmt.setString(3, row[3].toString());
			pstmt.setDate(4, (Date) row[4]);
			pstmt.setString(5, row[5].toString());
			pstmt.setInt(6, (int) row[6]);
			pstmt.setString(7, row[7].toString());

			pstmt.execute();
		}

	}
}
	

