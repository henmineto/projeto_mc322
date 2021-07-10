package com.unicamp.mc322.projeto.cartas.evolucoes;

import com.unicamp.mc322.projeto.cartas.ModuloEvolucao;
import com.unicamp.mc322.projeto.cartas.Traco;

public class BonusTraco extends ModuloEvolucao {
	
	private Traco bonusTraco;
	
	public BonusTraco(Traco bonusTraco) {
		this.bonusTraco = bonusTraco;
	}
	
	@Override
	protected void evoluir() {
		campeao.addTraco(bonusTraco);
	}

}
