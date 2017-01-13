package br.com.jeffque.tc.itemcontainer;

import br.com.jeffque.tc.itemcontainer.etc.RestauranteInfo;
import br.com.jeffque.tc.ui.BaseContainer;
import br.com.jeffque.tc.ui.BaseTCMain;
import br.com.jeffque.tc.ui.ItemContainer;
import totalcross.res.Resources;
import totalcross.ui.Button;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;

public class ItemContainerTest extends BaseContainer {
	ItemContainer<Restaurante> itemContainer = new ItemContainer<>(new RestauranteViewHandler());
	RestauranteBuilderView restauranteBuilderView = new RestauranteBuilderView();
	
	public ItemContainerTest(BaseTCMain<?> baseTest) {
		super(baseTest);
	}
	
	@Override
	public void initUI() {
		super.initUI();
		
		getHeaderBar().addBarButton("Carregar", Resources.comboArrow, new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent e) {
				present(new RestauranteInfo(itemContainer));
			}
		});
		
		Button addBtn = new Button("Adicionar Restaurante");
		
		addBtn.addPressListener(new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent arg0) {
				itemContainer.addItem(restauranteBuilderView.getValue());
			}
		});
		
		Button resetBtn = new Button("Resetar Restaurante");
		
		resetBtn.addPressListener(new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent arg0) {
				restauranteBuilderView.reset();
			}
		});
		
		add(restauranteBuilderView, LEFT, AFTER, FILL, PREFERRED);
		add(addBtn, LEFT, AFTER, FILL, PREFERRED);
		add(resetBtn, LEFT, AFTER, FILL, PREFERRED);
		add(itemContainer, LEFT, AFTER, FILL, FILL);
	}
}
