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
	private Jogador defensor;
	private int quantidadeRodadas;
	
	private ArrayList<Unidade> unidadesEvocadasJogador1;
	private ArrayList<Unidade> unidadesEvocadasJogador2;
	
	private Unidade[] unidadesEmCampoAtacante;
	private Unidade[] unidadesEmCampoDefensor;
	private int quantidadeCartasAtaque;
	
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
			defensor = jogador2;
		}
		else {
			atacante = jogador2;
			defensor = jogador1;
		}
		
		jogador1.pegarCarta();
		jogador2.pegarCarta();
		
		jogador1.resetarMana(quantidadeRodadas);
		jogador2.resetarMana(quantidadeRodadas);
	}
	
	public void realizarTurnoAtacante() {
		
		realizarEvocacaoCartas(atacante);
		
		realizarSubstituicaoCartas(atacante);
		
		boolean montarAtaque = true;
		while (montarAtaque) {
			ArrayList<Unidade> unidadesEvocadas = getUnidadesEvocadas(atacante);
			int indexMesa = atacante.escolherUnidadeParaCampo(unidadesEvocadas.size());
			
			for (; indexMesa >= 0; indexMesa = atacante.escolherUnidadeParaCampo(unidadesEvocadas.size())) {
				if (colocarCartaEmCampo(atacante, indexMesa, quantidadeCartasAtaque))
					quantidadeCartasAtaque++;
			}
			
			if (indexMesa < 0)
				montarAtaque = false;
		}
		
		finalizarTurno(atacante);
	}
	
	public void realizarTurnoDefensor() {
		realizarEvocacaoCartas(defensor);
		
		realizarSubstituicaoCartas(defensor);
		
		boolean montarDefesa = verificarAtaqueMontado();
		while (montarDefesa) {
			ArrayList<Unidade> unidadesEvocadas = getUnidadesEvocadas(defensor);
			int indexMesa = defensor.escolherUnidadeParaCampo(unidadesEvocadas.size());
			
			for (; indexMesa >= 0; indexMesa = defensor.escolherUnidadeParaCampo(unidadesEvocadas.size())) {
				int indexDefesa = defensor.escolherPosicaoDefesa(quantidadeCartasAtaque);
				colocarCartaEmCampo(atacante, indexMesa, indexDefesa);
			}
			
			if (indexMesa < 0)
				montarDefesa = false;
		}
		
		finalizarTurno(defensor);
	}
	
	public void realizarCombate() {
		for (int i = 0; i < unidadesEmCampoAtacante.length; i++) {
			if (unidadesEmCampoAtacante[i] != null) {
				if (unidadesEmCampoDefensor[i]!= null) {
					unidadesEmCampoAtacante[i].atacar(unidadesEmCampoDefensor[i]);
				}
				else {
					unidadesEmCampoAtacante[i].atacar(defensor);
				}
			}
		}
		
		removerCartasEmCampo(jogador1);
		removerCartasEmCampo(jogador2);
		quantidadeCartasAtaque = 0;
	}
	
	private void realizarEvocacaoCartas(Jogador jogador) {
		boolean evocarCarta = true;
		while (evocarCarta) {
			int indexMao = jogador.escolherCartaNaMao(true);
			
			if (indexMao > 0)
				evocarCarta = !evocarCarta(jogador, indexMao);
			else
				evocarCarta = false;
		}
	}
	
	private void realizarSubstituicaoCartas(Jogador jogador) {
		boolean substituirCarta = true;
		int quantidadeUnidades = getUnidadesEvocadas(jogador).size();
		
		while (substituirCarta) {
			int indexMesa = jogador.escolherUnidadeParaTroca(quantidadeUnidades);
			
			for (; indexMesa >= 0; indexMesa = jogador.escolherUnidadeParaTroca(quantidadeUnidades)) {
				int indexMao = jogador.escolherCartaNaMao(false);
				substituirCarta(jogador, indexMao, indexMesa);
			}
			
			if (indexMesa < 0)
				substituirCarta = false;
		}
	}
	
	private boolean evocarCarta(Jogador jogador, int indexMao) {
		ArrayList<Unidade> unidadesEvocadas = getUnidadesEvocadas(jogador);
		
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
	
	private void substituirCarta(Jogador jogador, int indexMao, int indexMesa) {
		ArrayList<Unidade> unidadesEvocadas = getUnidadesEvocadas(jogador);
		
		Carta substituida = unidadesEvocadas.get(indexMesa);
		Carta substituta = jogador.substituirCarta(indexMao, substituida);
		
		if (substituta != null) {
			substituta.ativar(AtivacaoEfeito.EVOCACAO_DA_CARTA);
			unidadesEvocadas.remove(indexMesa);
			
			if(substituta instanceof Unidade) {
				unidadesEvocadas.add((Unidade)substituta);
			}
		}
	}
	
	private boolean colocarCartaEmCampo(Jogador jogador, int indexEvocadas, int indexCampo) {
		ArrayList<Unidade> unidadesEvocadas = getUnidadesEvocadas(jogador);
		Unidade[] unidadesEmCampo = getUnidadesEmCampo(jogador);
		
		if (unidadesEmCampo[indexCampo] == null) {
			unidadesEmCampo[indexCampo] = unidadesEvocadas.remove(indexEvocadas);
			return true;
		}
		return false;
	}
	
	private void finalizarTurno(Jogador jogador) {
		ArrayList<Unidade> unidadesEvocadasOutroJogador = jogador == jogador1 ? unidadesEvocadasJogador2 : unidadesEvocadasJogador1;
		for (Unidade unidade : unidadesEvocadasOutroJogador) {
			unidade.ativar(AtivacaoEfeito.FINAL_DO_TURNO);
		}
	}

	private void removerCartasEmCampo(Jogador jogador) {
		ArrayList<Unidade> unidadesEvocadas = getUnidadesEvocadas(jogador);
		Unidade[] unidadesEmCampo = getUnidadesEmCampo(jogador);
		
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
	
	private boolean verificarAtaqueMontado() {
		boolean ataqueMontado = false;
		for (int i = 0; i < unidadesEmCampoAtacante.length && !ataqueMontado; i++) {
			ataqueMontado = unidadesEmCampoAtacante[i] != null;
		}
		return ataqueMontado;
	}
	
	private ArrayList<Unidade> getUnidadesEvocadas(Jogador jogador) {
		return jogador == jogador1 ? unidadesEvocadasJogador1 : unidadesEvocadasJogador2;
	}
	
	private Unidade[] getUnidadesEmCampo(Jogador jogador) {
		return jogador == atacante ? unidadesEmCampoAtacante : unidadesEmCampoDefensor;
	}
}
