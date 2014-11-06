package org.v07d.mssqlclient;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;

import jline.console.ConsoleReader;

public class Commander {
	public Commander(Connector connector) {
		ConsoleReader reader = null;
		try {
			reader = new ConsoleReader();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.err.println("No console.");
			System.exit(1);
		}
		reader.setPrompt(">: ");
		/*Console c = System.console();
		if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }*/
		String com = "";
		while(!com.equals("exit")) {
			try {
				com = reader.readLine(": ");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				connector.execute(com);
			} catch (SQLException e) {
				System.out.printf("Invalid SQL: %s\n", e.getMessage());
			}
		}
	}
}
