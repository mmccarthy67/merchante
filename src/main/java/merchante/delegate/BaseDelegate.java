package merchante.delegate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import merchante.util.FileUtil;
import merchante.util.HttpUtil;
import merchante.util.SecurityUtil;

public abstract class BaseDelegate {
	private static final String OUTPUT_FILE = "outputFile";
	
	public HttpURLConnection getHttpURLConnection(URL url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setUseCaches(false);

		if (connection instanceof HttpsURLConnection) {
			HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
			httpsConnection.setHostnameVerifier(SecurityUtil.getHostnameVerifier());
			httpsConnection.setSSLSocketFactory(SecurityUtil.getSSLSocketFactory());
		}

		return connection;
	}

	public void processSingleUrlConnection(HttpURLConnection connection) throws IOException {
		StringBuilder sb = new StringBuilder();
		Writer writer = null;

		try {
			Path path = getOutputDirectoryPath();
			
			if (!directoryExist(path)) {
				createDirectory(path);
			}
			
			writer = new FileWriter(new File(getOutputFile(OUTPUT_FILE)));

			sb.append("URL").append(":").append(connection.getURL()).append(System.lineSeparator()).append(System.lineSeparator());
			sb.append("*** Headers ****").append(System.lineSeparator());

			Map<String, List<String>> headerFeilds = connection.getHeaderFields();
			headerFeilds.forEach((k,v) -> sb.append((k == null ? "" : k)+ ":" + v).append(System.lineSeparator()));

			String contentDisposition = connection.getHeaderField("Content-Disposition");
			String fileName = "";

			if (contentDisposition != null) {
				// extracts file name from header field
				int index = contentDisposition.indexOf("filename=");
				if (index > 0) {
					fileName = contentDisposition.substring(index + 10, contentDisposition.length() - 1);
				}
			} else {
				// extracts file name from URL
				fileName = connection.getURL().toString().substring(connection.getURL()
						.toString().lastIndexOf("/") + 1, connection.getURL().toString().length());
			}

			sb.append(System.lineSeparator()).append(System.lineSeparator());
			sb.append("File Name:").append(fileName).append(System.lineSeparator());

			long contentLength = HttpUtil.getContentLength(connection);

			sb.append("File Size:").append(contentLength).append(System.lineSeparator());
			writer.write(sb.toString());
		} catch (IOException e) {
			System.out.println("Exception writing file output [" + e.getMessage() + "]");
		} finally {
			writer.close();	
		}		
	}
	
	public Path getOutputDirectoryPath() {
		return FileUtil.getOutputFilePath();
	}
	
	public boolean directoryExist(Path path) {
		return FileUtil.directoryExist(path);
	}
	
	public void createDirectory(Path path) throws IOException {
		FileUtil.createDirectory(path);
	}
	
	public String getOutputFile(String key) {
		StringBuilder sb = new StringBuilder();
		sb.append(FileUtil.getOutputFilePath()).append(File.separator).append(FileUtil.getOutputFileName(key));
		return sb.toString();
	}
}