package merchante.util;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
	private static Properties properties = new Properties();
	
	static {
		try {
			properties.load(ClassLoader.getSystemResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}