package com.unicamp.mc322.projeto.jogadores;

import java.util.Scanner;

public class JogadorHumano extends Jogador {
	private Scanner scanner;
	
	public JogadorHumano(int vida) {
		super(vida);
		this.scanner = new Scanner(System.in);
	}
	
	@Override
	public int escolherCartaNaMao(boolean confirmarEscolha) {
		final String mensagemConfirmacao = "Deseja evocar uma carta?(S/N): ";
		final String mensagemPosicao = "Escolha a posicao da carta na mao: ";
		final int maxIndex = mao.size() - 1;
		
		return confirmarEscolha ?
				interagirComConfirmacao(mensagemConfirmacao, mensagemPosicao, maxIndex) :
				interagirSemConfirmacao(mensagemPosicao, maxIndex);
	}

	@Override
	public int escolherUnidadeParaTroca(int quantidadeUnidades) {
		final String mensagemConfirmacao = "Deseja trocar uma unidade?(S/N): ";
		final String mensagemPosicao = "Escolha a posicao da unidade evocada: ";
		final int maxIndex = quantidadeUnidades - 1;
		
		return interagirComConfirmacao(mensagemConfirmacao, mensagemPosicao, maxIndex);
	}

	@Override
	public int escolherUnidadeParaCampo(int quantidadeUnidades) {
		final String mensagemConfirmacao = "Deseja colocar uma unidade em campo?(S/N): ";
		final String mensagemPosicao = "Escolha a posicao da unidade evocada: ";
		final int maxIndex = quantidadeUnidades - 1;
		
		return interagirComConfirmacao(mensagemConfirmacao, mensagemPosicao, maxIndex);
	}

	@Override
	public int escolherPosicaoDefesa(int quantidadeAtaque) {
		final String mensagemPosicao = "Escolha a posicao da unidade evocada: ";
		final int maxIndex = quantidadeAtaque - 1;
		
		return interagirSemConfirmacao(mensagemPosicao, maxIndex);
	}

	private int interagirComConfirmacao(String mensagemConfirmacao, String mensagemPosicao, int maxIndex) {
		int index = -1;
		boolean desejaEscolher = false;
		
		do {
			System.out.print(mensagemConfirmacao);
			desejaEscolher = scanner.next().toUpperCase().trim().equals("S");
			if (desejaEscolher) {
				System.out.print(mensagemPosicao);
				index = scanner.nextInt();
			}
		} while (desejaEscolher && index < 0 && index > maxIndex);
		
		return index;
	}
	
	private int interagirSemConfirmacao(String mensagemPosicao, int maxIndex) {
		int index = -1;
		
		do {
			System.out.print(mensagemPosicao);
			index = scanner.nextInt();
		} while (index < 0 && index > maxIndex);
		
		return index;
	}
}
