package com.unicamp.mc322.projeto.cartas.condicoes;

import com.unicamp.mc322.projeto.cartas.ModuloCondicao;

public class QuantidadeAtaque extends ModuloCondicao {

	public QuantidadeAtaque(int objetivo) {
		super(objetivo);
	}

	@Override
	public String toString() {
		return "Evolui ao atacar " + objetivo + " vezes";
	}
}
