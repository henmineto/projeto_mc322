package com.unicamp.mc322.projeto.jogo;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Unidade;
import com.unicamp.mc322.projeto.cartas.efeitos.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;

public class Mesa {
	private final int QTD_UNIDADES_EVOCADAS = 6;

	private Jogador jogador1;
	private Jogador jogador2;
	
	private Jogador atacante;
	private int quantidadeRodadas;
	
	private ArrayList<Unidade> unidadesEvocadasJogador1;
	private ArrayList<Unidade> unidadesEvocadasJogador2;
	
	private Unidade[] unidadesEmCampoAtacante;
	private Unidade[] unidadesEmCampoDefensor;
	
	public Mesa(Jogador jogador1, Jogador jogador2) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.unidadesEvocadasJogador1 = new ArrayList<>(QTD_UNIDADES_EVOCADAS);
		this.unidadesEvocadasJogador2 = new ArrayList<>(QTD_UNIDADES_EVOCADAS);
		this.unidadesEmCampoAtacante = new Unidade[QTD_UNIDADES_EVOCADAS];
		this.unidadesEmCampoDefensor = new Unidade[QTD_UNIDADES_EVOCADAS];
	}
	
	public void iniciarRodada() {
		quantidadeRodadas++;
		
		if (atacante == null || atacante == jogador2) {
			atacante = jogador1;
		}
		else {
			atacante = jogador2;
		}
		
		jogador1.pegarCarta();
		jogador2.pegarCarta();
		
		jogador1.resetarMana(quantidadeRodadas);
		jogador2.resetarMana(quantidadeRodadas);
	}
	
	public boolean evocarCarta(Jogador jogador, int indexMao) {
		ArrayList<Unidade> unidadesEvocadas = jogador == jogador1 ? unidadesEvocadasJogador1 : unidadesEvocadasJogador2;
		
		Carta evocada = jogador.evocarCarta(indexMao, podeEvocarUnidade(unidadesEvocadas));
		if (evocada != null) {
			evocada.ativar(AtivacaoEfeito.EVOCACAO_DA_CARTA);
			
			if(evocada instanceof Unidade) {
				unidadesEvocadas.add((Unidade)evocada);
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean substituirCarta(Jogador jogador, int indexMao, int indexMesa) {
		ArrayList<Unidade> unidadesEvocadas = jogador == jogador1 ? unidadesEvocadasJogador1 : unidadesEvocadasJogador2;
		
		Carta substituida = unidadesEvocadas.get(indexMesa);
		Carta substituta = jogador.substituirCarta(indexMao, substituida);
		
		if (substituta != null) {
			substituta.ativar(AtivacaoEfeito.EVOCACAO_DA_CARTA);
			unidadesEvocadas.remove(indexMesa);
			
			if(substituta instanceof Unidade) {
				unidadesEvocadas.add((Unidade)substituta);
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean colocarCartaEmCampo(Jogador jogador, int indexEvocadas, int indexCampo) {
		ArrayList<Unidade> unidadesEvocadas = jogador == jogador1 ? unidadesEvocadasJogador1 : unidadesEvocadasJogador2;
		Unidade[] unidadesEmCampo = jogador == atacante ? unidadesEmCampoAtacante : unidadesEmCampoDefensor;
		
		if (unidadesEmCampo[indexCampo] == null) {
			unidadesEmCampo[indexCampo] = unidadesEvocadas.remove(indexEvocadas);
			return true;
		}
		return false;
	}
	
	public void realizarCombate() {
		for (int i = 0; i < unidadesEmCampoAtacante.length; i++) {
			if (unidadesEmCampoAtacante[i] != null) {
				if (unidadesEmCampoDefensor[i]!= null) {
					unidadesEmCampoAtacante[i].atacar(unidadesEmCampoDefensor[i]);
				}
				else {
					unidadesEmCampoAtacante[i].atacar(atacante == jogador1 ? jogador2 : jogador1);
				}
			}
		}
		
		removerCartasEmCampo(jogador1);
		removerCartasEmCampo(jogador2);
	}
	
	public void finalizarTurno(Jogador jogador) {
		ArrayList<Unidade> unidadesEvocadasOutroJogador = jogador == jogador1 ? unidadesEvocadasJogador2 : unidadesEvocadasJogador1;
		for (Unidade unidade : unidadesEvocadasOutroJogador) {
			unidade.ativar(AtivacaoEfeito.FINAL_DO_TURNO);
		}
	}

	private void removerCartasEmCampo(Jogador jogador) {
		ArrayList<Unidade> unidadesEvocadas = jogador == jogador1 ? unidadesEvocadasJogador1 : unidadesEvocadasJogador2;
		Unidade[] unidadesEmCampo = jogador == atacante ? unidadesEmCampoAtacante : unidadesEmCampoDefensor;
		
		for (int i = 0; i < unidadesEmCampo.length; i++) {
			if (unidadesEmCampo[i] != null) {
				if (unidadesEmCampo[i].estaVivo()) {
					unidadesEvocadas.add(unidadesEmCampo[i]);
				}
				unidadesEmCampo[i] = null;
			}
		}
	}
	
	private boolean podeEvocarUnidade(ArrayList<Unidade> unidadesEvocadas) {
		return unidadesEvocadas.size() < QTD_UNIDADES_EVOCADAS;
	}
}
