package com.unicamp.mc322.projeto.jogo;

import com.unicamp.mc322.projeto.jogadores.*;

import java.io.IOException;

import com.unicamp.mc322.projeto.decks.DeckDemaciaFactory;

public class Jogo {
	private Mesa mesa;

	private static Jogo singleton;

	private Jogo() {
		singleton = this;
	}

	public static Jogo getInstance() {
		if (singleton == null) {
			singleton = new Jogo();
		}

		return singleton;
	}
	
	private void start() {
		Jogador jogador1 = new Bot(20, new DeckDemaciaFactory());
		Jogador jogador2 = new Bot(20, new DeckDemaciaFactory());
		this.mesa = new Mesa(jogador1, jogador2);
		
		char acao = 0; // armazena as ações escolhidas pelos jogadores
		boolean primeiraJogada = true;
		while (jogador1.estaVivo() && jogador2.estaVivo()) {
			mesa.iniciarRodada();

			if (primeiraJogada) {
				imprimeMesa();
				primeiraJogada = false;
			}

			mostrarMensagem("Turno do atacante\n");
			Jogador atacante = mesa.getAtacante();
			
			mesa.iniciarTurno(atacante);

			imprimeMesa();

			acao = atacante.escolherAcao();
			
			while (realizaAcao(atacante, acao)) {
				acao = atacante.escolherAcao();
				if (atacante.deveFazerImpressaoContinua()) {
					impressaoCondicional(atacante);
				}

			}
			
			mesa.finalizarTurno(atacante);
			
			
			// defensor
			mostrarMensagem("Turno do defensor\n");
			Jogador defensor = mesa.getDefensor();
			
			mesa.iniciarTurno(defensor);

			imprimeMesa();

			acao = defensor.escolherAcao();
			
			while (realizaAcao(defensor, acao)) {
				acao = defensor.escolherAcao();
				impressaoCondicional(defensor);
			}
			
			mesa.finalizarTurno(defensor);
			
			mesa.realizarCombate();
		}
		
		imprimeMesa();
		
		if (jogador1.estaVivo()) {
			mostrarMensagem("Jogador 1 venceu!\n");
		}
		else if(jogador2.estaVivo()) {
			mostrarMensagem("Jogador 2 venceu!\n");
		}
		else {
			mostrarMensagem("Houve um empate!\n");
		}
	}
	
	private boolean realizaAcao(Jogador j, char c) {
		switch(c) {
			case 'I': // Pegar info carta
				System.out.println(mesa.getInfoCarta(j));
				return true;
			case 'E': // evocar carta na mão
				mesa.realizarEvocacaoCartas(j);
				if (j.deveFazerImpressaoContinua()) {
					imprimeMesa();
				}
				return true;
			case 'C': // colocar carta no campo de combate
				mesa.addUnidadeCampo(j);
				if (j.deveFazerImpressaoContinua()) {
					imprimeMesa();
				}
				return true;
			case 'T': // terminar turno (ataque/defesa montado)
				return false;
			case 'F': // finalizar jogo (opção não disponível para o bot)
				System.exit(0);
				return false;
			default:
				return true; // caso o caractere seja inválido, repetir
		}
	}

	public void mostrarMensagem(String mensagem) {
		System.out.print(mensagem);
	}

	public void impressaoCondicional(Jogador j) {
		if (j.deveFazerImpressaoContinua()) {
			imprimeMesa();
		}
	}

	public void imprimeMesa() {
		System.out.println("########################################################################");
		System.out.println(this.mesa);
		System.out.println("########################################################################");
	}

	public static void main(String[] args) {
		new Jogo().start();
	}
}