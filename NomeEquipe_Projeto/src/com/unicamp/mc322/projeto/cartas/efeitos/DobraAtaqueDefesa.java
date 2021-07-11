package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Ativavel;
import com.unicamp.mc322.projeto.cartas.Efeito;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;
import com.unicamp.mc322.projeto.jogo.LORException;
import com.unicamp.mc322.projeto.jogo.MediadorEfeitos;

public class DobraAtaqueDefesa extends Efeito {

	public DobraAtaqueDefesa(AtivacaoEfeito momento) {
		super(momento);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ativar(Jogador jogador, Ativavel carta) throws LORException {
		super.ativar(jogador, carta);
		
		MediadorEfeitos.getInstance().dobrarAtaqueDefesaUnidadeAliada(jogador);
	}

	@Override
	public String toString() {
		return "Dobra a vida e ataque de uma unidade em " + momento;
	}
}
