package br.com.jeffque.tc.itemcontainer;

public class Restaurante {
	private String nome;
	private String satisfacao;
	private String tipo;
	private String preco;
	private String dist;
	private String tempo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSatisfacao() {
		return satisfacao;
	}

	public void setSatisfacao(String satisfacao) {
		this.satisfacao = satisfacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else if (other == null) {
			return false;
		} else if (!(other instanceof Restaurante)) {
			return false;
		}
		Restaurante otherMyClass = (Restaurante) other;
		return getNome().equals(otherMyClass.getNome());
	}
	
}
