package com.tc.ui.renewed;

import java.util.ArrayList;
import java.util.List;

import br.com.jeffque.tc.itemcontainer.ItemContainerTest;
import com.tc.ui.renewed.multislider.MultiSliderTest;
import com.tc.ui.renewed.ui.BaseContainer;
import com.tc.ui.renewed.ui.BaseTCMain;
import com.tc.ui.renewed.ui.FirstContainer;
import com.tc.ui.renewed.util.ContainerYielder;

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
		yielders.add(new ContainerYielder() {
			
			@Override
			public BaseContainer yield(BaseTCMain<?> base) {
				return new ItemContainerTest(base);
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
