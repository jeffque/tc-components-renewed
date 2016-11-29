package br.com.jeffque.tc.ui;

import br.com.jeffque.tc.util.ReadWriteAccessor;
import totalcross.sys.InvalidNumberException;
import totalcross.ui.Edit;
import totalcross.ui.Slider;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.DragEvent;
import totalcross.ui.event.FocusListener;
import totalcross.ui.event.PenEvent;
import totalcross.ui.event.PenListener;
import totalcross.ui.event.PressListener;
import totalcross.util.BigDecimal;

public class SliderValue<V> {
	int factor;
	Slider slider;
	Edit edtValue;
	ReadWriteAccessor<V, BigDecimal> accessor;
	V value;
	BigDecimal max;
	
	boolean unlocked;
	ProportionalMultiSlider<V> proportionalMultiSlider;
	int id;
	
	public SliderValue(V value, ReadWriteAccessor<V, BigDecimal> accessor, BigDecimal max) {
		this.factor = 4;
		this.value = value;
		this.accessor = new ReadWriteAccessor<V, BigDecimal>() {
			ReadWriteAccessor<V, BigDecimal> wrappedAccessor = accessor;
			@Override
			public BigDecimal getAttr(V source) {
				return wrappedAccessor.getAttr(source);
			}

			@Override
			public void setAttr(V source, BigDecimal value) {
				wrappedAccessor.setAttr(source, value);
				edtValue.setText(value.setScale(3, BigDecimal.ROUND_HALF_EVEN).toPlainString());
			}
		};
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
		
		edtValue = new Edit();
		edtValue.setValidChars("0123456789.");
		edtValue.setText(val.setScale(3, BigDecimal.ROUND_HALF_EVEN).toPlainString());
		edtValue.addFocusListener(new FocusListener() {
			
			@Override
			public void focusOut(ControlEvent arg0) {
				BigDecimal newBD = BigDecimal.ZERO;
				
				if (!edtValue.getText().matches("^[.]?$")) {
					try {
						newBD = new BigDecimal(edtValue.getText());
					} catch (InvalidNumberException e) {
					}
				}
				if (proportionalMultiSlider != null) {
					proportionalMultiSlider.penDown(SliderValue.this);
				}
				setNextValueAttr(newBD);
				if (proportionalMultiSlider != null) {
					proportionalMultiSlider.recalculo();
					proportionalMultiSlider.consolida();
				}
			}
			
			@Override
			public void focusIn(ControlEvent arg0) {
			}
		});
		
		unlocked = true;
	}
	
	public Slider getSlider() {
		return slider;
	}
	
	public Edit getEdtValue() {
		return edtValue;
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
	
	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}
	
	public boolean isUnlocked() {
		return unlocked;
	}
}
