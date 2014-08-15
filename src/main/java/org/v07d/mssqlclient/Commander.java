package org.v07d.mssqlclient;

import java.io.Console;
import java.sql.SQLException;

public class Commander {
	public Commander(Connector connector) {
		Console c = System.console();
		if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }
		String com = "";
		while(!com.equals("exit")) {
			com = c.readLine(": ");
			try {
				connector.execute(com);
			} catch (SQLException e) {
				System.out.printf("Invalid SQL: %s\n", e.getMessage());
			}
		}
	}
}
