package com.unicamp.mc322.projeto.cartas.efeitos;

public abstract class Efeito {
	private AtivacaoEfeito momento;
	private boolean usado;
	
	public Efeito(AtivacaoEfeito momento) {
		this.momento = momento;
	}
	
	public AtivacaoEfeito getAtivacao() {
		return this.momento;
	}
	
	public boolean getUsado() {
		return usado;
	}
	
	public void ativar() {
		//criar um método para verificar se o jogador quer ativar efeito no Mediador
		usado = true;
	}
}
