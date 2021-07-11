package com.unicamp.mc322.projeto.cartas.evolucoes;

import com.unicamp.mc322.projeto.cartas.ModuloEvolucao;

public class BonusVida extends ModuloEvolucao {

	private int bonusVida;
	
	public BonusVida(int bonus) {
		this.bonusVida = bonus;
	}
	
	@Override
	protected void evoluir() {
		campeao.aumentarVida(bonusVida);
	}

	@Override
	public String toString() {
		return "Recebe " + bonusVida + " vida";
	}
}
