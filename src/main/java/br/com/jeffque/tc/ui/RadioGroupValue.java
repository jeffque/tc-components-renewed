package br.com.jeffque.tc.ui;

import totalcross.ui.RadioGroupController;

public class RadioGroupValue<V> {
	private RadioGroupController rgc;
	public RadioGroupValue() {
		this(new RadioGroupController());
	}
	
	public RadioGroupValue(RadioGroupController rgc) {
		this.rgc = rgc;
	}
	
	public RadioGroupController getRgc() {
		return rgc;
	}
	
	public boolean hasSelectedValue() {
		return rgc.getSelectedItem() != null;
	}

	public V getSelectedValue() {
		if (getSelectedItem() != null) {
			return getSelectedItem().getValue();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public RadioValue<V> getSelectedItem() {
		return (RadioValue<V>) rgc.getSelectedItem();
	}

	public void add(RadioValue<V> newMember) {
		rgc.add(newMember);
	}
	
}
