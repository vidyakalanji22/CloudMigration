/**
 * 
 */
package com.cs.sparklesApp;

import java.io.IOException;
import java.util.Properties;


public abstract class PropertyLoader {

	private static Properties dbProperties = null;

	public static Properties getDBProperties() {
		if (dbProperties != null) {
			return dbProperties;
		}
		try {
			dbProperties = new Properties(); 
			dbProperties.load(PropertyLoader.class.getResourceAsStream("/db.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dbProperties;
	}
	

}
