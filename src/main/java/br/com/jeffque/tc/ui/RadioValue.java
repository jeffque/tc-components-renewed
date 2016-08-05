package br.com.jeffque.tc.ui;

import br.com.jeffque.magic.util.Valueable;
import totalcross.ui.Radio;

public class RadioValue<V> extends Radio implements Valueable<V> {
	private V value;

	public RadioValue(V value, String text, RadioGroupValue<V> radioGroup) {
		super(text, radioGroup != null? radioGroup.getRgc(): null);
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
