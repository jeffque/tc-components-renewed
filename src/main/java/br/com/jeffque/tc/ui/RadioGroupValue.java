package br.com.jeffque.tc.ui;

import java.util.ArrayList;
import java.util.List;

import totalcross.ui.RadioGroupController;

public class RadioGroupValue<V> {
	private RadioGroupController rgc;
	private List<RadioValue<V>> radios = new ArrayList<>();
	
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

	public boolean add(RadioValue<V> newMember) {
		rgc.add(newMember);
		return radios.add(newMember);
	}
	
	public boolean remove(RadioValue<V> oldMember) {
		rgc.remove(oldMember);
		return radios.remove(oldMember);
	}
	
	public int getSize() {
		return rgc.getSize();
	}
	
	public List<RadioValue<V>> getRadios() {
		return radios;
	}
	
}
