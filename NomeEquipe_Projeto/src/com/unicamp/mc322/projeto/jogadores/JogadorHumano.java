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
		
		return confirmarEscolha ?
				interagirComConfirmacao(mensagemConfirmacao, mensagemPosicao) :
				interagirSemConfirmacao(mensagemPosicao);
	}

	@Override
	public int escolherUnidadeParaTroca(int limite) {
		final String mensagemConfirmacao = "Deseja trocar uma unidade?(S/N): ";
		final String mensagemPosicao = "Escolha a posicao da unidade evocada: ";

		return interagirComConfirmacao(mensagemConfirmacao, mensagemPosicao);
	}

	@Override
	public int escolherUnidadeParaCampo(int limite) {
		final String mensagemConfirmacao = "Deseja colocar uma unidade em campo?(S/N): ";
		final String mensagemPosicao = "Escolha a posicao da unidade evocada: ";
		
		return interagirComConfirmacao(mensagemConfirmacao, mensagemPosicao);
	}

	@Override
	public int escolherPosicaoDefesa(int limite) {
		final String mensagemPosicao = "Escolha a posicao da unidade evocada: ";
		
		return interagirSemConfirmacao(mensagemPosicao);
	}
	
	@Override
	public void exibirMensagemErro(String mensagem) {
		System.err.println(mensagem);
	}

	private int interagirComConfirmacao(String mensagemConfirmacao, String mensagemPosicao) {
		int index = -1;
		boolean desejaEscolher = false;

		System.out.print(mensagemConfirmacao);
		desejaEscolher = scanner.next().toUpperCase().trim().equals("S");
		
		if (desejaEscolher) {
			index = interagirSemConfirmacao(mensagemPosicao);
		}
		
		return index;
	}
	
	private int interagirSemConfirmacao(String mensagemPosicao) {
		System.out.print(mensagemPosicao);
		return scanner.nextInt();
	}
}
