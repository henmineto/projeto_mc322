package com.unicamp.mc322.projeto.modulos;

public class ModuloAtaque {
	private int ataque;
	
	public ModuloAtaque(int ataque) {
		this.ataque = ataque;
	}
	
	public int getAtaque() {
		return ataque;
	}
	
	public void aumentarAtaque(int bonus) {
		ataque += bonus;
	}
}
