package br.com.jeffque.tc.ui;

import br.com.jeffque.magic.util.Valueable;
import totalcross.ui.Check;

public class CheckValue<V> extends Check implements Valueable<V> {
	private V value;

	public CheckValue(V value, String text) {
		super(text);
		this.value = value;
	}
	
	@Override
	public V getValue() {
		return value;
	}

}
