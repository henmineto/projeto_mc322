package com.unicamp.mc322.projeto.modulos;

public class ModuloAtaque {
	private int ataque;
	private boolean zerado;
	
	public ModuloAtaque(int ataque) {
		this.ataque = ataque;

		zerado = false;
	}
	
	public int getAtaque() {
		if (zerado) {
			return 0;
		}
		return ataque;
	}
	
	public void aumentarAtaque(int bonus) {
		ataque += bonus;
	}

	public void zerarAtaque() {
		zerado = true;
	}

	public void liberarAtaque() {
		zerado = false;
	}
}
