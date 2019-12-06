package edu.poly.Du_An_Tot_Ngiep.utils;

import java.text.DecimalFormat;

public class DecimalFormatUtils {

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###.000");
	
	public static String convert(Number money) {
		return DECIMAL_FORMAT.format(money).replace(".", ",")+ " đ";
	}
	
}
