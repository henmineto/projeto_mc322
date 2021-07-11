package com.unicamp.mc322.projeto.jogadores;

import java.util.ArrayList;
import java.util.Random;

import com.unicamp.mc322.projeto.cartas.Compravel;
import com.unicamp.mc322.projeto.decks.DeckFactory;
import com.unicamp.mc322.projeto.jogo.Jogo;

public class Bot extends Jogador {
	
	private Random random;
	
	public Bot(int vida, DeckFactory deckFactory) {
		super(vida, deckFactory);
		this.random = new Random();
	}



	@Override
	public int escolherCartaParaEvocacao(boolean confirmarEscolha) {
		int index = -1;

		ArrayList<Integer> posCorrespondente = new ArrayList<>();

		for (int i = 0; i < mao.size(); i++) {
			if (getMana() >= mao.get(i).getCusto()) {
				posCorrespondente.add(Integer.valueOf(i));
			}
		}

		if (posCorrespondente.size() > 0) {
			index = posCorrespondente.get(random.nextInt(posCorrespondente.size()));
			Jogo.getInstance().mostrarMensagem("Bot evocou carta na posição: "+index+"\n");
		}
		
		return index;
	}

	@Override
	public int escolherCartaNaMao(boolean confirmarEscolha) {
		int index = -1;

		if (mao.size() > 0) {
			index = random.nextInt(mao.size());
			Jogo.getInstance().mostrarMensagem("Bot escolheu a carta na posição: "+index+"\n");
		}

		return index;
	}

	@Override
	public int escolherDescartes(boolean confirmarEscolha) {
		int index = -1;
		if (!confirmarEscolha || mao.size() > 0) {
			index = random.nextInt(mao.size());
			Jogo.getInstance().mostrarMensagem("Bot escolheu fazer " + index + " descartes.\n");
		}
		else {
			Jogo.getInstance().mostrarMensagem("Bot escolheu não descartar nenhuma carta.\n");
		}
		
		return index;
	}

	@Override
	public int escolherUnidadeParaTroca(int limite) {
		int index = -1;
		if (limite > 0) {
			index = random.nextInt(limite);
			Jogo.getInstance().mostrarMensagem("Bot escolheu substituir unidade evocada na posição: "+index+"\n");
		}
		else {
			Jogo.getInstance().mostrarMensagem("Bot escolheu não substituir nenhuma unidade evocada.\n");
		}
		
		return index;
	}

	@Override
	public int escolherUnidadeParaCampo(int limite) {
		int index = -1;
		if (limite > 0) {
			index = random.nextInt(limite);
			Jogo.getInstance().mostrarMensagem("Bot escolheu colocar em campo unidade evocada na posição: "+index+"\n");
		}
		
		return index;
	}

	@Override
	public int escolherPosicaoDefesa(int limite) {
		int index = limite > 0 ? random.nextInt(limite) : 0;
		Jogo.getInstance().mostrarMensagem("Bot escolheu colocar em campo uma defesa na posição: " + index + "\n");
		return index;
	}

	@Override
	public int escolherUnidadeParaBonus(int limite) {
		int index = limite > 0 ? random.nextInt(limite) : 0;
		Jogo.getInstance().mostrarMensagem("Efeito: Bonus para Carta Aliada ativado\r\nBot escolheu bonificar unidade na posição: "+index+"\n");
		return index;
	}
	
	@Override
	public int escolherUnidadeParaAtaque(int limite) {
		int index = limite > 0 ? random.nextInt(limite) : 0;
		Jogo.getInstance().mostrarMensagem("Efeito: Bonus contra carta Oponente ativado\r\nBot escolheu prejudicar unidade na posição: "+index+"\n");
		return index;
	}
	
	@Override
	public void exibirMensagemErro(String mensagem) {
		System.err.println(mensagem);
	}

	@Override
	public char escolherAcao() {
		int n = random.nextInt(5);
		// evocar carta, colocar carta no campo, terminar turno (respectivamente)
		final char[] escolhas = {'E', 'E', 'C', 'C', 'T'};
		char escolha = escolhas[n];
		return escolha;
	}

	@Override
	public boolean deveFazerImpressaoContinua() {
		return false;
	}
}
