package br.com.jeffque.tc.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.jeffque.tc.util.ContentViewHandler;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.Flick;
import totalcross.ui.Scrollable;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.PenEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Rect;

public class ItemContainer<T> extends Container implements Scrollable {
	private List<T> items;
	private List<ValueableContainer<? extends T>> containers;
	private ContentViewHandler<T> handler;
	private ValueableContainer<? extends T> selectedContainer;

	public ItemContainer(ContentViewHandler<T> handler) {
		this(handler, null);
	}

	public ItemContainer(ContentViewHandler<T> handler, Collection<T> objects) {
		super();
		
		this.handler = handler;
		
		containers = new ArrayList<>();
		items = new ArrayList<>();
		if (objects != null) {
			items.addAll(objects);
		}
	}
	
	@Override
	public void initUI() {
		super.initUI();
		
		addObjectsUI(items);
	}

	private void addObjectsUI(List<T> items) {
		for (T item: items) {
			addObjectUI(item);
		}
	}

	private void addObjectUI(T item) {
		ValueableContainer<? extends T> itemContainer = handler.createView(item);
		containers.add(itemContainer);
		
		add(itemContainer, LEFT, AFTER, FILL, PREFERRED);
		
		itemContainer.addPressListener(new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent arg0) {
				setSelectedContainer(itemContainer);
				Vm.debug("Elemento selecionado: " + getSelectedItem());
			}
		});
	}
	
	public boolean setSelectedItem(T item) {
		for (ValueableContainer<? extends T> container: containers) {
			if (container.getValue().equals(item)) {
				setSelectedContainer(container);
				return true;
			}
		}
		setSelectedContainer(null);
		return false;
	}
	
	public T getSelectedItem() {
		return selectedContainer != null? selectedContainer.getValue(): null;
	}
	
	public void addObject(T item) {
		items.add(item);
		addObjectUI(item);
	}


	@Override
	public boolean canScrollContent(int arg0, Object arg1) {
		return false;
	}

	@Override
	public void flickEnded(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean flickStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Flick getFlick() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScrollPosition(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean scrollContent(int arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean wasScrolled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	int ts;
	
	@Override
	public void onEvent(Event event) {
		switch (event.type) {
		case PenEvent.PEN_DOWN:
			ts = event.timeStamp;
			repaintNow();
			break;
		case PenEvent.PEN_UP:
			{
				PenEvent pe = (PenEvent) event;
				if (validAsPress(pe)) {
					postEventForTarget(pe);
				}
			}
			break;
		}
	}

	private void postEventForTarget(PenEvent pe) {
		postEvent(getPressedEvent(getTarget(pe)));
	}

	private Control getTarget(PenEvent pe) {
		Control target = this;
		
		for (ValueableContainer<? extends T> container: containers) {
			Rect rect = container.getAbsoluteRect();
			if (rect.contains(pe.absoluteX, pe.absoluteY)) {
				target = container;
				break;
			}
		}
		
		return target;
	}

	private boolean validAsPress(PenEvent pe) {
		return pe.timeStamp - ts < 500 && ((!Settings.fingerTouch || !hadParentScrolled()) && isInsideOrNear(pe.x, pe.y));
	}
	
	private void setSelectedContainer(ValueableContainer<? extends T> itemContainer) {
		selectedContainer = itemContainer;
	}
}
