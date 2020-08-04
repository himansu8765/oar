package com.tyss_practice.genericUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * 
 * @author Himanshu
 * 
 * This class is used to get data from property file
 *
 */
public class PropertyFileLib {

	/**
	 * This method is used to get data from property file based on key
	 * 
	 * @param key
	 * @return value
	 * @throws IOException
	 */
	public String getPropertyKeyValue(String key) throws Throwable {

		FileInputStream file = new FileInputStream("./src/main/resources/commonData.properties");
		Properties pObj = new Properties();
		pObj.load(file);
		String value = pObj.getProperty(key);

		return value;
	}
}
