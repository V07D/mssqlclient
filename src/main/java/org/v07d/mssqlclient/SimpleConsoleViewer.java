package org.v07d.mssqlclient;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SimpleConsoleViewer implements Viewer{
	public void view(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
		int colNum = rsmd.getColumnCount();
		System.out.printf("Num of colums: %d\n", colNum);
		StringBuffer sb = new StringBuffer();
		
		for (int i = 1; i <= colNum; i++) {
			sb.append(rsmd.getColumnLabel(i));
			sb.append("\t");
		}
		sb.append("\n");
		
		for(int i=1;i<=colNum;i++) {
			String colLabel = rsmd.getColumnLabel(i);
			sb.append(colLabel.replaceAll(".", "-")).append("\t");
		}
		sb.append("\n");
		
		while (rs.next()) {
			for (int i = 1; i <= colNum; i++) {
				sb.append(rs.getString(i));
				sb.append("\t");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
