package com.unicamp.mc322.projeto.decks;

import java.util.ArrayList;
import com.unicamp.mc322.projeto.cartas.Campeao;
import com.unicamp.mc322.projeto.cartas.Efeito;
import com.unicamp.mc322.projeto.cartas.Feitico;
import com.unicamp.mc322.projeto.cartas.ModuloEvolucao;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.cartas.Traco;
import com.unicamp.mc322.projeto.cartas.ativacoes.*;
import com.unicamp.mc322.projeto.cartas.condicoes.*;
import com.unicamp.mc322.projeto.cartas.efeitos.*;
import com.unicamp.mc322.projeto.cartas.evolucoes.*;
import com.unicamp.mc322.projeto.cartas.tracos.*;

public class DeckDemacia extends Deck {

	public DeckDemacia() {
		gerarCampeoes();
		gerarSeguidores();
		gerarFeiticos();
	}
	
	private void gerarFeiticos() {
		//Julgamento
		ArrayList<Efeito> efeitos = new ArrayList<Efeito>();
		efeitos.add(new GolpeiaTodos(AtivacaoEfeito.EVOCACAO_DA_CARTA));
		
		cartas.add(new Feitico("Julgamento", 8, efeitos));
		
		//Valor Redobrado
		efeitos = new ArrayList<Efeito>();
		efeitos.add(new DobraAtaqueDefesa(AtivacaoEfeito.EVOCACAO_DA_CARTA));
		
		cartas.add(new Feitico("Valor Redobrado", 6, efeitos));
		
		//Golpe Certeiro
		efeitos = new ArrayList<Efeito>();
		efeitos.add(new BonusStatusUnidade(AtivacaoEfeito.EVOCACAO_DA_CARTA, 1, 1));
		
		cartas.add(new Feitico("Golpe Certeiro", 1, efeitos));
		
		//Combate Um-a-um
		efeitos = new ArrayList<Efeito>();
		efeitos.add(new CombateImediato(AtivacaoEfeito.EVOCACAO_DA_CARTA));
		
		cartas.add(new Feitico("Combate um-a-um", 2, efeitos));
	}
	
	private void gerarSeguidores() {
		//Tiana
		ArrayList<Efeito> efeitos = new ArrayList<Efeito>();
		efeitos.add(new ForcaAtaqueNexus(AtivacaoEfeito.EVOCACAO_DA_CARTA));
				
		cartas.add(new Seguidor("Tiana", 7, 7, 8, efeitos, new ArrayList<Traco>()));
		
		//Vanguarda
		efeitos = new ArrayList<Efeito>();
		efeitos.add(new BonusStatusGlobal(AtivacaoEfeito.EVOCACAO_DA_CARTA));
		
		cartas.add(new Seguidor("Vanguarda", 3, 3, 4, efeitos, new ArrayList<Traco>()));
		
		//Duelista
		efeitos = new ArrayList<Efeito>();
		efeitos.add(new BonusUnidadeAoMatar(AtivacaoEfeito.FINAL_DO_TURNO));
		
		cartas.add(new Seguidor("Duelista", 2, 3, 3, efeitos, new ArrayList<Traco>()));
		
		//Defensor
		ArrayList<Traco> tracos = new ArrayList<Traco>();
		tracos.add(new Furia(AtivacaoTraco.APOS_ATAQUE, 0, 1));
		
		cartas.add(new Seguidor("Defensor", 2, 2, 2, new ArrayList<Efeito>(), tracos));
		
		//Poro
		cartas.add(new Seguidor("Poro", 1, 2, 1, new ArrayList<Efeito>(), new ArrayList<Traco>()));
		
		//Poro Defensor
		efeitos = new ArrayList<Efeito>();
		efeitos.add(new BonusCartaDestruicao(AtivacaoEfeito.FINAL_DO_TURNO));
		
		cartas.add(new Seguidor("Poro Defensor", 2, 1, 1, efeitos, new ArrayList<Traco>()));
	}
	
	private void gerarCampeoes() {
		ArrayList<Efeito> efeitos = new ArrayList<Efeito>();
		efeitos.add(new CuraCompleta(AtivacaoEfeito.FINAL_DO_TURNO));
		
		ArrayList<Traco> tracos = new ArrayList<Traco>();
		
		ArrayList<ModuloEvolucao> evolucoes = new ArrayList<ModuloEvolucao>();
		evolucoes.add(new BonusTraco(new Elusivo(AtivacaoTraco.ADICAO_DO_TRACO)));
		evolucoes.add(new BonusPoder(1));
		evolucoes.add(new BonusVida(1));
		
		cartas.add(new Campeao("Garen", 5, 5, 5, efeitos, tracos, new QuantidadeAtaque(2), evolucoes));
	}
}
