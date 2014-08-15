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
	
	private Connector() {
		
	}
	public static Connector getInstance() {
		if(instance == null) {
			instance = new Connector();
		}
		return instance;
	}
	public String execute(Map<String,String> config) {
		FileInputStream fis;
        Properties property = new Properties();
        //databaseName=ZUP_Univrsal;useUnicode=true;characterEncoding=UTF-8;characterSetResults=UTF-8;
        
        try {
        	
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = String.format("jdbc:sqlserver://%s;databaseName=%s;%s%s%suser=%s;password=%s;", 
					config.get("host"),
					config.get("name"),
					config.get("useUnicode"),
					config.get("characterEncoding"),
					config.get("characterSetResults"),
					config.get("login"),
					config.get("password")
					);
			
			 Connection con = DriverManager.getConnection(connectionUrl);
			 System.out.printf("Connected.\n");
			 String SQL = "SELECT * FROM EmployeeOTRS WHERE Surname='Снегирькова'"; //Don't forget to remove it
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(SQL);
			 while (rs.next())  
	            {  
	               System.out.println(rs.getString(1) + " " + rs.getString(2));  
	            }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
        
	}
	
	private static void f(String arg0, Object... args) {
		System.out.printf(arg0,args);
	}
}
