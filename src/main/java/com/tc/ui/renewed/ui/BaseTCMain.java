package com.tc.ui.renewed.ui;

import com.tc.ui.renewed.util.StyleUtils;

import totalcross.sys.Vm;
import totalcross.ui.MainWindow;

public abstract class BaseTCMain<F extends FirstContainer> extends MainWindow {

	public BaseTCMain() {
		super();
	}

	public BaseTCMain(String title, byte style) {
		super(title, style);
	}
	
	@Override
	public void initUI() {
		StyleUtils.setStyle(this);
		show(getFirstContainer());
	}

	public void show(BaseContainer container) {
		Vm.debug("Showing " + container.getClass().getCanonicalName());
		swap(container);
	}

	public abstract F getFirstContainer();
}
