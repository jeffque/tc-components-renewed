package com.tc.ui.renewed.ui;

import com.tc.utils.magic.util.Valueable;

import totalcross.ui.Container;

public class ValueableContainer<V> extends Container implements Valueable<V> {

	V value;

	public ValueableContainer(V value) {
		this.value = value;
	}
	
	@Override
	public V getValue() {
		return value;
	}

}
