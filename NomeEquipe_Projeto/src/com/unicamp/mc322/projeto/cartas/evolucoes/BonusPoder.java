package com.unicamp.mc322.projeto.cartas.evolucoes;

import com.unicamp.mc322.projeto.cartas.ModuloEvolucao;

public class BonusPoder extends ModuloEvolucao {
	private int bonus;
	
	public BonusPoder(int bonus) {
		this.bonus = bonus;
	}
	
	@Override
	protected void evoluir() {
		campeao.aumentarDano(bonus);
	}

}
