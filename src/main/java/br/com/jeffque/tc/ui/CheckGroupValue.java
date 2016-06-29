package br.com.jeffque.tc.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CheckGroupValue<V> {
	private List<CheckValue<V>> checks = new ArrayList<>();
	
	public CheckGroupValue() {
		this(new ArrayList<>());
	}
	
	public CheckGroupValue(Collection<CheckValue<V>> checkCollection) {
		for (CheckValue<V> check: checkCollection) {
			add(check);
		}
	}

	public List<V> getSelectedValues() {
		List<V> selecteds = new ArrayList<>();
		
		for (CheckValue<V> check: checks) {
			if (check.isChecked()) {
				selecteds.add(check.getValue());
			}
		}
		
		return selecteds;
	}
	
	public void add(CheckValue<V> check) {
		checks.add(check);
	}
	
	public void remove(CheckValue<V> oldMember) {
		checks.remove(oldMember);
	}
}
