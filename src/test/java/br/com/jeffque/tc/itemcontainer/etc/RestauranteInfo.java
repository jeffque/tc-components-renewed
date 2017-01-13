package br.com.jeffque.tc.itemcontainer.etc;

import br.com.jeffque.tc.itemcontainer.Restaurante;
import br.com.jeffque.tc.ui.BaseContainer;
import br.com.jeffque.tc.ui.ItemContainer;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.FocusListener;
import totalcross.ui.event.PressListener;

public class RestauranteInfo extends BaseContainer {
	ItemContainer<Restaurante> restauranteScreen; // TODO usar um padrão mais bacana que esse... quem sabe com callback?
	Label qnt;
	String fileName = "restaurantes.json";
	
	public RestauranteInfo(ItemContainer<Restaurante> restauranteScreen) {
		super();
		
		this.restauranteScreen = restauranteScreen;
	}
	
	@Override
	public void initUI() {
		super.initUI();
		qnt = new Label("Quantidade: " + restauranteScreen.getItems().size());
		
		Button limparButton = new Button("Limpar");
		limparButton.addPressListener(new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent e) {
				limpar();
			}
		});
		
		add(qnt, LEFT, AFTER, FILL, PREFERRED);
		
		Label fileNameLbl = new Label("Nome do arquivo: ");
		Edit fileNameEdt = new Edit(fileName);
		fileNameEdt.setText(fileName);
		
		fileNameEdt.addFocusListener(new FocusListener() {
			
			@Override
			public void focusOut(ControlEvent e) {
				fileName = fileNameEdt.getText();
			}
			
			@Override
			public void focusIn(ControlEvent e) {
			}
		});
		
		add(fileNameLbl, LEFT, AFTER, PREFERRED, PREFERRED);
		add(fileNameEdt, AFTER, SAME, FILL, SAME);
		
		add(limparButton, LEFT, AFTER, FILL, PREFERRED);
	}
	
	public String getFileName() {
		return fileName;
	}
	
	private void salvar() {
		
	}
	
	private void carregar() {
		// TODO implementar
	}
	
	private void limpar() {
		qnt.setText("Quantidade: 0");
		qnt.repaintNow();
		restauranteScreen.reset();
	}
}
