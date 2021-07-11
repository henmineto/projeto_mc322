package com.unicamp.mc322.projeto.cartas.tracos;

import com.unicamp.mc322.projeto.cartas.Evocavel;
import com.unicamp.mc322.projeto.cartas.Traco;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoTraco;

public class Elusivo extends Traco {

	public Elusivo(AtivacaoTraco ativacao) {
		super(ativacao);
	}

	@Override
	protected void ativar(Evocavel evocavel) {
		evocavel.setElusivo();
	}

	@Override
	public String toString() {
		return "Elusivo";
	}
}
