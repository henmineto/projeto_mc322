package com.unicamp.mc322.projeto.cartas.condicoes;

import com.unicamp.mc322.projeto.cartas.Campeao;
import com.unicamp.mc322.projeto.cartas.ModuloCondicao;
import com.unicamp.mc322.projeto.cartas.Unidade;

public class QuantidadeSeguidoresMortos extends ModuloCondicao {

	public QuantidadeSeguidoresMortos(int objetivo) {
		super(objetivo);
	}

	@Override
	public void contabilizarAtaque(Unidade unidade, int dano) {
		if (unidade != null && !(unidade instanceof Campeao) && !unidade.estaVivo()) {
			quantidadeAtingida++;
		}
		
		super.contabilizarAtaque(unidade, dano);
	}

}
