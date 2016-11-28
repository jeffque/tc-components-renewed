package br.com.jeffque.tc;

import br.com.jeffque.tc.ui.BaseTCMain;

public class BaseTest extends BaseTCMain<BaseMenu> {
	public BaseTest() {
		super("Testes UI", NO_BORDER);
	}

	@Override
	public BaseMenu getFirstContainer() {
		return new BaseMenu(this);
	}

}
