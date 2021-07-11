package com.unicamp.mc322.projeto.cartas;

public abstract class ModuloEvolucao {

	protected Campeao campeao;
	
	void setCampeao(Campeao campeao) {
		this.campeao = campeao;
	}
	
	protected abstract void evoluir();

	@Override
	public String toString() {
		return "Evolução";
	}
}
