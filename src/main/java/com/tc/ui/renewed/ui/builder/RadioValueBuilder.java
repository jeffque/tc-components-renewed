package com.tc.ui.renewed.ui.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tc.ui.renewed.ui.RadioGroupValue;
import com.tc.ui.renewed.ui.RadioValue;
import com.tc.utils.magic.util.Presenter;
import com.tc.utils.magic.util.ValueChangeHandler;

import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;

public class RadioValueBuilder<V> {
	Presenter<V> presenter;
	List<V> values = new ArrayList<>();
	ValueChangeHandler<V> handler;
	
	public RadioValueBuilder(Presenter<V> presenter) {
		this.presenter = presenter;
	}
	
	public void setValueChangeHandler(ValueChangeHandler<V> handler) {
		this.handler = handler;
	}
	
	public void addValue(V value) {
		values.add(value);
	}
	
	public void addValues(Collection<V> value) {
		values.addAll(value);
	}
	
	public RadioGroupValue<V> build() {
		RadioGroupValue<V> rgroup = new RadioGroupValue<>();
		
		for (V v: values) {
			RadioValue<V> radio = new RadioValue<>(v, presenter.stringfy(v), rgroup);
			if (handler != null) {
				radio.addPressListener(new PressListener() {
					
					@Override
					public void controlPressed(ControlEvent arg0) {
						handler.setChangedValue(v);
					}
				});
			}
		}
		return rgroup;
	}
}
