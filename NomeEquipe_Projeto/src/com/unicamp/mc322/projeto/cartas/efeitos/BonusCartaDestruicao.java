package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Ativavel;
import com.unicamp.mc322.projeto.cartas.Efeito;
import com.unicamp.mc322.projeto.cartas.Evocavel;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;
import com.unicamp.mc322.projeto.jogo.MediadorEfeitos;

public class BonusCartaDestruicao extends Efeito {

	public BonusCartaDestruicao(AtivacaoEfeito momento) {
		super(momento);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ativar(Jogador jogador, Ativavel carta) throws Exception {
		if (carta instanceof Evocavel) {
			if (!((Evocavel)carta).estaVivo()) {
				super.ativar(jogador, carta);
				
				MediadorEfeitos.getInstance().darBonusCartaDestruicao(jogador);
			}
		}
		else {
			throw new Exception("Efeito BonusCartaDestruicao n�o pode ser usado nessa carta: "+ carta);
		}
	}

	@Override
	public String toString() {
		return "Puxe uma carta quando esta carta for destruída";
	}
}
