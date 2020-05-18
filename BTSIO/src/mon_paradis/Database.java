package mon_paradis;

import java.sql.*;

public class Database {
	Connection con;
	
	public Database() {
		
	}

	public Connection db_connect() throws SQLException, ClassNotFoundException {
		
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/monparadissa2?autoReconnect=true&useSSL=false","root","");
		
		return con;
	}
	
	
	public void close_connect() throws SQLException {
		con.close();
	}

	
}
