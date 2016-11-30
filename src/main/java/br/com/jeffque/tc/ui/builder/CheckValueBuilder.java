package br.com.jeffque.tc.ui.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.jeffque.magic.util.Presenter;
import br.com.jeffque.magic.util.ValueChangeHandler;
import br.com.jeffque.tc.ui.CheckGroupValue;
import br.com.jeffque.tc.ui.CheckValue;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;

public class CheckValueBuilder<V> {
	Presenter<V> presenter;
	List<V> values = new ArrayList<>();
	ValueChangeHandler<List<V>> handler;
	
	public CheckValueBuilder(Presenter<V> presenter) {
		this.presenter = presenter;
	}
	
	public void setValueChangeHandler(ValueChangeHandler<List<V>> handler) {
		this.handler = handler;
	}
	
	public void addValue(V value) {
		values.add(value);
	}
	
	public void addValues(Collection<V> value) {
		values.addAll(value);
	}
	
	public CheckGroupValue<V> build() {
		CheckGroupValue<V> cgroup = new CheckGroupValue<>();
		
		for (V v: values) {
			CheckValue<V> check = new CheckValue<>(v, presenter.stringfy(v));
			if (handler != null) {
				check.addPressListener(new PressListener() {
					
					@Override
					public void controlPressed(ControlEvent arg0) {
						handler.setChangedValue(cgroup.getSelectedValues());
					}
				});
			}
		}
		return cgroup;
	}
}
