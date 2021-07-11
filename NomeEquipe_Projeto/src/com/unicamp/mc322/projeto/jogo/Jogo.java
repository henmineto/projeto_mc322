package com.unicamp.mc322.projeto.jogo;

import com.unicamp.mc322.projeto.jogadores.*;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.Evocavel;
import com.unicamp.mc322.projeto.decks.DeckDemaciaFactory;
import com.unicamp.mc322.projeto.jogo.Mesa;

public class Jogo {
	private Mesa mesa;
	private boolean finalizarPrograma = false;
	private final int QTD_CARTAS_INICIAIS = 4;
	private final int QTD_UNIDADES_EVOCADAS = 6;
	
	private void start() {
		Jogador jogador1 = new JogadorHumano(100, new DeckDemaciaFactory());
		Jogador jogador2 = new Bot(100, new DeckDemaciaFactory());
		this.mesa = new Mesa(jogador1, jogador2);
		
		char acao = 0; // armazena as ações escolhidas pelos jogadores
		while (jogador1.estaVivo() && jogador2.estaVivo() && acao != 'F') {
			mesa.iniciarRodada();
			
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
		case 'E': // evocar carta na mão
			mesa.realizarEvocacaoCartas(j);
			return true;
		case 'C': // colocar carta no campo de combate
			mesa.addUnidadeCampo(j);
			return true;
		case 'T': // terminar turno (ataque/defesa montado)
			return false;
		case 'F': // finalizar jogo (opção não disponível para o bot)
			return false;
		default:
			return true; // caso o caractere seja inválido, repetir
		}
	}
	
	private void imprimeMesa() {
		System.out.println(this.mesa);
	}

	public static void main(String[] args) {
		new Jogo().start();
	}
}