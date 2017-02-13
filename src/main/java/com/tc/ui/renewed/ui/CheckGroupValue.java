package com.tc.ui.renewed.ui;

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
	
	public boolean add(CheckValue<V> check) {
		return checks.add(check);
	}
	
	public boolean remove(CheckValue<V> oldMember) {
		return checks.remove(oldMember);
	}
	
	public List<CheckValue<V>> getChecks() {
		return checks;
	}
}
