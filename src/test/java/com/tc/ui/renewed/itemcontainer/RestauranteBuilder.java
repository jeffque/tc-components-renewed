package com.tc.ui.renewed.itemcontainer;

import totalcross.json.JSONObject;

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

	public void povoa(JSONObject json) {
		setNome(getJsonString(json, "nome"));
		setSatisfacao(getJsonString(json, "satisfacao"));
		setTipo(getJsonString(json, "tipo"));
		setPreco(getJsonString(json, "preco"));
		setDist(getJsonString(json, "dist"));
		setTempo(getJsonString(json, "tempo"));
	}
	
	private static String getJsonString(JSONObject json, String key) {
		return json.optString(key, null);
	}
}
