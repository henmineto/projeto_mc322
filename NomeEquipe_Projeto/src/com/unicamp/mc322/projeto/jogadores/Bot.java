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
		if (!confirmarEscolha || (mao.size() > 0 && random.nextBoolean())) {
			index = random.nextInt(mao.size());
			System.out.println("Bot escolheu evocar a carta na posi��o: "+index);
		}
		else {
			System.out.println("Bot escolheu n�o evocar nenhuma carta.");
		}
		
		return index;
	}
	
	@Override
	public int escolherDescartes(boolean confirmarEscolha) {
		int index = -1;
		if (!confirmarEscolha || (mao.size() > 0 && random.nextBoolean())) {
			index = random.nextInt(mao.size());
			System.out.println("Bot escolheu fazer " + index + " descartes.");
		}
		else {
			System.out.println("Bot escolheu n�o descartar nenhuma carta.");
		}
		
		return index;
	}

	@Override
	public int escolherUnidadeParaTroca(int limite) {
		int index = -1;
		if (limite > 0 && random.nextBoolean()) {
			index = random.nextInt(limite);
			System.out.println("Bot escolheu substituir unidade evocada na posi��o: "+index);
		}
		else {
			System.out.println("Bot escolheu n�o substituir nenhuma unidade evocada.");
		}
		
		return index;
	}

	@Override
	public int escolherUnidadeParaCampo(int limite) {
		int index = -1;
		if (limite > 0 && random.nextBoolean()) {
			index = random.nextInt(limite);
			System.out.println("Bot escolheu colocar em campo unidade evocada na posi��o: "+index);
		}
		else {
			System.out.println("Bot escolheu n�o colocar nenhuma unidade evocada em campo.");
		}
		
		return index;
	}

	@Override
	public int escolherPosicaoDefesa(int limite) {
		int index = limite > 0 ? random.nextInt(limite) : 0;
		System.out.println("Bot escolheu colocar em campo uma defesa na posi��o: "+index);
		return index;
	}

	@Override
	public int escolherUnidadeParaBonus(int limite) {
		int index = limite > 0 ? random.nextInt(limite) : 0;
		System.out.println("Efeito: Bonus para Carta Aliada ativado\r\nBot escolheu bonificar unidade na posi��o: "+index);
		return index;
	}
	
	@Override
	public int escolherUnidadeParaAtaque(int limite) {
		int index = limite > 0 ? random.nextInt(limite) : 0;
		System.out.println("Efeito: Bonus contra carta Oponente ativado\\r\\nBot escolheu prejudicar unidade na posi��o: "+index);
		return index;
	}
	
	@Override
	public void exibirMensagemErro(String mensagem) {
		System.err.println(mensagem);
	}

	@Override
	public char escolherAcao() {
		int n = random.nextInt(9);
		// evocar carta, colocar carta no campo, terminar turno (respectivamente)
		final char[] escolhas = {'E', 'E', 'E', 'E', 'C', 'C', 'C', 'C', 'T'};
		char escolha = escolhas[n];
		return escolha;
	}

	@Override
	public boolean deveFazerImpressaoContinua() {
		return false;
	}
}
