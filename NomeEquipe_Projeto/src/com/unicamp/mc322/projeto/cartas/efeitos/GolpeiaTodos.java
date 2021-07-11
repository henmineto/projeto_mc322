package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Ativavel;
import com.unicamp.mc322.projeto.cartas.Efeito;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;
import com.unicamp.mc322.projeto.jogo.MediadorEfeitos;

public class GolpeiaTodos extends Efeito {

	public GolpeiaTodos(AtivacaoEfeito momento) {
		super(momento);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ativar(Jogador jogador, Ativavel carta) throws Exception {
		super.ativar(jogador, carta);
		
		MediadorEfeitos.getInstance().golpearTodosEvocadosOponente(jogador);
	}

	@Override
	public String toString() {
		return "Força um ataque em todas as unidades inimigas em " + momento;
	}
}
