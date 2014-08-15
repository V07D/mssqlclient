package org.v07d.mssqlclient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigManager {
private static ConfigManager instance = null;
	
	private ConfigManager() {
		
	}
	public static ConfigManager getInstance() {
		if(instance == null) {
			instance = new ConfigManager();
		}
		return instance;
	}
	
	public Map<String,String> configure() {
		FileInputStream fis;
        Properties property = new Properties();
        
        Map<String,String> config = new HashMap<String,String>();
        
		try {
			fis = new FileInputStream("config/config.properties");
			property.load(fis);
			String host = property.getProperty("db.host");
			host = host == null || host.equals("") ? "test" : host;
			String login = property.getProperty("db.login");
			login = login == null || login.equals("") ? "test" : login;
			String password = property.getProperty("db.password");
			password = password == null || password.equals("") ? "test" : password;
			String name = property.getProperty("db.name");
			name = name == null || name.equals("") ? "test" : name;
			String useUnicode = property.getProperty("db.useUnicode");
			useUnicode = useUnicode != null && useUnicode.equals("true") ? "useUnicode=true;" : "";
			String characterEncoding = property.getProperty("db.characterEncoding");
			characterEncoding = (characterEncoding == null || characterEncoding.equals("")) ? "" : "characterEncoding="+characterEncoding+";";
			String characterSetResults = property.getProperty("db.characterSetResults");
			characterSetResults = (characterSetResults == null || characterSetResults.equals("")) ? "" : "characterSetResults="+characterSetResults+";";
			
			config.put("host", host);
			config.put("login", login);
			config.put("password", password);
			config.put("name", name);
			config.put("useUnicode", useUnicode);
			config.put("characterEncoding", characterEncoding);
			config.put("characterSetResults", characterSetResults);
			
		} catch (FileNotFoundException e) {
			System.out.printf("Config file not found. Exception message: %s\n", e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return config;
		
	}
}
