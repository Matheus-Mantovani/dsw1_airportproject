package br.edu.ifsp.dsw1.model.totens;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.flightstates.TookOff;
import br.edu.ifsp.dsw1.model.observer.FlightDataObserver;

public class TotemAllFlights implements FlightDataObserver{
	private List<FlightData> allFlights;
	
	
	public TotemAllFlights() {
		this.allFlights = new ArrayList<>();
	}


	@Override
	public void update(FlightData flight) {
		if(!(flight.getState() instanceof TookOff)) {
			if(!allFlights.contains(flight)) {
				allFlights.add(flight);
			}
		} else {
			allFlights.remove(flight);
		}
	}
	
	public List<FlightData> getFlights() {
		return new ArrayList<>(allFlights);
	}
}
