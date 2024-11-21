package br.edu.ifsp.dsw1.model.totens;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.flightstates.TookOff;
import br.edu.ifsp.dsw1.model.observer.FlightDataObserver;

public class TotemTookOff implements FlightDataObserver {
	// Lista para armazenar voos cujo estado é TookOff
	private List<FlightData> tookOffList;
	
	public TotemTookOff() {
		this.tookOffList = new ArrayList<>();
	}
	
	// Método chamado sempre que o estado de um voo é atualizado
	@Override
	public void update(FlightData flight) {
		// Verifica se o estado do voo é "TookOff"
		if (flight.getState() instanceof TookOff) {
			// Adiciona o voo à lista se ele ainda não estiver presente
			if (!tookOffList.contains(flight)) {
				tookOffList.add(flight);
			}
		}
	}
	
	// Retorna uma cópia da lista de voos decolados
	public List<FlightData> getFlights() {
		return new ArrayList<>(tookOffList);
	}
}
