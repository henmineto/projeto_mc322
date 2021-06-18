package com.unicamp.mc322.projeto.jogadores;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Feitico;
import com.unicamp.mc322.projeto.cartas.Unidade;
import com.unicamp.mc322.projeto.decks.Deck;
import com.unicamp.mc322.projeto.modulos.ModuloMana;
import com.unicamp.mc322.projeto.modulos.ModuloVida;

public abstract class Jogador {
	private final int MAX_MANA_RODADA = 10;
	private final int MAX_MANA_FEITICO = 3;
	
	protected ArrayList<Carta> mao;
	private Deck deck;
	private ModuloMana mana;
	private ModuloMana manaFeitico;
	private ModuloVida vidaNexus;
	
	protected Jogador(int vida) {
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
	
	public Carta evocarCarta(int indexCarta, boolean evocarUnidade) throws Exception {
		if (indexCarta < 0 || indexCarta >= mao.size())
			throw new Exception("Posição de carta na mão inválida. Posição: "+indexCarta+". Quantidade de cartas: "+mao.size());
			
		Carta evocada = mao.get(indexCarta);
		int custo = evocada.getCusto();
		
		if (evocada instanceof Unidade && !evocarUnidade)
			throw new Exception("Carta do tipo Unidade não pode ser evocada no momento.");
		
		comprarCarta(evocada, custo);
		
		return mao.remove(indexCarta);
	}
	
	public Carta substituirCarta(int indexMao, Carta cartaMesa) throws Exception {
		if (indexMao < 0 || indexMao >= mao.size())
			throw new Exception("Posição de carta na mão inválida. Posição: "+indexMao+". Quantidade de cartas: "+mao.size());
			
		Carta evocada = mao.get(indexMao);
		int custo = Math.max(evocada.getCusto() - cartaMesa.getCusto(), 0);
		
		comprarCarta(evocada, custo);
		
		mao.add(cartaMesa);
		return mao.remove(indexMao);
	}
	
	public void pegarCarta() {
		mao.add(deck.pegarCarta());
	}
	
	public abstract int escolherCartaNaMao(boolean confirmarEscolha);
	
	public abstract int escolherUnidadeParaTroca(int limite);
	
	public abstract int escolherUnidadeParaCampo(int limite);
	
	public abstract int escolherPosicaoDefesa(int limite);
	
	public abstract void exibirMensagemErro(String mensagem);
	
	private void comprarCarta(Carta compra, int custo) throws Exception {
		if (compra instanceof Feitico) {
			int manaDisponivel = mana.getMana() + manaFeitico.getMana();
			
			if (manaDisponivel > custo)
				throw new Exception("Quantidade de mana insuficiente para ativar carta. Disponível: "+manaDisponivel+". Exigido: "+custo);
			
			int feitico = manaFeitico.getMana();
			manaFeitico.gastarMana(custo);
			custo -= feitico;
		}
		
		if (mana.getMana() < custo) {
			throw new Exception("Quantidade de mana insuficiente para ativar carta. Disponível: "+mana.getMana()+". Exigido: "+custo);
		}
		
		mana.gastarMana(custo);
	}
}
