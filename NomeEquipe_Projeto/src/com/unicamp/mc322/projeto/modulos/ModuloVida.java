package com.unicamp.mc322.projeto.modulos;

public class ModuloVida {
	private int vida;
	
	public ModuloVida(int vida) {
		this.vida = vida;
	}
	
	public void recuperarVida(int bonus) {
		this.vida += bonus;
	}
	
	public void receberDano(int dano) {
		this.vida = Math.max(vida - dano, 0);
	}
	
	public int getVida() {
		return vida;
	}
}
