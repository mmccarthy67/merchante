package merchante.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {	
	private static final String OUTPUT_DIRECTORY = "outputFileDir";
	
	public static Path getOutputFilePath() {
		return Paths.get(PropertyUtil.getProperty(OUTPUT_DIRECTORY)).toAbsolutePath().normalize();
	}
	
	public static String getOutputFileName(String key) {
		return PropertyUtil.getProperty(key);
	}
	
	public static boolean directoryExist(Path path) {
		return Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS);
	}
	
	public static void createDirectory(Path path) throws IOException {
		Files.createDirectories(path);	
	}
}