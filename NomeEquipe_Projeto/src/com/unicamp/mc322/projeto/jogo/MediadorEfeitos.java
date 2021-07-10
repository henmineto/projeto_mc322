package com.unicamp.mc322.projeto.jogo;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.Evocavel;
import com.unicamp.mc322.projeto.jogadores.Jogador;

public class MediadorEfeitos {

	private Mesa mesa;
	private static MediadorEfeitos singleton;
	
	public static MediadorEfeitos getInstance() {
		if (singleton == null) {
			singleton = new MediadorEfeitos();
		}
		return singleton;
	}
	
	void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	public void darBonusStatusUnidadeAliada(Jogador jogador, int bonusAtaque, int bonusDefesa) {
		Evocavel aliada = escolherCartaParaBonus(jogador);
		if (aliada != null) { 
			aliada.aumentarVida(bonusDefesa);
			aliada.aumentarDano(bonusAtaque);
		}
	}
	
	public void darBonusStatusGlobal(Jogador jogador, int bonusAtaque, int bonusDefesa) {
		ArrayList<Evocavel> unidadesEvocadas = mesa.getUnidadesEvocadas(jogador);
		
		for (Evocavel carta : unidadesEvocadas) {
			carta.aumentarVida(bonusDefesa);
			carta.aumentarDano(bonusAtaque);
		}
	}
	
	public void dobrarAtaqueDefesaUnidadeAliada(Jogador jogador) {
		Evocavel aliada = escolherCartaParaBonus(jogador);
		if (aliada != null) {
			aliada.aumentarVida(aliada.getVida());
			aliada.aumentarDano(aliada.getAtaque());		
		}
	}
	
	public void darBonusCartaDestruicao(Jogador jogador) {
		jogador.pegarCarta();
	}

	public void darBonusUnidadeAoMatar(Jogador jogador, Evocavel unidadeBonus) {
		jogador.receberCarta(unidadeBonus);
	}
	
	public void atacarNexusInimigo(Jogador jogador) {
		Evocavel aliada = escolherCartaParaBonus(jogador);
		if (aliada != null) {			
			Jogador outro = mesa.getOutroJogador(jogador);
			
			aliada.atacar(outro);
		}
	}

	public void danificarNexusInimigo(Jogador jogador, int dano) {
		Jogador outro = mesa.getOutroJogador(jogador);
		outro.receberDano(dano);
	}
	
	public void golpearTodosEvocadosOponente(Jogador jogador) {
		Jogador outro = mesa.getOutroJogador(jogador);
		ArrayList<Evocavel> unidadesOponente = mesa.getUnidadesEvocadas(outro);
		
		Evocavel aliada = escolherCartaParaBonus(jogador);
		if (aliada != null) {	
			for (Evocavel oponente : unidadesOponente) {
				if (mesa.realizarCombateUnidades(aliada, oponente) && !oponente.estaVivo()) {
					unidadesOponente.remove(oponente);
				}
			}
		}
	}
	
	public void realizarCombateImediato(Jogador jogador) {
		Jogador outro = mesa.getOutroJogador(jogador);
		ArrayList<Evocavel> unidadesOponente = mesa.getUnidadesEvocadas(outro);
		
		Evocavel aliada = escolherCartaParaBonus(jogador);
		Evocavel oponente = escolherCartaOponente(jogador);
		
		if (aliada != null && oponente != null && mesa.realizarCombateUnidades(oponente, aliada) && !oponente.estaVivo()) {
			unidadesOponente.remove(oponente);
		}
	}

	public void realizarCuraCompleta(Jogador jogador) {
		Evocavel aliada = escolherCartaParaBonus(jogador);

		if (aliada != null) {
			aliada.curaCompleta();
		}
	}

	public void garantirEscudoUnidade(Jogador jogador) {
		Evocavel aliada = escolherCartaParaBonus(jogador);

		if (aliada != null) {
			aliada.garantirEscudo();
		}
	}

	public void zerarAtaqueUnidade(Jogador jogador) {
		Evocavel aliada = escolherCartaParaBonus(jogador);

		if (aliada != null) {
			aliada.zerarAtaque();
		}
	}
	
	private Evocavel escolherCartaOponente(Jogador jogador) {
		Jogador outro = mesa.getOutroJogador(jogador);
		ArrayList<Evocavel> unidadesOponente = mesa.getUnidadesEvocadas(outro);
		
		boolean aplicarEfeito = true;
		
		while(aplicarEfeito) {
			try {
				int indexUnidade = jogador.escolherUnidadeParaAtaque(unidadesOponente.size());
				
				if (indexUnidade < 0 || indexUnidade >= unidadesOponente.size())
					throw new Exception("Posi��o de unidade evocada inv�lida. Informada: "+indexUnidade+". Quantidade de unidades: "+unidadesOponente.size());
				
				return unidadesOponente.get(indexUnidade); 
			}
			catch (Exception ex) {
				jogador.exibirMensagemErro(ex.getMessage());
			}
		}
		
		return null;
	}
	
	private Evocavel escolherCartaParaBonus(Jogador jogador) {
		ArrayList<Evocavel> unidadesEvocadas = mesa.getUnidadesEvocadas(jogador);
		
		boolean aplicarEfeito = true;
		
		while(aplicarEfeito) {
			try {
				int indexUnidade = jogador.escolherUnidadeParaBonus(unidadesEvocadas.size());
				
				if (indexUnidade < 0 || indexUnidade >= unidadesEvocadas.size())
					throw new Exception("Posi��o de unidade evocada inv�lida. Informada: "+indexUnidade+". Quantidade de unidades: "+unidadesEvocadas.size());
				
				return unidadesEvocadas.get(indexUnidade); 
			}
			catch (Exception ex) {
				jogador.exibirMensagemErro(ex.getMessage());
			}
		}
		
		return null;
	}
}
