package sdeng;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String formatBySimpledate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String rs = sdf.format(date);
		return rs;

	}
}
