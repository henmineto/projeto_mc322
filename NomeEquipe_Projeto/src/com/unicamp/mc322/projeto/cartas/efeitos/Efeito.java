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
			throw new Exception("Não é pssível ativar esse efeito, pois ele já foi utilizado.");
		
		usado = true;
	}
}
