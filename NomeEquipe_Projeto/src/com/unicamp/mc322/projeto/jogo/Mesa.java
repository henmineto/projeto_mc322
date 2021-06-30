package com.unicamp.mc322.projeto.jogo;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.Compravel;
import com.unicamp.mc322.projeto.cartas.Evocavel;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;

public class Mesa {
	private final int QTD_UNIDADES_EVOCADAS = 6;

	private Jogador jogador1;
	private Jogador jogador2;
	
	private Jogador atacante;
	private Jogador defensor;
	private int quantidadeRodadas;
	
	private ArrayList<Evocavel> unidadesEvocadasJogador1;
	private ArrayList<Evocavel> unidadesEvocadasJogador2;
	
	private Evocavel[] unidadesEmCampoAtacante;
	private Evocavel[] unidadesEmCampoDefensor;
	private int quantidadeCartasAtaque;
	
	public Mesa(Jogador jogador1, Jogador jogador2) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.unidadesEvocadasJogador1 = new ArrayList<>(QTD_UNIDADES_EVOCADAS);
		this.unidadesEvocadasJogador2 = new ArrayList<>(QTD_UNIDADES_EVOCADAS);
		this.unidadesEmCampoAtacante = new Evocavel[QTD_UNIDADES_EVOCADAS];
		this.unidadesEmCampoDefensor = new Evocavel[QTD_UNIDADES_EVOCADAS];
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
		
		ArrayList<Evocavel> unidadesEvocadas = getUnidadesEvocadas(atacante);
		
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
		
		ArrayList<Evocavel> unidadesEvocadas = getUnidadesEvocadas(defensor);
		
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
				for (int j = 0; j < unidadesEmCampoAtacante[i].getAtaquesPorTurno(); j++) {
					if (validarDefesa(unidadesEmCampoAtacante[i], unidadesEmCampoDefensor[i])) {
						unidadesEmCampoAtacante[i].atacar(unidadesEmCampoDefensor[i]);
						if (!unidadesEmCampoDefensor[i].estaVivo()) {
							unidadesEmCampoDefensor[i] = null;

							if (unidadesEmCampoAtacante[i].isFuria()) {
								unidadesEmCampoAtacante[i].receberBonusFuria();
							}
						}
					}
					else {
						unidadesEmCampoAtacante[i].atacar(defensor);
					}

					if (!unidadesEmCampoAtacante[i].estaVivo()) {
						unidadesEmCampoAtacante[i] = null;
						break;
					}
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
		
		ArrayList<Evocavel> unidadesEvocadas = getUnidadesEvocadas(jogador);
		
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
		ArrayList<Evocavel> unidadesEvocadas = getUnidadesEvocadas(jogador);
		
		Evocavel evocada = jogador.evocarCarta(indexMao, podeEvocarUnidade(unidadesEvocadas));
		
		evocada.ativar(AtivacaoEfeito.EVOCACAO_DA_CARTA);
		
		if(evocada instanceof Evocavel) {
			unidadesEvocadas.add((Evocavel)evocada);
		}
	}
	
	private void substituirCarta(Jogador jogador, int indexMao, int indexMesa) throws Exception {
		ArrayList<Evocavel> unidadesEvocadas = getUnidadesEvocadas(jogador);
		
		if (indexMesa < 0 || indexMesa >= unidadesEvocadas.size())
			throw new Exception("Posi��o de carta na mesa inv�lida. Posi��o: "+indexMesa+". Quantidade de cartas: "+unidadesEvocadas.size());
		
		Evocavel substituida = unidadesEvocadas.get(indexMesa);
		Compravel substituta = jogador.substituirCarta(indexMao, substituida);
	
		substituta.ativar(AtivacaoEfeito.EVOCACAO_DA_CARTA);
		unidadesEvocadas.remove(indexMesa);
		
		if(substituta instanceof Evocavel) {
			unidadesEvocadas.add((Evocavel)substituta);
		}
	}
	
	private void colocarCartaEmCampo(Jogador jogador, int indexEvocadas, int indexCampo) throws Exception {
		ArrayList<Evocavel> unidadesEvocadas = getUnidadesEvocadas(jogador);
		Evocavel[] unidadesEmCampo = getUnidadesEmCampo(jogador);
		
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
		ArrayList<Evocavel> unidadesEvocadasOutroJogador = jogador == jogador1 ? unidadesEvocadasJogador2 : unidadesEvocadasJogador1;
		for (Evocavel unidade : unidadesEvocadasOutroJogador) {
			try {
				unidade.ativar(AtivacaoEfeito.FINAL_DO_TURNO);
			}
			catch (Exception ex) {
				jogador.exibirMensagemErro(ex.getMessage());
			}
		}
	}

	private void removerCartasEmCampo(Jogador jogador) {
		ArrayList<Evocavel> unidadesEvocadas = getUnidadesEvocadas(jogador);
		Evocavel[] unidadesEmCampo = getUnidadesEmCampo(jogador);
		
		for (int i = 0; i < unidadesEmCampo.length; i++) {
			if (unidadesEmCampo[i] != null) {
				if (unidadesEmCampo[i].estaVivo()) {
					unidadesEvocadas.add(unidadesEmCampo[i]);
				}
				unidadesEmCampo[i] = null;
			}
		}
	}
	
	private boolean validarDefesa(Evocavel atacante, Evocavel defensor) {
		if (defensor == null) //nao existe defensor nessa posicao
			return false;
		if (!atacante.isElusivo()) //existe defensor e atacante � um seguidor n�o elusivo
			return true;
		
		return defensor.isElusivo(); //atacante � seguidor elusivo, s� defende se defensor for elusivo tamb�m.
	}
	
	private boolean podeEvocarUnidade(ArrayList<Evocavel> unidadesEvocadas) {
		return unidadesEvocadas.size() < QTD_UNIDADES_EVOCADAS;
	}
	
	ArrayList<Evocavel> getUnidadesEvocadas(Jogador jogador) {
		return jogador == jogador1 ? unidadesEvocadasJogador1 : unidadesEvocadasJogador2;
	}
	
	private Evocavel[] getUnidadesEmCampo(Jogador jogador) {
		return jogador == atacante ? unidadesEmCampoAtacante : unidadesEmCampoDefensor;
	}
}