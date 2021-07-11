package com.unicamp.mc322.projeto.jogo;

import com.unicamp.mc322.projeto.jogadores.*;

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
		Jogador jogador1 = new JogadorHumano(20, new DeckDemaciaFactory());
		Jogador jogador2 = new Bot(20, new DeckDemaciaFactory());
		this.mesa = new Mesa(jogador1, jogador2);
		
		char acao = 0; // armazena as ações escolhidas pelos jogadores
		while (jogador1.estaVivo() && jogador2.estaVivo() && acao != 'F') {
			mesa.iniciarRodada();

			imprimeMesa();
			
			mesa.realizarTurnoAtacante();
			
			imprimeMesa();
			
			Jogador atacante = mesa.getAtacante();
			acao = atacante.escolherAcao();
			
			while (realizaAcao(atacante, acao)) {
				acao = atacante.escolherAcao();
				imprimeMesa();
			}
			
			mesa.finalizarTurno(atacante);
			
			
			// defensor
			mesa.realizarTurnoDefensor();

			imprimeMesa();
			
			Jogador defensor = mesa.getDefensor();
			acao = defensor.escolherAcao();
			
			while (realizaAcao(atacante, acao)) {
				acao = atacante.escolherAcao();
				imprimeMesa();
			}
			
			mesa.finalizarTurno(defensor);
			
			mesa.realizarCombate();
			
			imprimeMesa();
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

	public void imprimeMesa() {
		System.out.println(this.mesa);
	}

	public static void main(String[] args) {
		new Jogo().start();
	}
}