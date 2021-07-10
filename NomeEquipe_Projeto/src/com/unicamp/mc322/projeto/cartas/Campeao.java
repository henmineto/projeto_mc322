package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.jogadores.Jogador;

public class Campeao extends Seguidor {
	
	private ModuloCondicao condicao;
	private ArrayList<ModuloEvolucao> evolucoes;
	private boolean nivelSuperior;

	public Campeao(String nome, int vida, int ataque, int custo, ArrayList<Efeito> efeitos, ArrayList<Traco> tracos, ModuloCondicao condicao, ArrayList<ModuloEvolucao> evolucoes) {
		super(nome, vida, ataque, custo, efeitos, tracos);
		setEvolucao(evolucoes);
		setCondicao(condicao);
	}
	
	@Override
	public void atacar(Atacavel atacavel) {
		super.atacar(atacavel);
		condicao.contabilizarDanoSofrido(vida.getUltimoDanoSofrido());
		condicao.contabilizarAtaque(atacavel, ataque.getAtaque());
	}

	@Override
	public void receberDano(int dano) {
		condicao.contabilizarDanoSofrido(Math.min(vida.getVida(), dano));
		super.receberDano(dano);
	}
	
	void evoluir() {
		if (evolucoes != null) {
			nivelSuperior = true;
			for (ModuloEvolucao evolucao : evolucoes) {
				evolucao.evoluir();
			}
		}
	}
	
	private void setCondicao(ModuloCondicao condicao) {
		this.condicao = condicao;
		
		if (this.condicao != null) {
			this.condicao.setCampeao(this);
		}
	}
	
	private void setEvolucao(ArrayList<ModuloEvolucao> evolucoes) {
		this.evolucoes = evolucoes;
		
		if (this.evolucoes != null) {
			for (ModuloEvolucao evolucao : evolucoes) {
				evolucao.setCampeao(this);
			}
		}
	}
}
