package com.unicamp.mc322.projeto.jogadores;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Feitico;
import com.unicamp.mc322.projeto.cartas.Unidade;
import com.unicamp.mc322.projeto.decks.Deck;
import com.unicamp.mc322.projeto.modulos.ModuloMana;
import com.unicamp.mc322.projeto.modulos.ModuloVida;

public class Jogador {
	private final int MAX_MANA_RODADA = 10;
	private final int MAX_MANA_FEITICO = 3;
	
	private ArrayList<Carta> mao;
	private Deck deck;
	private ModuloMana mana;
	private ModuloMana manaFeitico;
	private ModuloVida vidaNexus;
	
	private Jogador(int vida) {
		this.mana = new ModuloMana(MAX_MANA_RODADA);
		this.manaFeitico = new ModuloMana(MAX_MANA_FEITICO);
		this.vidaNexus = new ModuloVida(vida);
	}
	
	public void resetarMana(int numeroRodada) {
		manaFeitico.receberMana(mana.getMana());
		mana.receberMana(numeroRodada);
	}
	
	public void receberDano(int dano) {
		vidaNexus.receberDano(dano);
	}
	
	public boolean estaVivo() {
		return vidaNexus.getVida() > 0;
	}
	
	public Carta evocarCarta(int indexCarta, boolean evocarUnidade) {
		Carta evocada = mao.get(indexCarta);
		int custo = evocada.getCusto();
		
		if (evocada instanceof Unidade && !evocarUnidade) {
			return null;
		}
		
		return comprarCarta(evocada, custo) ? mao.remove(indexCarta) : null;
	}
	
	public Carta substituirCarta(int indexMao, Carta cartaMesa) {
		Carta evocada = mao.get(indexMao);
		int custo = Math.max(evocada.getCusto() - cartaMesa.getCusto(), 0);
		
		if (comprarCarta(evocada, custo)) {
			mao.add(cartaMesa);
			return mao.remove(indexMao);
		}
		
		return null;
	}
	
	public void pegarCarta() {
		mao.add(deck.pegarCarta());
	}
	
	private boolean comprarCarta(Carta compra, int custo) {
		if (compra instanceof Feitico && mana.getMana() + manaFeitico.getMana() > custo) {
			int feitico = manaFeitico.getMana();
			manaFeitico.gastarMana(custo);
			custo -= feitico;
		}
		
		if (mana.getMana() > custo) {
			mana.gastarMana(custo);
			return true;
		}
		return false;
	}
}
