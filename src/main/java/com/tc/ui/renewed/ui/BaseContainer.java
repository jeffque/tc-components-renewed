package com.tc.ui.renewed.ui;

import java.util.ArrayList;
import java.util.List;

import totalcross.res.Resources;
import totalcross.sys.Vm;
import totalcross.ui.Container;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public abstract class BaseContainer extends Container {
	private List<BaseContainer> containerStack;
	private BaseTCMain<? extends FirstContainer> baseMain;
	private HeaderBar headerBar;
	
	public String getTitle() {
		return "";
	}
	
	public HeaderBar getHeaderBar() {
		return headerBar;
	}
	
	@Override
	public void initUI() {
		super.initUI();
		
		setBackForeColors(Color.WHITE, Color.BLACK);
		
		headerBar = new HeaderBar(getTitle());
		headerBar.titleAlign = LEFT;
		headerBar.backgroundStyle = BACKGROUND_SOLID;
		headerBar.setBorderStyle(BORDER_NONE);
		headerBar.setBackForeColors(Color.interpolate(Color.BLACK, Color.BLUE), Color.WHITE);
		
		add(headerBar, LEFT, TOP, FILL, PREFERRED);
		
		if (getContainerStack().size() > 1) {
			addBackButton();
		}
	}
	
	public void addBackButton() {
		headerBar.addBarButton("Voltar", Resources.back, new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent arg0) {
				back();
			}
		});
	}

	private List<BaseContainer> getContainerStack() {
		if (containerStack == null) {
			containerStack = new ArrayList<>();
			containerStack.add(this);
		}
		
		return containerStack;
	}
	
	public BaseContainer() {
		super();
	}
	
	public BaseContainer(BaseTCMain<?> baseMain) {
		this();
		this.baseMain = baseMain;
	}

	public final void present(BaseContainer another) {
		initNextContainer(another);
		stackNextContainer(another, getContainerStack());
	}
	
	public final void swap(BaseContainer another) {
		initNextContainer(another);
		popLastContainer(getContainerStack());
		stackNextContainer(another, getContainerStack());
	}
	
	private void stackNextContainer(BaseContainer another, List<BaseContainer> containerStack) {
		containerStack.add(another);
		baseMain.show(another);
	}

	private void initNextContainer(BaseContainer another) {
		List<BaseContainer> l = getContainerStack();
		another.containerStack = l;
		another.baseMain = baseMain;
	}
	
	private void popLastContainer(List<BaseContainer> containerStack) {
		int n = containerStack.size();
		
		if (n > 0) {
			BaseContainer removed = containerStack.remove(n - 1);
			Vm.debug("Poped " + removed.getClass().getCanonicalName());
		}

	}

	public void back() {
		List<BaseContainer> l = getContainerStack();
		popLastContainer(l);
		int n = l.size();
		BaseContainer prevContainer = null;
		
		if (n > 0) {
			prevContainer = l.get(n - 1);
		}
		
		if (prevContainer == null) {
			prevContainer = baseMain.getFirstContainer();
		}
		
		prevContainer.onSwapBack();
		baseMain.show(prevContainer);
	}
	
	public BaseTCMain<?> getBaseMain() {
		return baseMain;
	}

	public void onSwapBack() {
	}
}
