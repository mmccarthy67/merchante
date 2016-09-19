package merchante.delegate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import merchante.model.UriRequestModel;
import merchante.util.HttpUtil;
import merchante.util.JsonUtil;

public class JsonArrayDelegate extends BaseDelegate {
	private static final String JSON_OUTPUT_FILE = "jsonOutputFile";
	
	public void processUrlsFromJsonArray() throws IOException {
		Writer writer = null;
		
		try {
			List<UriRequestModel> uriRequestList = new ArrayList<UriRequestModel>();
			JsonArray jsonArray = JsonUtil.getJsonArray();

			for (Object obj: jsonArray) {
				JsonObject jsonObj = (JsonObject) obj;
				JsonElement element = jsonObj.get("url");
				
				URL url = new URL(element.getAsString());

				HttpURLConnection connection = getHttpURLConnection(url);
				
				if (HttpUtil.isValidConnection(connection)) {
					UriRequestModel model = new UriRequestModel();
					model.setUri(url.toURI());
					uriRequestList.add(model);
					
					model.setSize(HttpUtil.getContentLength(connection));
				}
			}	

			Path path = getOutputDirectoryPath();
			
			if (!directoryExist(path)) {
				createDirectory(path);
			}
			
			writer = new FileWriter(new File(getOutputFile(JSON_OUTPUT_FILE)));
			writer.write(new Gson().toJson(uriRequestList));
			
		} catch (IOException e) {
			System.out.println("Exception writing file output [" + e.getMessage() + "]");
		} catch (URISyntaxException e) {
			System.out.println("Exception writing URI to model [" + e.getMessage() + "]");
		} finally {
			writer.close();	
		}
	}
}