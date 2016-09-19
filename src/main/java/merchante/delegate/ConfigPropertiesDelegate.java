package merchante.delegate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import merchante.util.HttpUtil;
import merchante.util.PropertyUtil;

public class ConfigPropertiesDelegate extends BaseDelegate {
	private static final String URL = "url";
	
	public void processUrlFromConfigProps() throws IOException {
		URL url = new URL(PropertyUtil.getProperty(URL));

		HttpURLConnection connection = getHttpURLConnection(url);
		
		if (HttpUtil.isValidConnection(connection)) {
			processSingleUrlConnection(connection);
		}
	}
}