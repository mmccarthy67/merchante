package merchante.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	public static String getCurrentTimestampAsString() {
		return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", Locale.US).format(new Date());
	}
}