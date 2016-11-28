package br.com.jeffque.tc;

import java.util.ArrayList;
import java.util.List;

import br.com.jeffque.tc.multislider.MultiSliderTest;
import br.com.jeffque.tc.ui.BaseContainer;
import br.com.jeffque.tc.ui.BaseTCMain;
import br.com.jeffque.tc.ui.FirstContainer;
import totalcross.ui.Button;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;

public class BaseMenu extends FirstContainer {
	public List<BaseContainer> containers;
	public void povoaContainers(BaseTCMain<?> baseTest) {
		containers = new ArrayList<>();
		containers.add(new MultiSliderTest(baseTest));
	}

	public BaseMenu(BaseTCMain<?> baseTest) {
		super(baseTest);
		povoaContainers(baseTest);
	}
	
	@Override
	public void initUI() {
		super.initUI();
		
		for (BaseContainer containerTest: containers) {
			addButton(containerTest);
		}
	}

	private void addButton(BaseContainer container) {
		PressListener abrirContainer = new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent arg0) {
				present(container);
			}
		};
		Button btn = new Button(container.getClass().getName().replaceAll("[^.]*\\.", ""));
		btn.addPressListener(abrirContainer);
		add(btn, LEFT, AFTER, FILL, PREFERRED);
	}

}
