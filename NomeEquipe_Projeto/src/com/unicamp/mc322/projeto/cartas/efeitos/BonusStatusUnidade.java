package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.AtivacaoEfeito;
import com.unicamp.mc322.projeto.cartas.Efeito;
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
	public void ativar() throws Exception {
		super.ativar();
		
		MediadorEfeitos.getInstance().darBonusStatusUnidadeAliada(jogador, bonusAtaque, bonusDefesa);
	}
}
