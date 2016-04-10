package com.scientificgames.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class StudentProperties {
	InputStream inputStream;
	private Properties prop;
	
	public StudentProperties() {
		try {
			loadPropValues();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadPropValues() throws IOException {
 
		try {
			prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
	}
	
	public String getProperty(String property){
		return prop.getProperty(property);
	}
	
}
