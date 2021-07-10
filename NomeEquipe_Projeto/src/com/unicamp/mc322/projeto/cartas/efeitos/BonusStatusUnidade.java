package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Ativavel;
import com.unicamp.mc322.projeto.cartas.Efeito;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;
import com.unicamp.mc322.projeto.jogo.MediadorEfeitos;

public class BonusStatusUnidade extends Efeito {

	private int bonusAtaque;
	private int bonusDefesa;
	
	public BonusStatusUnidade(AtivacaoEfeito momento, int bonusAtaque, int bonusDefesa) {
		super(momento);
		this.bonusAtaque = bonusAtaque;
		this.bonusDefesa = bonusDefesa;
	}

	@Override
	public void ativar(Jogador jogador, Ativavel carta) throws Exception {
		super.ativar(jogador, carta);
		
		MediadorEfeitos.getInstance().darBonusStatusUnidadeAliada(jogador, bonusAtaque, bonusDefesa);
	}
}
