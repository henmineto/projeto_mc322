package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.jogadores.Jogador;

public abstract class Efeito {
	protected Jogador jogador;
	protected boolean usado;
	private AtivacaoEfeito momento;
	
	public Efeito(AtivacaoEfeito momento) {
		this.momento = momento;
	}
	
	public AtivacaoEfeito getAtivacao() {
		return this.momento;
	}
	
	public boolean getUsado() {
		return usado;
	}
	
	public void ativar() throws Exception {
		if (usado)
			throw new Exception("N�o � pss�vel ativar esse efeito, pois ele j� foi utilizado.");
		
		usado = true;
	}
}
