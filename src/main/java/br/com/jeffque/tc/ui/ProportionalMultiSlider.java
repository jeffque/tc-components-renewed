package br.com.jeffque.tc.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import totalcross.util.BigDecimal;

public class ProportionalMultiSlider<V> {
	List<SliderValue<V>> sliders = new ArrayList<>();
	BigDecimal sum;
	int nextId = 0;
	
	SliderValue<V> dragging;
	BigDecimal z;
	int n;
	BigDecimal bign;
	BigDecimal dragSum;
	BigDecimal maxz;
	Map<Integer,BigDecimal> oldValues = new HashMap<>();
	boolean estrategiaZero;
	
	public ProportionalMultiSlider(BigDecimal sum) {
		this.sum = sum;
	}
	
	public void addSlider(SliderValue<V> slider) {
		sliders.add(slider);
		slider.setId(nextId++);
		slider.setProportionalMultiSlider(this);
	}
	
	public List<SliderValue<V>> getSliders() {
		return sliders;
	}

	public void penDown(SliderValue<V> sliderValue) {
		dragging = sliderValue;
		z = sliderValue.getValueAttr();
		dragSum = BigDecimal.ZERO;
		n = 0;
		
		for (SliderValue<V> slider: sliders) {
			if (changeableSlider(slider)) {
				dragSum = dragSum.add(slider.getValueAttr());
				oldValues.put(slider.getId(), slider.getValueAttr());
				n++;
			}
		}
		bign = new BigDecimal(n);
		maxz = dragSum.add(z);
		estrategiaZero = dragSum.compareTo(BigDecimal.ZERO) == 0;
	}
	
	public void recalculo() {
		if (dragging != null && n >= 1) {
			if (dragging.getNextValueAttr().compareTo(maxz) > 0) {
				dragging.setNextValueAttr(maxz);
			}
			if (estrategiaZero) {
				recalculoZero();
			} else {
				recalculoDelta();
			}
			consolidaParcial();
		} else if (n < 1) {
			dragging.setNextValueAttr(z);
		}
	}
	
	void recalculoZero() {
		BigDecimal d = z.subtract(dragging.getNextValueAttr());
		
		BigDecimal delta = d.divide(bign, 30, BigDecimal.ROUND_HALF_EVEN);
		
		for (SliderValue<V> slider: sliders) {
			if (changeableSlider(slider)) {
				slider.setNextValueAttr(delta);
			}
		}
	}
	
	boolean changeableSlider(SliderValue<V> slider) {
		return !slider.equals(dragging) && slider.isUnlocked();
	}
	
	void recalculoDelta() {
		BigDecimal d = z.subtract(dragging.getNextValueAttr());


		BigDecimal delta = d.divide(dragSum, 30, BigDecimal.ROUND_HALF_EVEN);
		for (SliderValue<V> slider: sliders) {
			if (changeableSlider(slider)) {
				BigDecimal newAttr = oldValues.get(slider.id).multiply(BigDecimal.ONE.add(delta));
				slider.setNextValueAttr(newAttr);
			}
		}
	}

	public void consolida() {
		if (dragging != null) {
			for (SliderValue<V> slider: sliders) {
				slider.consolidaValueAttr();
			}
			dragging = null;
		}
	}
	
	public void consolidaParcial() {
		if (dragging != null) {
			for (SliderValue<V> slider: sliders) {
				slider.consolidaValueAttr();
			}
		}
	}
}
