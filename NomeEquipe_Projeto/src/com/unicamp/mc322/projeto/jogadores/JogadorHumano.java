package com.unicamp.mc322.projeto.jogadores;

import java.util.Scanner;

import com.unicamp.mc322.projeto.decks.DeckFactory;

public class JogadorHumano extends Jogador {
	private Scanner scanner;
	
	public JogadorHumano(int vida, DeckFactory deckFactory) {
		super(vida, deckFactory);
		this.scanner = new Scanner(System.in);
	}
	
	@Override
	public int escolherCartaNaMao(boolean confirmarEscolha) {
		final String mensagemConfirmacao = "Deseja escolher uma carta?(S/N): "; // generaliza para o caso onde o jogador não irá evocar uma carta
		final String mensagemPosicao = "Escolha a posicao da carta na mao: ";
		
		return confirmarEscolha ?
				interagirComConfirmacao(mensagemConfirmacao, mensagemPosicao) :
				interagirSemConfirmacao(mensagemPosicao);
	}
	
	@Override
	public int escolherDescartes(boolean confirmarEscolha) {
		final String mensagemConfirmacao = "Deseja descartar cartas?(S/N): ";
		final String mensagemQuantidade = "Deseja descartar quantas cartas?";
		
		return confirmarEscolha ?
				interagirComConfirmacao(mensagemConfirmacao, mensagemQuantidade) :
				interagirSemConfirmacao(mensagemQuantidade);
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
		return interagirSemConfirmacao("Escolha a posicao da unidade evocada: ");
	}
	
	@Override
	public int escolherUnidadeParaBonus(int limite) {
		return interagirSemConfirmacao("Efeito: Bonus para Carta Aliada ativado\r\nEscolha a posicao da unidade a ser bonificada: ");
	}
	
	@Override
	public int escolherUnidadeParaAtaque(int limite) {
		return interagirSemConfirmacao("Efeito: Bonus contra carta Oponente ativado\r\nEscolha a posicao da unidade a ser prejudicada: ");
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
