package br.com.jeffque.tc.itemcontainer;

import br.com.jeffque.tc.ui.ValueableContainer;
import totalcross.sys.Vm;
import totalcross.ui.Label;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;

public class RestauranteItemView extends ValueableContainer<Restaurante> {

	public RestauranteItemView(Restaurante value) {
		super(value);
	}
	
	@Override
	public void initUI() {
		Label nome = new Label(getValue().getNome());
		add(nome, LEFT, TOP, PREFERRED, PREFERRED);
		
		
		addPressListener(new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent arg0) {
				Vm.debug("clique detectado dentro do RestauranteView");
			}
		});
	}
	
}
