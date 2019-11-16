package edu.poly.Du_An_Tot_Ngiep.utils;

import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class AppUtils {

	
	public static Optional<Cookie> getCookie(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies == null) {
			return Optional.empty();
		}
		
		return Stream.of(cookies)
				.filter(cookie -> cookie.getName().equalsIgnoreCase(name))
				.findFirst();
	}
}
