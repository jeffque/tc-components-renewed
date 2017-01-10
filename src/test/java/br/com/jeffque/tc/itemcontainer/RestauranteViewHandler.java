package br.com.jeffque.tc.itemcontainer;

import br.com.jeffque.tc.util.ContentViewHandler;

public class RestauranteViewHandler implements ContentViewHandler<Restaurante> {

	@Override
	public RestauranteItemView createView(Restaurante content) {
		return new RestauranteItemView(content);
	}

}
