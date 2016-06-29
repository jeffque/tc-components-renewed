package br.com.jeffque.tc.ui;

import java.util.ArrayList;
import java.util.List;

public class CheckGroupValue<V> {
	private List<CheckValue<V>> checks = new ArrayList<>();
	
	public List<V> getSelectedValues() {
		List<V> selecteds = new ArrayList<>();
		
		for (CheckValue<V> check: checks) {
			if (check.isChecked()) {
				selecteds.add(check.getValue());
			}
		}
		
		return selecteds;
	}
	
	public void addCheck(CheckValue<V> check) {
		checks.add(check);
	}
}
