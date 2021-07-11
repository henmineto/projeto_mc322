package com.unicamp.mc322.projeto.cartas;

import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;
import com.unicamp.mc322.projeto.jogo.LORException;

public abstract class Efeito {
	protected boolean usado;
	protected AtivacaoEfeito momento;
	
	protected Efeito(AtivacaoEfeito momento) {
		this.momento = momento;
	}
	
	protected AtivacaoEfeito getAtivacao() {
		return this.momento;
	}
	
	protected boolean getUsado() {
		return usado;
	}
	
	protected void ativar(Jogador jogador, Ativavel carta) throws LORException {
		if (usado)
			throw new LORException("N�o � pss�vel ativar esse efeito, pois ele j� foi utilizado.");
		
		usado = true;
	}

	@Override
	public String toString() {
		return "Efeito";
	}
}
