package br.com.jeffque.tc.ui;

import totalcross.ui.Radio;

public class RadioValue<V> extends Radio {
	private V value;

	public RadioValue(V value, String text, RadioGroupValue<V> radioGroup) {
		super(text, radioGroup != null? radioGroup.getRgc(): null);
		this.value = value;
	}

	public RadioValue(V value, String text) {
		this(value, text, null);
	}
	
	public V getValue() {
		return value;
	}

}
