package com.unicamp.mc322.projeto.jogadores;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.Atacavel;
import com.unicamp.mc322.projeto.cartas.Compravel;
import com.unicamp.mc322.projeto.cartas.Feitico;
import com.unicamp.mc322.projeto.cartas.Evocavel;
import com.unicamp.mc322.projeto.decks.Deck;
import com.unicamp.mc322.projeto.decks.DeckFactory;
import com.unicamp.mc322.projeto.modulos.ModuloMana;
import com.unicamp.mc322.projeto.modulos.ModuloVida;

public abstract class Jogador implements Atacavel {
	private final int MAX_MANA_RODADA = 10;
	private final int MAX_MANA_FEITICO = 3;
	
	protected ArrayList<Compravel> mao;
	private Deck deck;
	private ModuloMana mana;
	private ModuloMana manaFeitico;
	private ModuloVida vidaNexus;
	
	protected Jogador(int vida, DeckFactory deckFactory) {
		this.mana = new ModuloMana(MAX_MANA_RODADA);
		this.manaFeitico = new ModuloMana(MAX_MANA_FEITICO);
		this.vidaNexus = new ModuloVida(vida);
		this.mao = new ArrayList<Compravel>();
		this.deck = deckFactory.gerarDeck();
	}
	
	public void resetarMana(int numeroRodada) {
		manaFeitico.receberMana(mana.getMana());
		mana.receberMana(numeroRodada);
	}
	
	public void receberDano(int dano) {
		vidaNexus.receberDano(dano);
	}
	
	public int getAtaque() {
		return 0;
	}
	
	public int getVida() {
		return vidaNexus.getVida();
	}
	
	public boolean estaVivo() {
		return vidaNexus.getVida() > 0;
	}
	
	public Compravel evocarCarta(int indexCarta, boolean evocarUnidade) throws Exception {
		if (indexCarta < 0 || indexCarta >= mao.size())
			throw new Exception("Posição de carta na mão inválida. Posição: "+indexCarta+". Quantidade de cartas: "+mao.size());
			
		Compravel evocada = mao.get(indexCarta);
		int custo = evocada.getCusto();
		
		if (evocada instanceof Evocavel && !evocarUnidade)
			throw new Exception("Carta do tipo Unidade não pode ser evocada no momento.");
		
		comprarCarta(evocada, custo);
		
		return mao.remove(indexCarta);
	}
	
	public Compravel substituirCarta(int indexMao, Evocavel cartaMesa) throws Exception {
		if (indexMao < 0 || indexMao >= mao.size())
			throw new Exception("Posição de carta na mão inválida. Posição: "+indexMao+". Quantidade de cartas: "+mao.size());
		
		return mao.remove(indexMao);
	}
	
	public void pegarCarta() {
		mao.add(deck.pegarCarta());
	}
	
	public boolean descartaCarta() {
		int index = escolherCartaNaMao(true);
		try {
			mao.remove(index);
			return true;
		}
		catch (Exception ex) {
			exibirMensagemErro(ex.getMessage());
		}
		return false;
	}
	
	public abstract int escolherDescartes(boolean confirmarEscolha);
	
	public abstract int escolherCartaNaMao(boolean confirmarEscolha);
	
	public abstract int escolherUnidadeParaTroca(int limite);
	
	public abstract int escolherUnidadeParaCampo(int limite);
	
	public abstract int escolherPosicaoDefesa(int limite);
	
	public abstract int escolherUnidadeParaBonus(int limite);
	
	public abstract int escolherUnidadeParaAtaque(int limite);
	
	public abstract void exibirMensagemErro(String mensagem);
	
	private void comprarCarta(Compravel compra, int custo) throws Exception {
		if (compra instanceof Feitico) {
			int manaDisponivel = mana.getMana() + manaFeitico.getMana();
			
			if (manaDisponivel < custo)
				throw new Exception("Quantidade de mana insuficiente para evocar carta. Disponível: "+manaDisponivel+". Exigido: "+custo);
			
			int feitico = manaFeitico.getMana();
			manaFeitico.gastarMana(custo);
			custo -= feitico;
		}
		
		if (mana.getMana() < custo) {
			throw new Exception("Quantidade de mana insuficiente para evocar carta. Disponível: "+mana.getMana()+". Exigido: "+custo);
		}
		
		mana.gastarMana(custo);
	}
}
