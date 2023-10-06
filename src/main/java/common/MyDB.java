package common;

import com.mysql.cj.jdbc.MysqlDataSource;

public abstract class MyDB {
	protected MysqlDataSource dataSource;
	protected String sqlToUse;
	protected MyTable tableToUse;
	
	protected MyDB() {
		dataSource = new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/icidb");
		dataSource.setUser("root");
		dataSource.setPassword("1234");
	}

	public MysqlDataSource getDataSource() {
		return this.dataSource;
	}
}
