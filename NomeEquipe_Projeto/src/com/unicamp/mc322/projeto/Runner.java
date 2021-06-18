package com.unicamp.mc322.projeto;

import com.unicamp.mc322.projeto.jogadores.*;
import com.unicamp.mc322.projeto.jogo.Mesa;

public class Runner {
	
	private void start() {
		Jogador j1 = new JogadorHumano(100);
		Jogador j2 = new Bot(100);
		Mesa mesa = new Mesa(j1, j2);
		
		while (j1.estaVivo() && j2.estaVivo()) {
			mesa.iniciarRodada();
			mesa.realizarTurnoAtacante();
			mesa.realizarTurnoDefensor();
			mesa.realizarCombate();
		}
	}

	public static void main(String[] args) {
		new Runner().start();
	}

}
