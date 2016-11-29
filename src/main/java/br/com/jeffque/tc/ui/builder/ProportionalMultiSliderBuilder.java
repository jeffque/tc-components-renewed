package br.com.jeffque.tc.ui.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.jeffque.tc.ui.ProportionalMultiSlider;
import br.com.jeffque.tc.ui.SliderValue;
import br.com.jeffque.tc.util.Presenter;
import br.com.jeffque.tc.util.ReadWriteAccessor;
import totalcross.util.BigDecimal;

public class ProportionalMultiSliderBuilder<V> {
	BigDecimal sum;
	Presenter<V> presenter;
	List<V> values = new ArrayList<>();
	ReadWriteAccessor<V, BigDecimal> accessor;
	
	public ProportionalMultiSliderBuilder(Presenter<V> presenter, ReadWriteAccessor<V, BigDecimal> accessor) {
		this.presenter = presenter;
		this.accessor = accessor;
	}
	
	public void addValue(V value) {
		values.add(value);
	}
	
	public void addValues(Collection<V> value) {
		values.addAll(value);
	}
	
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	
	public static <V> void normalizeValuesToSum(List<V> values, ReadWriteAccessor<V, BigDecimal> accessor, BigDecimal aux, BigDecimal sum) {
		if (aux.compareTo(sum) > 0) {
			BigDecimal deltaRemnant = aux;
			for (V v: values) {
				BigDecimal val = accessor.getAttr(v);
				int comparison = val.compareTo(deltaRemnant);
				if (comparison < 0) {
					deltaRemnant = deltaRemnant.subtract(val);
					accessor.setAttr(v, BigDecimal.ZERO);
				} else {
					BigDecimal newVal = comparison > 0? val.subtract(deltaRemnant): BigDecimal.ZERO;
					accessor.setAttr(v, newVal);
					break;
				}
			}
		} else if (aux.compareTo(sum) < 0) {
			V v0 = values.get(0);
			BigDecimal firstValue = accessor.getAttr(v0);
			BigDecimal delta2sum = sum.subtract(aux);
			BigDecimal newValue = delta2sum.add(firstValue);
			
			accessor.setAttr(v0, newValue);
		}
	}
	
	void checkAndNormalize() {
		if (values.size() <= 1) {
			throw new BuildComponentException("Impossible to build component: insufficient values");
		}
		for (V value: values) {
			if (accessor.getAttr(value) == null) {
				accessor.setAttr(value, BigDecimal.ZERO);
			} else if (accessor.getAttr(value).signum() == -1) {
				throw new BuildComponentException("Impossible to build component: negative values");
			}
		}
		if (sum == null) {
			sum = BigDecimal.ZERO;
			for (V value: values) {
				sum = sum.add(accessor.getAttr(value));
			}
		} else {
			BigDecimal aux = BigDecimal.ZERO;
			for (V value: values) {
				aux = aux.add(accessor.getAttr(value));
			}
			normalizeValuesToSum(values, accessor, aux, sum);
		}
		
		if (sum.compareTo(BigDecimal.ZERO) <= 0) {
			throw new BuildComponentException("Impossible to build component: non-positive sum");
		}
	}
	
	public ProportionalMultiSlider<V> build() {
		checkAndNormalize();
		
		ProportionalMultiSlider<V> multiSlider = new ProportionalMultiSlider<>(sum);
		for (V value: values) {
			SliderValue<V> slider = new SliderValue<V>(value, presenter, accessor, sum);
			multiSlider.addSlider(slider);
		}
		return multiSlider;
	}
}
