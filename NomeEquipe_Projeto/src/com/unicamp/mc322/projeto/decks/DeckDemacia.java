package com.unicamp.mc322.projeto.decks;

import com.unicamp.mc322.projeto.cartas.Campeao;
import com.unicamp.mc322.projeto.cartas.Feitico;
import com.unicamp.mc322.projeto.cartas.Seguidor;
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
		Feitico julgamento = new Feitico("Julgamento", 8);
		julgamento.addEfeito(new GolpeiaTodos(AtivacaoEfeito.EVOCACAO_DA_CARTA));
		cartas.add(julgamento);
		
		//Valor Redobrado
		Feitico valorRedobrado = new Feitico("Valor Redobrado", 6);
		valorRedobrado.addEfeito(new DobraAtaqueDefesa(AtivacaoEfeito.EVOCACAO_DA_CARTA));
		cartas.add(valorRedobrado);
		
		//Golpe Certeiro
		Feitico golpeCerteiro = new Feitico("Golpe Certeiro", 1);
		golpeCerteiro.addEfeito(new BonusStatusUnidade(AtivacaoEfeito.EVOCACAO_DA_CARTA, 1, 1));
		cartas.add(golpeCerteiro);
		
		//Combate Um-a-um
		Feitico combateUmAUm = new Feitico("Combate um-a-um", 2);
		combateUmAUm.addEfeito(new CombateImediato(AtivacaoEfeito.EVOCACAO_DA_CARTA));
		cartas.add(combateUmAUm);
	}
	
	private void gerarSeguidores() {
		//Tiana
		Seguidor tiana = new Seguidor("Tiana", 7, 7, 8);
		tiana.addEfeito(new ForcaAtaqueNexus(AtivacaoEfeito.EVOCACAO_DA_CARTA));
		cartas.add(tiana);
		
		//Vanguarda
		Seguidor vanguarda = new Seguidor("Vanguarda", 3, 3, 4);
		vanguarda.addEfeito(new BonusStatusGlobal(AtivacaoEfeito.EVOCACAO_DA_CARTA, 1, 1));
		cartas.add(vanguarda);
		
		//Duelista
		Seguidor duelista = new Seguidor("Duelista", 2, 3, 3);
		duelista.addEfeito(new BonusUnidadeAoMatar(AtivacaoEfeito.FINAL_DO_TURNO, new Seguidor("Poro", 1, 2, 1)));
		cartas.add(duelista);
		
		//Defensor
		Seguidor defensor = new Seguidor("Defensor", 2, 2, 2);
		defensor.addTraco(new Furia(AtivacaoTraco.APOS_ATAQUE, 0, 1));
		cartas.add(defensor);
		
		//Poro
		cartas.add(new Seguidor("Poro", 1, 2, 1));
		
		//Poro Defensor
		Seguidor poroDefensor = new Seguidor("Poro Defensor", 2, 1, 1);
		poroDefensor.addEfeito(new BonusCartaDestruicao(AtivacaoEfeito.FINAL_DO_TURNO));
		cartas.add(poroDefensor);
	}
	
	private void gerarCampeoes() {
		Campeao garen = new Campeao("Garen", 5, 5, 5, new QuantidadeAtaque(2));
		garen.addEfeito(new CuraCompleta(AtivacaoEfeito.FINAL_DO_TURNO));
		garen.addEvolucao(new BonusTraco(new Elusivo(AtivacaoTraco.ADICAO_DO_TRACO)));
		garen.addEvolucao(new BonusPoder(1));
		garen.addEvolucao(new BonusVida(1));
		cartas.add(garen);
	}
}
