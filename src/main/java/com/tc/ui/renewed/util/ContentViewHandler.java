package com.tc.ui.renewed.util;

import com.tc.ui.renewed.ui.ValueableContainer;

public interface ContentViewHandler<T> {
	ValueableContainer<? extends T> createView(T content);
}
