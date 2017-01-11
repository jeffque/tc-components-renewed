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
import totalcross.ui.event.DragEvent;
import totalcross.ui.event.PenEvent;
import totalcross.ui.event.PenListener;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;
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
		onEventFirst = false;
		callListenersOnAllTargets = true;
	}
	
	@Override
	public void initUI() {
		super.initUI();
		
		addObjectsUI(items);
		
		addPenListener(new PenListener() {
			Control penTarget;
			
			private boolean validAsPress(PenEvent pe) {
				return ((!Settings.fingerTouch || !hadParentScrolled()) && penTarget.isInsideOrNear(pe.x, pe.y));
			}
			
			@Override
			public void penDown(PenEvent arg0) {
				penTarget = (Control) arg0.target;
				if (penTarget == ItemContainer.this) {
					setSelectedContainer(null);
				}
			}
			
			@Override
			public void penUp(PenEvent arg0) {
				if (validAsPress(arg0)) {
					postEventForTarget(arg0);
				}
			}
			
			@Override
			public void penDragStart(DragEvent arg0) {
			}
			
			@Override
			public void penDragEnd(DragEvent arg0) {
			}
			
			@Override
			public void penDrag(DragEvent arg0) {
			}
		});
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

	private void setSelectedContainer(ValueableContainer<? extends T> newSelectedContainer) {
		if (selectedContainer == newSelectedContainer) {
			return;
		}
		if (selectedContainer != null) {
			Vm.debug("old back color old container (" + selectedContainer.getValue() + ")" + Integer.toHexString(selectedContainer.getBackColor()));
			selectedContainer.setBackColor(getBackColor() - 1);
			selectedContainer.repaintNow();
			Vm.debug("new back color old container (" + selectedContainer.getValue() + ")" + Integer.toHexString(selectedContainer.getBackColor()));
		}
		
		if (newSelectedContainer != null) {
			Vm.debug("old back color new container (" + newSelectedContainer.getValue() + ")" + Integer.toHexString(newSelectedContainer.getBackColor()));
			newSelectedContainer.setBackColor(Color.darker(getBackColor()));
			newSelectedContainer.repaintNow();
			Vm.debug("new back color new container (" + newSelectedContainer.getValue() + ")" + Integer.toHexString(newSelectedContainer.getBackColor()));
		}
		
		selectedContainer = newSelectedContainer;
	}
}
