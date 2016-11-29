package br.com.jeffque.tc;

import java.util.ArrayList;
import java.util.List;

import br.com.jeffque.tc.multislider.MultiSliderTest;
import br.com.jeffque.tc.ui.BaseContainer;
import br.com.jeffque.tc.ui.BaseTCMain;
import br.com.jeffque.tc.ui.FirstContainer;
import br.com.jeffque.tc.util.ContainerYielder;
import totalcross.ui.Button;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;

public class BaseMenu extends FirstContainer {
	public List<ContainerYielder> yielders;
	BaseTCMain<?> base;
	public void povoaContainers() {
		yielders = new ArrayList<>();
		yielders.add(new ContainerYielder() {
			@Override
			public BaseContainer yield(BaseTCMain<?> base) {
				return new MultiSliderTest(base);
			}
		});
	}

	public BaseMenu(BaseTCMain<?> baseTest) {
		super(baseTest);
		base = baseTest;
		povoaContainers();
	}
	
	@Override
	public void initUI() {
		super.initUI();
		
		for (ContainerYielder yielderContainerTest: yielders) {
			addButton(yielderContainerTest);
		}
	}

	private void addButton(ContainerYielder yielderContainer) {
		PressListener abrirContainer = new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent arg0) {
				present(yielderContainer.yield(base));
			}
		};
		Button btn = new Button(yielderContainer.yield(base).getClass().getName().replaceAll("[^.]*\\.", ""));
		btn.addPressListener(abrirContainer);
		add(btn, LEFT, AFTER, FILL, PREFERRED);
	}

}
