package org.v07d.mssqlclient;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public interface Viewer {
	public void view(ResultSet rs, ResultSetMetaData rsmd) throws SQLException;
}
