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
	
	public void atacarNexusInimigo(Jogador jogador) {
		Evocavel aliada = escolherCartaParaBonus(jogador);
		Jogador outro = mesa.getOutroJogador(jogador);
		
		aliada.atacar(outro);
	}
	
	private Evocavel escolherCartaParaBonus(Jogador jogador) {
		ArrayList<Evocavel> unidadesEvocadas = mesa.getUnidadesEvocadas(jogador);
		
		boolean aplicarEfeito = true;
		
		while(aplicarEfeito) {
			try {
				int indexUnidade = jogador.escolherUnidadeParaBonus(unidadesEvocadas.size());
				
				if (indexUnidade < 0 || indexUnidade >= unidadesEvocadas.size())
					throw new Exception("Posição de unidade evocada inválida. Informada: "+indexUnidade+". Quantidade de unidades: "+unidadesEvocadas.size());
				
				return unidadesEvocadas.get(indexUnidade); 
			}
			catch (Exception ex) {
				jogador.exibirMensagemErro(ex.getMessage());
			}
		}
		
		return null;
	}
}
