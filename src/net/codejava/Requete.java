package net.codejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Requete {
	private String path = "jdbc:mysql://localhost:3306/ataqwa";
	private String user = "root";
	private String mdp = "";
	private ResultSet resultSet;
	private Statement statement;
	private Connection connection = null;
	
	public Requete (String query) {
		 try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         connection = DriverManager.getConnection(
	             path,user, mdp);
	         statement = connection.createStatement();
	         this.resultSet = statement.executeQuery(query);
		 } catch (Exception exception) {
	         System.out.println(exception);
	     	}
	}
	
	public void close() {
		try {
			this.resultSet.close();
			this.statement.close();
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getResultSet() {
		return resultSet;
	}
	
	
}
