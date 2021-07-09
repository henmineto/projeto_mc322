package com.unicamp.mc322.projeto.jogadores;

import java.util.Random;

public class Bot extends Jogador {
	
	private Random random;
	
	public Bot(int vida) {
		super(vida);
		this.random = new Random();
	}

	@Override
	public int escolherCartaNaMao(boolean confirmarEscolha) {
		int index = -1;
		if (!confirmarEscolha || random.nextBoolean()) {
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
		if (!confirmarEscolha || random.nextBoolean()) {
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
		if (random.nextBoolean()) {
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
		if (random.nextBoolean()) {
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
		int index = random.nextInt(limite);
		System.out.println("Bot escolheu colocar em campo uma defesa na posi��o: "+index);
		return index;
	}

	@Override
	public int escolherUnidadeParaBonus(int limite) {
		int index = random.nextInt(limite);
		System.out.println("Efeito: Bonus para Carta Aliada ativado\r\nBot escolheu bonificar unidade na posi��o: "+index);
		return index;
	}
	
	@Override
	public void exibirMensagemErro(String mensagem) {
		System.err.println(mensagem);
	}
}
