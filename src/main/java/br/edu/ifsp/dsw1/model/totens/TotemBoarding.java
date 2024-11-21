package br.edu.ifsp.dsw1.model.totens;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.flightstates.Boarding;
import br.edu.ifsp.dsw1.model.observer.FlightDataObserver;

public class TotemBoarding implements FlightDataObserver{
	private List<FlightData> boardingList;
	
	
	public TotemBoarding() {
		this.boardingList = new ArrayList<>();
	}

	
	@Override
	public void update(FlightData flight) {
		if(flight.getState() instanceof Boarding) {
			if(!boardingList.contains(flight)) {
				boardingList.add(flight);
			}
		} else if(boardingList.contains(flight)) {
			boardingList.remove(flight);
		}
	}
	
	public List<FlightData> getFlights() {
		return new ArrayList<>(boardingList);
	}
}
