package com.unicamp.mc322.projeto.cartas;

import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;

public abstract class Efeito {
	protected Jogador jogador;
	protected boolean usado;
	private AtivacaoEfeito momento;
	
	protected Efeito(AtivacaoEfeito momento) {
		this.momento = momento;
	}
	
	protected AtivacaoEfeito getAtivacao() {
		return this.momento;
	}
	
	protected boolean getUsado() {
		return usado;
	}
	
	protected void ativar() throws Exception {
		if (usado)
			throw new Exception("N�o � pss�vel ativar esse efeito, pois ele j� foi utilizado.");
		
		usado = true;
	}
}
