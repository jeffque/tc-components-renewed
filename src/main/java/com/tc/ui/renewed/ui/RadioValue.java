package com.tc.ui.renewed.ui;

import com.tc.utils.magic.util.Valueable;

import totalcross.ui.Radio;

public class RadioValue<V> extends Radio implements Valueable<V> {
	private V value;

	public RadioValue(V value, String text, RadioGroupValue<V> radioGroup) {
		super(text);
		if (radioGroup != null) {
			radioGroup.add(this);
		}
		this.value = value;
	}

	public RadioValue(V value, String text) {
		this(value, text, null);
	}
	
	@Override
	public V getValue() {
		return value;
	}

}
