package merchante.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class HttpUtil {
	public static long getContentLength(HttpURLConnection connection) throws IOException {
		long contentLength = connection.getContentLengthLong();

		if (contentLength <= 0) {
			String transferEncoding = getTransferEncoding(connection);
			
			if (transferEncoding == null || transferEncoding.equalsIgnoreCase("chunked")) {
				contentLength = connection.getHeaderFieldInt("Content-Length", -1);
			} else {
				contentLength = -1;
			}
		}

		if (contentLength == -1) {
			contentLength = 0;
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((line = reader.readLine()) != null) {
				contentLength += line.length();
			}		
		}

		return contentLength;
	}

	private static String getTransferEncoding(HttpURLConnection connection) {
		return connection.getHeaderField("Transfer-Encoding");
	}
	
	public static boolean isValidConnection(HttpURLConnection connection) throws IOException {
		return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
	}
}