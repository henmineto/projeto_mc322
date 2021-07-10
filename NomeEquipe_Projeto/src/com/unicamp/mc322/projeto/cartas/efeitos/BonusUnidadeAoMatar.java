package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Efeito;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;

public class BonusUnidadeAoMatar extends Efeito {

	private Evocavel unidadeBonus;

	public BonusUnidadeAoMatar(AtivacaoEfeito momento, Evocavel unidadeBonus) {
		super(momento);

		this.unidadeBonus = unidadeBonus;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ativar(Jogador jogador, Ativavel carta) throws Exception {
		if (carta instanceof Evocavel) {
			if (((Evocavel)carta).verificarMatouNesseTurno()) {
				super.ativar(jogador, carta);

				MediadorEfeitos.getInstance().darBonusUnidadeAoMatar(jogador, unidadeBonus);
			}
		}
		else {
			throw new Exception("Efeito BonusUnidadeAoMatar n�o pode ser usado nessa carta: "+ carta);
		}
	}

}
