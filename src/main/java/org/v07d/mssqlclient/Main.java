package org.v07d.mssqlclient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {
		FileInputStream fis;
        Properties property = new Properties();
        //databaseName=ZUP_Univrsal;useUnicode=true;characterEncoding=UTF-8;characterSetResults=UTF-8;
        
        try {
			fis = new FileInputStream("config/config.properties");
			property.load(fis);
			String host = property.getProperty("db.host");
			String login = property.getProperty("db.login");
			String password = property.getProperty("db.password");
			String name = property.getProperty("db.name");
			String useUnicode = property.getProperty("db.useUnicode");
			useUnicode = useUnicode != null && useUnicode.equals("true") ? "useUnicode=true;" : "";
			String characterEncoding = property.getProperty("db.characterEncoding");
			characterEncoding = (characterEncoding == null || characterEncoding.equals("")) ? "characterEncoding=UTF-8;" : "characterEncoding="+characterEncoding+";";
			String characterSetResults = property.getProperty("db.characterSetResults");
			characterSetResults = (characterSetResults == null || characterSetResults.equals("")) ? "characterSetResults=UTF-8;" : "characterSetResults="+characterSetResults+";";
			
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = String.format("jdbc:sqlserver://%s;databaseName=%s;%s%s%suser=%s;password=%s;", 
					host,
					name,
					useUnicode,
					characterEncoding,
					characterSetResults,
					login,
					password);
			
			 Connection con = DriverManager.getConnection(connectionUrl);
			 System.out.printf("Connected.\n");
			 String SQL = "SELECT * FROM EmployeeOTRS WHERE Surname='�����������'"; //Don't forget to remove it
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(SQL);
			 while (rs.next())  
	            {  
	               System.out.println(rs.getString(1) + " " + rs.getString(2));  
	            }
		} catch (FileNotFoundException e1) {
			f("Error while reading config.properties: %s\n",e1.getStackTrace());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	private static void f(String arg0, Object... args) {
		System.out.printf(arg0,args);
	}

}
