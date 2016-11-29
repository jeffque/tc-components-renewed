package br.com.jeffque.tc.ui;

import br.com.jeffque.magic.util.Valueable;
import br.com.jeffque.tc.util.Presenter;
import totalcross.ui.Label;

public class LabelValue<V> extends Label implements Valueable<V> {
	Presenter<V> presenter;
	V value;

	public LabelValue(Presenter<V> presenter, V value) {
		super(presenter.stringfy(value));
		this.presenter = presenter;
		this.value = value;
	}

	public LabelValue(Presenter<V> presenter, V value, int align) {
		super(presenter.stringfy(value), align);
		
		this.presenter = presenter;
		this.value = value;
	}

	public LabelValue(Presenter<V> presenter, V value, int align, int fore, boolean bold) {
		super(presenter.stringfy(value), align, fore, bold);
		
		this.presenter = presenter;
		this.value = value;
	}

	@Override
	public V getValue() {
		return value;
	}
	
	public void updateText() {
		super.setText(presenter.stringfy(value));
	}

}
