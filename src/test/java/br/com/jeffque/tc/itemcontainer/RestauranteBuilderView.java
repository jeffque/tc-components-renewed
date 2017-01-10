package br.com.jeffque.tc.itemcontainer;

import br.com.jeffque.tc.ui.ValueableContainer;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.FocusListener;

public class RestauranteBuilderView extends ValueableContainer<Restaurante> {

	public RestauranteBuilderView() {
		super(new Restaurante());
		
		Restaurante restauranteBuilder = getBuilder();
		restauranteBuilder.setNome("");
		restauranteBuilder.setSatisfacao("");
		restauranteBuilder.setTipo("");
		restauranteBuilder.setPreco("");
		restauranteBuilder.setDist("");
		restauranteBuilder.setTempo("");
	}
	
	@Override
	public void initUI() {
		Label lblNome = new Label("Nome: ");
		Edit edtNome = new Edit();
		
		edtNome.addFocusListener(new FocusListener() {
			
			@Override
			public void focusOut(ControlEvent arg0) {
				getBuilder().setNome(edtNome.getText());
			}
			
			@Override
			public void focusIn(ControlEvent arg0) {
			}
		});
		add(lblNome, LEFT, TOP, PREFERRED, PREFERRED);
		add(edtNome, AFTER, SAME, FILL, SAME);
	}
	
	private Restaurante getBuilder() {
		return super.getValue();
	}
	
	@Override
	public Restaurante getValue() {
		Restaurante restauranteBuilder = getBuilder();
		Restaurante newRestaurante = new Restaurante();
		
		newRestaurante.setNome(restauranteBuilder.getNome());
		newRestaurante.setSatisfacao(restauranteBuilder.getSatisfacao());
		newRestaurante.setTipo(restauranteBuilder.getTipo());
		newRestaurante.setPreco(restauranteBuilder.getPreco());
		newRestaurante.setDist(restauranteBuilder.getDist());
		newRestaurante.setTempo(restauranteBuilder.getTempo());
		
		return newRestaurante;
	}

}
