package merchante.util;

import java.io.InputStreamReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class JsonUtil {
	private static final JsonArray jsonArray;
	
	static {
		InputStreamReader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream("urls.json"));
		jsonArray = (JsonArray) new JsonParser().parse(reader).getAsJsonObject().get("urls");
	}
	
	public static JsonArray getJsonArray() {
		return jsonArray;
	}
} 