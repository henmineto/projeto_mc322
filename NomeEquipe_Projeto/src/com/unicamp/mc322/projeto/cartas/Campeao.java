package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.jogadores.Jogador;

public class Campeao extends Seguidor {
	
	private ModuloCondicao condicao;
	private ModuloEvolucao evolucao;
	private boolean nivelSuperior;

	public Campeao(int vida, int ataque, int custo, ArrayList<Efeito> efeitos, ArrayList<Traco> tracos, ModuloCondicao condicao, ModuloEvolucao evolucao) {
		super(vida, ataque, custo, efeitos, tracos);
		setEvolucao(evolucao);
		setCondicao(condicao);
	}
	
	@Override
	public void atacar(Unidade unidade) {
		super.atacar(unidade);
		condicao.contabilizarAtaque(unidade, ataque.getAtaque());
	}
	
	@Override
	public void atacar(Jogador jogador) {
		super.atacar(jogador);
		condicao.contabilizarAtaque(null, ataque.getAtaque());
	}
	
	void evoluir() {
		if (evolucao != null) {
			evolucao.evoluir();
			nivelSuperior = true;
		}
	}
	
	private void setCondicao(ModuloCondicao condicao) {
		this.condicao = condicao;
		
		if (this.condicao != null) {
			this.condicao.setCampeao(this);
		}
	}
	
	private void setEvolucao(ModuloEvolucao evolucao) {
		this.evolucao = evolucao;
		
		if (this.evolucao != null) {
			this.evolucao.setCampeao(this);
		}
	}
}
