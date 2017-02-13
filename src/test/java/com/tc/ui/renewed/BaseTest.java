package com.tc.ui.renewed;

import com.tc.ui.renewed.ui.BaseTCMain;

public class BaseTest extends BaseTCMain<BaseMenu> {
	public BaseTest() {
		super("Testes UI", NO_BORDER);
	}

	@Override
	public BaseMenu getFirstContainer() {
		return new BaseMenu(this);
	}

}
