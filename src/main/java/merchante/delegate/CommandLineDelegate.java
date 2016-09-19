package merchante.delegate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import merchante.util.HttpUtil;

public class CommandLineDelegate extends BaseDelegate {
	public void processCommandLineUrl(String spec) throws IOException {
		URL url = new URL(spec);

		HttpURLConnection connection = getHttpURLConnection(url);

		if (HttpUtil.isValidConnection(connection)) {
			processSingleUrlConnection(connection);
		}
	}
}