package merchante;

import java.io.IOException;
import java.net.MalformedURLException;

import merchante.delegate.CommandLineDelegate;
import merchante.delegate.ConfigPropertiesDelegate;
import merchante.delegate.HtmlParserDelegate;
import merchante.delegate.JsonArrayDelegate;

public class UrlProcessor {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println(getMenuOptions());
		}
		else {
			try {
				if (args[0].length() == 1) {
					switch (Integer.parseInt(args[0])) {
					case 1:
						processUrlFromConfigProps();
						break;
					case 2:
						processUrlsFromJsonArray();
						break;
					case 3:
						parseHtmlAndReportSizes();
						break;
					default:
						System.out.println(new StringBuilder().append("Incorrect option, please try again!").append(System.lineSeparator())
								.append(System.lineSeparator()).append(getMenuOptions()));		
					}
				}
				else {
					processCommandLineUrl(args[0]);
				}
			} catch (MalformedURLException e) {
				System.out.println(new StringBuilder().append(e.getMessage()).append(System.lineSeparator())
						.append(System.lineSeparator()).append(getMenuOptions()));
			} catch (IOException e) {
				System.out.println(new StringBuilder().append(e.getMessage()).append(System.lineSeparator())
						.append(System.lineSeparator()).append(getMenuOptions()));	
			} catch (Exception e) {
				System.out.println(new StringBuilder().append(e.getMessage()).append(System.lineSeparator())
						.append(System.lineSeparator()).append(getMenuOptions()));	
			}
		}
	}

	private static void processUrlFromConfigProps() throws IOException {
		new ConfigPropertiesDelegate().processUrlFromConfigProps();
	}

	private static void processUrlsFromJsonArray() throws IOException {
		new JsonArrayDelegate().processUrlsFromJsonArray();
	}

	private static void parseHtmlAndReportSizes() {
		new HtmlParserDelegate().parseHtmlAndReportSizes();
	}

	private static void processCommandLineUrl(String url) throws IOException {
		new CommandLineDelegate().processCommandLineUrl(url);
	}

	private static String getMenuOptions() {
		return new StringBuilder().append("Please enter a URL or one of the following options:").append(System.lineSeparator())
				.append("1 = Execute single URL from config file.").append(System.lineSeparator())
				.append("2 = Execute JSON Array of URL's.").append(System.lineSeparator())
				.append("3 = Parse HTML and report all sizes.").toString();
	}
}