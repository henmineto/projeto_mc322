package com.unicamp.mc322.projeto.jogo;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.AtivacaoEfeito;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.cartas.Unidade;
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
		MediadorEfeitos.getInstance().setMesa(this);;
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
		
		ArrayList<Unidade> unidadesEvocadas = getUnidadesEvocadas(atacante);
		
		boolean montarAtaque = true;
		while (montarAtaque) {
			try {
				int indexMesa = atacante.escolherUnidadeParaCampo(unidadesEvocadas.size());
				
				while(indexMesa >= 0 && quantidadeCartasAtaque < QTD_UNIDADES_EVOCADAS) {
					colocarCartaEmCampo(atacante, indexMesa, quantidadeCartasAtaque);
					quantidadeCartasAtaque++;
					indexMesa = atacante.escolherUnidadeParaCampo(unidadesEvocadas.size());
				}
				
				montarAtaque = false;
			}
			catch (Exception ex) {
				atacante.exibirMensagemErro(ex.getMessage());
			}
		}
		
		finalizarTurno(atacante);
	}
	
	public void realizarTurnoDefensor() {
		realizarEvocacaoCartas(defensor);
		
		realizarSubstituicaoCartas(defensor);
		
		boolean montarDefesa = quantidadeCartasAtaque > 0;
		
		ArrayList<Unidade> unidadesEvocadas = getUnidadesEvocadas(defensor);
		
		while (montarDefesa) {
			try {
				int indexMesa = defensor.escolherUnidadeParaCampo(unidadesEvocadas.size());
				
				while (indexMesa >= 0) {
					int indexDefesa = defensor.escolherPosicaoDefesa(quantidadeCartasAtaque);
					
					if (indexDefesa >= quantidadeCartasAtaque)
						throw new Exception("N�o h� carta de ataque na posi��o escolhida para defesa. Posi��o: "+indexDefesa);
					
					colocarCartaEmCampo(atacante, indexMesa, indexDefesa);
					indexMesa = defensor.escolherUnidadeParaCampo(unidadesEvocadas.size());
				}
				
				montarDefesa = false;
			}
			catch (Exception ex) {
				defensor.exibirMensagemErro(ex.getMessage());
			}
		}
		
		finalizarTurno(defensor);
	}
	
	public void realizarCombate() {
		for (int i = 0; i < unidadesEmCampoAtacante.length; i++) {
			if (unidadesEmCampoAtacante[i] != null) {
				if (validarDefesa(unidadesEmCampoAtacante[i], unidadesEmCampoDefensor[i])) {
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
			try {
				int indexMao = jogador.escolherCartaNaMao(true);
				
				if (indexMao > 0)
					evocarCarta(jogador, indexMao);

				evocarCarta = false;
			}
			catch (Exception ex) {
				jogador.exibirMensagemErro(ex.getMessage());
			}
		}
	}
	
	private void realizarSubstituicaoCartas(Jogador jogador) {
		boolean substituirCarta = true;
		
		ArrayList<Unidade> unidadesEvocadas = getUnidadesEvocadas(jogador);
		
		while (substituirCarta) {
			try {
				int indexMesa = jogador.escolherUnidadeParaTroca(unidadesEvocadas.size());
				
				while (indexMesa >= 0) {
					int indexMao = jogador.escolherCartaNaMao(false);
					substituirCarta(jogador, indexMao, indexMesa);
					indexMesa = jogador.escolherUnidadeParaTroca(unidadesEvocadas.size());
				}
				
				substituirCarta = false;
			}
			catch (Exception ex) {
				jogador.exibirMensagemErro(ex.getMessage());
			}
		}
	}
	
	private void evocarCarta(Jogador jogador, int indexMao) throws Exception {
		ArrayList<Unidade> unidadesEvocadas = getUnidadesEvocadas(jogador);
		
		Carta evocada = jogador.evocarCarta(indexMao, podeEvocarUnidade(unidadesEvocadas));
		
		evocada.ativar(AtivacaoEfeito.EVOCACAO_DA_CARTA);
		
		if(evocada instanceof Unidade) {
			unidadesEvocadas.add((Unidade)evocada);
		}
	}
	
	private void substituirCarta(Jogador jogador, int indexMao, int indexMesa) throws Exception {
		ArrayList<Unidade> unidadesEvocadas = getUnidadesEvocadas(jogador);
		
		if (indexMesa < 0 || indexMesa >= unidadesEvocadas.size())
			throw new Exception("Posi��o de carta na mesa inv�lida. Posi��o: "+indexMesa+". Quantidade de cartas: "+unidadesEvocadas.size());
		
		Carta substituida = unidadesEvocadas.get(indexMesa);
		Carta substituta = jogador.substituirCarta(indexMao, substituida);
	
		substituta.ativar(AtivacaoEfeito.EVOCACAO_DA_CARTA);
		unidadesEvocadas.remove(indexMesa);
		
		if(substituta instanceof Unidade) {
			unidadesEvocadas.add((Unidade)substituta);
		}
	}
	
	private void colocarCartaEmCampo(Jogador jogador, int indexEvocadas, int indexCampo) throws Exception {
		ArrayList<Unidade> unidadesEvocadas = getUnidadesEvocadas(jogador);
		Unidade[] unidadesEmCampo = getUnidadesEmCampo(jogador);
		
		if (indexEvocadas < 0 || indexEvocadas >= unidadesEvocadas.size())
			throw new Exception("Posi��o de carta evocada inv�lida. Posi��o: "+indexEvocadas+". Quantidade de cartas: "+unidadesEvocadas.size());
		
		if (indexCampo < 0 || indexCampo >= unidadesEmCampo.length)
			throw new Exception("Posi��o do campo inv�lida. Posi��o: "+indexCampo+". Quantidade de posi��es: "+unidadesEmCampo.length);
		
		if (unidadesEmCampo[indexCampo] != null) {
			unidadesEvocadas.add(unidadesEmCampo[indexCampo]);
		}
	
		unidadesEmCampo[indexCampo] = unidadesEvocadas.remove(indexEvocadas);
	}
	
	private void finalizarTurno(Jogador jogador) {
		ArrayList<Unidade> unidadesEvocadasOutroJogador = jogador == jogador1 ? unidadesEvocadasJogador2 : unidadesEvocadasJogador1;
		for (Unidade unidade : unidadesEvocadasOutroJogador) {
			try {
				unidade.ativar(AtivacaoEfeito.FINAL_DO_TURNO);
			}
			catch (Exception ex) {
				jogador.exibirMensagemErro(ex.getMessage());
			}
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
	
	private boolean validarDefesa(Unidade atacante, Unidade defensor) {
		if (defensor == null) //nao existe defensor nessa posicao
			return false;
		if (!((Seguidor)atacante).isElusivo()) //existe defensor e atacante � um seguidor n�o elusivo
			return true;
		
		return ((Seguidor)defensor).isElusivo(); //atacante � seguidor elusivo, s� defende se defensor for elusivo tamb�m.
	}
	
	private boolean podeEvocarUnidade(ArrayList<Unidade> unidadesEvocadas) {
		return unidadesEvocadas.size() < QTD_UNIDADES_EVOCADAS;
	}
	
	ArrayList<Unidade> getUnidadesEvocadas(Jogador jogador) {
		return jogador == jogador1 ? unidadesEvocadasJogador1 : unidadesEvocadasJogador2;
	}
	
	private Unidade[] getUnidadesEmCampo(Jogador jogador) {
		return jogador == atacante ? unidadesEmCampoAtacante : unidadesEmCampoDefensor;
	}
}