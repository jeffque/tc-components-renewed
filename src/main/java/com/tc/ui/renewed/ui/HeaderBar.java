package com.tc.ui.renewed.ui;

import java.util.HashMap;
import java.util.Map;

import totalcross.ui.Bar;
import totalcross.ui.event.PressListener;
import totalcross.ui.image.Image;

public class HeaderBar extends Bar {
	private Map<String, Bar.BarButton> mapButtons = new HashMap<>();
	
	private class InnerButton extends Bar.BarButton {

		public InnerButton(Image img) {
			this(null, img);
		}
		
		public InnerButton(String title, Image img) {
			super(title, img);
		}
		
	}
	
	/**
	 * Coloca o botão na barra. SEMPRE forneça o título
	 * @param id
	 * @param img
	 * @param pressListener
	 */
	public void addBarButton(String id, Image img, PressListener pressListener) {
		Bar.BarButton button = new InnerButton(img);
		
		if (pressListener != null) {
			button.addPressListener(pressListener);
		}
		
		addControl(button);
		
		mapButtons.put(id, button);
	}
	
	public HeaderBar() {
		super();
	}

	public HeaderBar(String title) {
		super(title);
	}

}
