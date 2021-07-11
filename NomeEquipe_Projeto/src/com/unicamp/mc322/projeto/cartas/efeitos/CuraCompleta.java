package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Ativavel;
import com.unicamp.mc322.projeto.cartas.Efeito;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;
import com.unicamp.mc322.projeto.jogo.MediadorEfeitos;

public class CuraCompleta extends Efeito {

	public CuraCompleta(AtivacaoEfeito momento) {
		super(momento);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ativar(Jogador jogador, Ativavel carta) throws Exception {
		super.ativar(jogador, carta);

		MediadorEfeitos.getInstance().realizarCuraCompleta(jogador);
	}

	@Override
	public String toString() {
		return "Cura completamente uma unidade em " + momento;
	}

}
