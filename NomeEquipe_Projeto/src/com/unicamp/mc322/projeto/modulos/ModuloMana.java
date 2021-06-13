package com.unicamp.mc322.projeto.modulos;

public class ModuloMana {
	private int mana;
	private int maxMana;
	
	public ModuloMana(int maxMana) {
		this.maxMana = maxMana;
	}
	
	public void receberMana(int mana) {
		this.mana = Math.min(mana, maxMana);
	}
	
	public void gastarMana(int mana) {
		this.mana = Math.max(this.mana - mana, 0);
	}
	
	public int getMana() {
		return mana;
	}
}
