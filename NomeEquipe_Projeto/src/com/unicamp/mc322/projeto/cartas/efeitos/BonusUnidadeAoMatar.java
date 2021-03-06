package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Ativavel;
import com.unicamp.mc322.projeto.cartas.Efeito;
import com.unicamp.mc322.projeto.cartas.Evocavel;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;
import com.unicamp.mc322.projeto.jogo.LORException;
import com.unicamp.mc322.projeto.jogo.MediadorEfeitos;

public class BonusUnidadeAoMatar extends Efeito {

	private Evocavel unidadeBonus;

	public BonusUnidadeAoMatar(AtivacaoEfeito momento, Evocavel unidadeBonus) {
		super(momento);

		this.unidadeBonus = unidadeBonus;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ativar(Jogador jogador, Ativavel carta) throws LORException {
		if (carta instanceof Evocavel) {
			if (((Evocavel)carta).verificarMatouNesseTurno()) {
				super.ativar(jogador, carta);

				MediadorEfeitos.getInstance().darBonusUnidadeAoMatar(jogador, unidadeBonus);
			}
		}
		else {
			throw new LORException("Efeito BonusUnidadeAoMatar n�o pode ser usado nessa carta: "+ carta);
		}
	}

	@Override
	public String toString() {
		return "Ao destruir uma unidade inimiga, receba unidade";
	}

}
