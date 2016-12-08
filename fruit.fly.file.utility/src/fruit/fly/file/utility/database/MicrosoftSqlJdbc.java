package fruit.fly.file.utility.database;

import java.sql.Connection;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import com.microsoft.sqlserver.jdbc.*;

public class MicrosoftSqlJdbc {
	
	private static Connection db = null;
	private Statement stUpd = null;

	public MicrosoftSqlJdbc() throws Exception {
		@SuppressWarnings("unused")
		Driver d = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		String connectionUrl = "jdbc:sqlserver://192.168.249.201:1433;databaseName=SysproCompany0;integratedSecurity=false;";
		db = DriverManager.getConnection(connectionUrl,"sa","Hornet123");
		stUpd = db.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
	}

	public void update(String q) throws Exception {
		stUpd.executeUpdate(q);
	}

	public void drop(String q) throws Exception {
		try {
			stUpd.executeUpdate(q);
		} catch (SQLException e) {
			if(!(e.getErrorCode()==(3701))) {
				
				System.out.println("(:" + e.getErrorCode());
				
				throw new Exception(e);
			}
		}
	}
	
	public void disconnect() throws Exception {
		if (stUpd != null)
			stUpd.close();
		if (db != null)
			db.close();
	}
	
	public Connection getConnection() {
		return db;
	}
	
	public ResultSet result(String q) throws Exception {
		return db.createStatement().executeQuery(q);
	}

}
