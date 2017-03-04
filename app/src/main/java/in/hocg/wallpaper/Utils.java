package in.hocg.wallpaper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hocgin on 02/03/2017.
 */

public class Utils {

	public static int time2color() {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		String colorInt = formatter.format(new Date());
		colorInt = colorInt.length() < 8 ? "0" + colorInt : colorInt;
		return Integer.parseInt(colorInt);
	}
}
