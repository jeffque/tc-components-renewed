package br.com.jeffque.tc.util;

import totalcross.ui.Control;
import totalcross.ui.gfx.Color;

public final class StyleUtils {
	private StyleUtils() {
	}
	
	public static void setStyle(Control c) {
		c.setBackForeColors(Color.darker(Color.WHITE), Color.BLACK);
		c.transparentBackground = true;
	}
	
}
