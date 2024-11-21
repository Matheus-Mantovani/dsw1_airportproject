package br.edu.ifsp.dsw1.model.totens;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.flightstates.TookOff;
import br.edu.ifsp.dsw1.model.observer.FlightDataObserver;

public class TotemAllFlights implements FlightDataObserver {
	// Lista para armazenar todos os voos que ainda não decolaram
	private List<FlightData> allFlights;
	
	public TotemAllFlights() {
		this.allFlights = new ArrayList<>();
	}

	// Método chamado sempre que há uma atualização no estado de um voo
	@Override
	public void update(FlightData flight) {
		// Verifica se o estado do voo não é "TookOff" (decolou)
		if (!(flight.getState() instanceof TookOff)) {
			// Adiciona o voo à lista se ele ainda não estiver presente
			if (!allFlights.contains(flight)) {
				allFlights.add(flight);
			}
		} else {
			// Remove o voo da lista se ele estiver na lista e já tiver decolado
			allFlights.remove(flight);
		}
	}
	
	// Retorna uma cópia da lista de voos
	public List<FlightData> getFlights() {
		return new ArrayList<>(allFlights);
	}
}
