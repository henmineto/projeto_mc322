package com.unicamp.mc322.projeto.jogadores;

import java.util.Random;

import com.unicamp.mc322.projeto.decks.DeckFactory;

public class Bot extends Jogador {
	
	private Random random;
	
	public Bot(int vida, DeckFactory deckFactory) {
		super(vida, deckFactory);
		this.random = new Random();
	}

	@Override
	public int escolherCartaNaMao(boolean confirmarEscolha) {
		int index = -1;
		if (!confirmarEscolha || random.nextBoolean()) {
			index = random.nextInt(mao.size());
			System.out.println("Bot escolheu evocar a carta na posição: "+index);
		}
		else {
			System.out.println("Bot escolheu não evocar nenhuma carta.");
		}
		
		return index;
	}
	
	@Override
	public int escolherDescartes(boolean confirmarEscolha) {
		int index = -1;
		if (!confirmarEscolha || random.nextBoolean()) {
			index = random.nextInt(mao.size());
			System.out.println("Bot escolheu fazer " + index + " descartes.");
		}
		else {
			System.out.println("Bot escolheu não descartar nenhuma carta.");
		}
		
		return index;
	}

	@Override
	public int escolherUnidadeParaTroca(int limite) {
		int index = -1;
		if (random.nextBoolean()) {
			index = random.nextInt(limite);
			System.out.println("Bot escolheu substituir unidade evocada na posição: "+index);
		}
		else {
			System.out.println("Bot escolheu não substituir nenhuma unidade evocada.");
		}
		
		return index;
	}

	@Override
	public int escolherUnidadeParaCampo(int limite) {
		int index = -1;
		if (random.nextBoolean()) {
			index = random.nextInt(limite);
			System.out.println("Bot escolheu colocar em campo unidade evocada na posição: "+index);
		}
		else {
			System.out.println("Bot escolheu não colocar nenhuma unidade evocada em campo.");
		}
		
		return index;
	}

	@Override
	public int escolherPosicaoDefesa(int limite) {
		int index = random.nextInt(limite);
		System.out.println("Bot escolheu colocar em campo uma defesa na posição: "+index);
		return index;
	}

	@Override
	public int escolherUnidadeParaBonus(int limite) {
		int index = random.nextInt(limite);
		System.out.println("Efeito: Bonus para Carta Aliada ativado\r\nBot escolheu bonificar unidade na posição: "+index);
		return index;
	}
	
	@Override
	public int escolherUnidadeParaAtaque(int limite) {
		int index = random.nextInt(limite);
		System.out.println("Efeito: Bonus contra carta Oponente ativado\\r\\nBot escolheu prejudicar unidade na posição: "+index);
		return index;
	}
	
	@Override
	public void exibirMensagemErro(String mensagem) {
		System.err.println(mensagem);
	}
}
