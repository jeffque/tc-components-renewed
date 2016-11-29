package br.com.jeffque.tc.ui;

import br.com.jeffque.tc.util.ReadWriteAccessor;
import totalcross.ui.Slider;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.DragEvent;
import totalcross.ui.event.PenEvent;
import totalcross.ui.event.PenListener;
import totalcross.ui.event.PressListener;
import totalcross.util.BigDecimal;

public class SliderValue<V> {
	int factor;
	Slider slider;
	ReadWriteAccessor<V, BigDecimal> accessor;
	V value;
	BigDecimal max;
	
	ProportionalMultiSlider<V> proportionalMultiSlider;
	int id;
	
	public SliderValue(V value, ReadWriteAccessor<V, BigDecimal> accessor, BigDecimal max) {
		this.factor = 4;
		this.value = value;
		this.accessor = accessor;
		this.max = max;
		BigDecimal val = accessor.getAttr(value);
		
		slider = new Slider();
		int sliderMax = 1;
		
		for (int i = 0; i < factor; i++) {
			sliderMax *= 10;
		}
		slider.setValues(0, 1, 0, sliderMax + 1);
		
		setNextValueAttr(val);
		
		slider.setLiveScrolling(true);
		slider.addPressListener(new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent arg0) {
				if (proportionalMultiSlider != null) {
					proportionalMultiSlider.recalculo();
					proportionalMultiSlider.consolidaParcial();
				}
			}
		});
		
		slider.addPenListener(new PenListener() {
			
			@Override
			public void penUp(PenEvent arg0) {
				if (proportionalMultiSlider != null) {
					proportionalMultiSlider.consolida();
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
			
			@Override
			public void penDown(PenEvent arg0) {
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
		BigDecimal proportion = new BigDecimal(slider.getValue()).movePointLeft(factor);
		return proportion.multiply(max);
	}

	public void setNextValueAttr(BigDecimal newNextValue) {
		BigDecimal proportion = newNextValue.divide(max, 30, BigDecimal.ROUND_HALF_EVEN);
		// There will be precision until a proportion of 10^-factor
		proportion = proportion.movePointRight(factor).setScale(0, BigDecimal.ROUND_HALF_EVEN);
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
