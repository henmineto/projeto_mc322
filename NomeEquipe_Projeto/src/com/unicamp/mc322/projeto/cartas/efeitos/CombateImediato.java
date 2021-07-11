package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Ativavel;
import com.unicamp.mc322.projeto.cartas.Efeito;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;
import com.unicamp.mc322.projeto.jogo.LORException;
import com.unicamp.mc322.projeto.jogo.MediadorEfeitos;

public class CombateImediato extends Efeito {

	public CombateImediato(AtivacaoEfeito momento) {
		super(momento);
	}

	@Override
	public void ativar(Jogador jogador, Ativavel carta) throws LORException {
		super.ativar(jogador, carta);
		
		MediadorEfeitos.getInstance().realizarCombateImediato(jogador);
	}

	@Override
	public String toString() {
		return "Força combate com inimigo em " + momento;
	}
}
