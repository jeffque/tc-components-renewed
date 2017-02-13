package com.tc.ui.renewed.itemcontainer;

import com.tc.ui.renewed.ui.ValueableContainer;

import totalcross.sys.Vm;
import totalcross.ui.Label;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;

public class RestauranteItemView extends ValueableContainer<Restaurante> {
	Label nome;
	Label satisfacao;
	Label tipo;
	Label preco;
	Label dist;
	Label tempo;
	
	public RestauranteItemView(Restaurante value) {
		super(value);
		
		nome = new Label(getValue().getNome());
		satisfacao = new Label(getValue().getSatisfacao());
		tipo = new Label(getValue().getTipo());
		preco = new Label(getValue().getPreco());
		dist = new Label(getValue().getDist());
		tempo = new Label(getValue().getTempo());
		
		nome.transparentBackground = true;
		satisfacao.transparentBackground = true;
		tipo.transparentBackground = true;
		preco.transparentBackground = true;
		dist.transparentBackground = true;
		tempo.transparentBackground = true;
	}
	
	@Override
	public void initUI() {
		add(nome, LEFT, TOP, PREFERRED, PREFERRED);
		add(tipo, SAME, AFTER, PREFERRED, SAME, nome);
		add(preco, AFTER + 5, SAME, PREFERRED, SAME, tipo);
		add(dist, SAME, AFTER, PREFERRED, SAME, tipo);
		add(tempo, AFTER + 5, SAME, PREFERRED, SAME, dist);
		
		add(satisfacao, RIGHT, TOP, PREFERRED, PREFERRED);
		
		addPressListener(new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent arg0) {
				Vm.debug("clique detectado dentro do RestauranteView");
			}
		});
	}
	
	@Override
	public int getPreferredHeight() {
		return nome.getPreferredHeight() * 3;
	}
}
