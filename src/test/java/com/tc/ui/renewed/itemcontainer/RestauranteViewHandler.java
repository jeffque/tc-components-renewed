package com.tc.ui.renewed.itemcontainer;

import com.tc.ui.renewed.util.ContentViewHandler;

public class RestauranteViewHandler implements ContentViewHandler<Restaurante> {

	@Override
	public RestauranteItemView createView(Restaurante content) {
		return new RestauranteItemView(content);
	}

}
