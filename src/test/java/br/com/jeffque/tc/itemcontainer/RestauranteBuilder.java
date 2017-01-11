package br.com.jeffque.tc.itemcontainer;

public class RestauranteBuilder extends Restaurante {
	public Restaurante build() {
		Restaurante newRestaurante = new Restaurante();
		
		newRestaurante.setNome(getNome());
		newRestaurante.setSatisfacao(getSatisfacao());
		newRestaurante.setTipo(getTipo());
		newRestaurante.setPreco(getPreco());
		newRestaurante.setDist(getDist());
		newRestaurante.setTempo(getTempo());
		
		return newRestaurante;
	}
	
	public void reset() {
		setNome("");
		setSatisfacao("");
		setTipo("");
		setPreco("");
		setDist("");
		setTempo("");
	}

	public void getInfo(Restaurante restaurante) {
		setNome(restaurante.getNome());
		setSatisfacao(restaurante.getSatisfacao());
		setTipo(restaurante.getTipo());
		setPreco(restaurante.getPreco());
		setDist(restaurante.getDist());
		setTempo(restaurante.getTempo());
	}
}
