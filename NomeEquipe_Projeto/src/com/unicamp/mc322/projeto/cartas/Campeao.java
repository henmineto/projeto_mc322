package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

public class Campeao extends Seguidor {
	
	private ModuloCondicao condicao;
	private ArrayList<ModuloEvolucao> evolucoes;

	public Campeao(String nome, int vida, int ataque, int custo, ModuloCondicao condicao) {
		super(nome, vida, ataque, custo);
		this.evolucoes = new ArrayList<ModuloEvolucao>();
		setCondicao(condicao);
	}
	
	public void addEvolucao(ModuloEvolucao evolucao) {
		evolucao.setCampeao(this);
		evolucoes.add(evolucao);
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

	private String getInfoEvolucoes() {
		String txt;

		if (evolucoes.size() > 0) {
			txt = "Tem os bonus: (";
			boolean virgula = false;

			for (int i = 0; i < evolucoes.size(); i++) {
				if (virgula) {
					txt += ", ";
				}
				virgula = true;

				txt += evolucoes.get(i);
			}

			txt += ")";
		} else {
			txt = "Sem bonus de evolução";
		}

		return txt;
	}

	@Override
	public String getInfo() {
		return "Campeão - " + condicao  + " e " + getInfoEvolucoes() + " - " + getInfoParcial();
	}
}
