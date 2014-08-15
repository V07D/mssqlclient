package org.v07d.mssqlclient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

public class Connector {
	private static Connector instance = null;
	private Connection con;
	
	private Connector(Map<String,String> config) {
		 con = getConnection(config);
	}
	public static Connector getInstance(Map<String,String> config) {
		if(instance == null) {
			instance = new Connector(config);
		}
		return instance;
	}
	public String execute(String query) throws SQLException {
		 System.out.printf("Connected.\n");
		 String SQL = "SELECT * FROM Folder"; //Don't forget to remove it
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(SQL);
		 while (rs.next())  
            {  
               System.out.println(rs.getString(1) + " " + rs.getString(2));  
            }
		return null;
	}
	
	private Connection getConnection(Map<String,String> config) {
		FileInputStream fis;
        Properties property = new Properties();
        //databaseName=ZUP_Univrsal;useUnicode=true;characterEncoding=UTF-8;characterSetResults=UTF-8;
        
        try {
        	
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	String host = config.get("host");
        	String name = config.get("name");
        	String login = config.get("login");
        	String password = config.get("password");
        	Class.forName("com.mysql.jdbc.Driver");
        	String mysql = String.format("jdbc:mysql://%s/%s?user=%s&password=%s",host,name,login,password);
			String connectionUrl = String.format("jdbc:sqlserver://%s;databaseName=%s;%s%s%suser=%s;password=%s;", 
					config.get("host"),
					config.get("name"),
					config.get("useUnicode"),
					config.get("characterEncoding"),
					config.get("characterSetResults"),
					config.get("login"),
					config.get("password")
					);
			
			 con = DriverManager.getConnection(mysql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		return con;
	}
	
	private static void f(String arg0, Object... args) {
		System.out.printf(arg0,args);
	}
}
