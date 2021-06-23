package com.unicamp.mc322.projeto.cartas;

public abstract class ModuloCondicao {
	protected int quantidadeAtingida;
	private int objetivo;
	private Campeao campeao;
	
	protected ModuloCondicao(int objetivo) {
		this.objetivo = objetivo;
	}
	
	protected boolean isObjetivoAtingido() {
		return quantidadeAtingida >= objetivo;
	}
	
	protected void contabilizarAtaque(Atacavel atacavel, int dano) {
		if (isObjetivoAtingido()) {
			campeao.evoluir();
		}
	}
	
	void setCampeao(Campeao campeao) {
		this.campeao = campeao;
	}
}
