package com.unicamp.mc322.projeto.cartas.condicoes;

import com.unicamp.mc322.projeto.cartas.Atacavel;
import com.unicamp.mc322.projeto.cartas.Campeao;
import com.unicamp.mc322.projeto.cartas.ModuloCondicao;
import com.unicamp.mc322.projeto.cartas.Evocavel;

public class QuantidadeSeguidoresMortos extends ModuloCondicao {

	public QuantidadeSeguidoresMortos(int objetivo) {
		super(objetivo);
	}

	@Override
	public void contabilizarAtaque(Atacavel atacavel, int dano) {
		if (atacavel != null && !(atacavel instanceof Campeao) && !atacavel.estaVivo()) {
			quantidadeAtingida++;
		}
		
		super.contabilizarAtaque(atacavel, dano);
	}

}
