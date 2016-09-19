package merchante.delegate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import merchante.util.PropertyUtil;

public class HtmlParserDelegate extends BaseDelegate {
	private static final String URL = "url2";
	private static final String OUTPUT_FILE = "outputFile";
	
	public void parseHtmlAndReportSizes() {
		StringBuilder sb = new StringBuilder();
		Writer writer = null;

		try {
			Path path = getOutputDirectoryPath();
			
			if (!directoryExist(path)) {
				createDirectory(path);
			}
				
			writer = new FileWriter(new File(getOutputFile(OUTPUT_FILE)));

		    Document doc = Jsoup.connect(PropertyUtil.getProperty(URL)).get();
		    Elements links = doc.getElementsByTag("a");
		    
		    for (Element link : links) {
		        System.out.println(link.attr("href") + " - " + link.text());
		    }
	        		
		    Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
			for (Element image : images) {
		    	System.out.println(image.attr("src"));
		    }
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	}}