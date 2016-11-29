package br.com.jeffque.tc.ui;

import br.com.jeffque.tc.util.ReadWriteAccessor;
import totalcross.sys.Vm;
import totalcross.ui.Slider;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.DragEvent;
import totalcross.ui.event.PenEvent;
import totalcross.ui.event.PenListener;
import totalcross.ui.event.PressListener;
import totalcross.util.BigDecimal;

public class SliderValue<V> {
	Slider slider;
	ReadWriteAccessor<V, BigDecimal> accessor;
	V value;
	BigDecimal max;
	
	ProportionalMultiSlider<V> proportionalMultiSlider;
	int id;
	
	public SliderValue(V value, ReadWriteAccessor<V, BigDecimal> accessor, BigDecimal max) {
		this.value = value;
		this.accessor = accessor;
		this.max = max;
		BigDecimal val = accessor.getAttr(value);
		
		slider = new Slider();
		slider.setValues(0, 1, 0, 1001);
		
		setNextValueAttr(val);
		
		slider.setLiveScrolling(true);
		slider.addPressListener(new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent arg0) {
				Vm.debug("slideando: " + slider.getValue());
				if (proportionalMultiSlider != null) {
					proportionalMultiSlider.recalculo();
				}
			}
		});
		
		slider.addPenListener(new PenListener() {
			
			@Override
			public void penUp(PenEvent arg0) {
				Vm.debug("Terminou a pena: " + slider.getValue());
				if (proportionalMultiSlider != null) {
					proportionalMultiSlider.consolida();
				}
			}
			
			@Override
			public void penDragStart(DragEvent arg0) {
			}
			
			@Override
			public void penDragEnd(DragEvent arg0) {
				Vm.debug("Terminou o drag?");
			}
			
			@Override
			public void penDrag(DragEvent arg0) {
			}
			
			@Override
			public void penDown(PenEvent arg0) {
				Vm.debug("Começou a pena: " + slider.getValue());
				if (proportionalMultiSlider != null) {
					proportionalMultiSlider.penDown(SliderValue.this);
				}
			}
		});
	}
	
	public Slider getSlider() {
		return slider;
	}

	public void setProportionalMultiSlider(ProportionalMultiSlider<V> proportionalMultiSlider) {
		this.proportionalMultiSlider = proportionalMultiSlider;
	}
	
	public BigDecimal getValueAttr() {
		return accessor.getAttr(value);
	}

	public BigDecimal getNextValueAttr() {
		BigDecimal proportion = new BigDecimal(slider.getValue()).movePointLeft(3);
		return proportion.multiply(max);
	}

	public void setNextValueAttr(BigDecimal newNextValue) {
		BigDecimal proportion = newNextValue.divide(max, 30, BigDecimal.ROUND_HALF_EVEN);
		// There will be precision until a proportion of 0.001, or 0.1% of the original
		proportion = proportion.movePointRight(3).setScale(0, BigDecimal.ROUND_HALF_EVEN);
		int intProportion = proportion.intValue();
		slider.setValue(intProportion);
		
	}

	public void consolidaValueAttr() {
		accessor.setAttr(value, getNextValueAttr());
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
