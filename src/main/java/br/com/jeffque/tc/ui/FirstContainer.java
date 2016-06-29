package br.com.jeffque.tc.ui;

import totalcross.ui.MainWindow;

public abstract class FirstContainer extends BaseContainer {
	
	public FirstContainer() {
	}

	public FirstContainer(BaseTCMain<?> baseMain) {
		super(baseMain);
	}

	@Override
	public void back() {// TODO sair do programa apenas se não tiver o que voltar
		MainWindow.exit(0);
	}
}
