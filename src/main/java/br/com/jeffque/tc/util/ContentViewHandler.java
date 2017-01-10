package br.com.jeffque.tc.util;

import br.com.jeffque.tc.ui.ValueableContainer;

public interface ContentViewHandler<T> {
	ValueableContainer<? extends T> createView(T content);
}
