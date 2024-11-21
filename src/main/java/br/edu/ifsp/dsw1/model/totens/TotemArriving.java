package br.edu.ifsp.dsw1.model.totens;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.flightstates.Arriving;
import br.edu.ifsp.dsw1.model.observer.FlightDataObserver;

public class TotemArriving implements FlightDataObserver{
	private List<FlightData> arrivingList;
	
	
	public TotemArriving() {
		this.arrivingList = new ArrayList<>();
	}

	
	@Override
	public void update(FlightData flight) {
		if(flight.getState() instanceof Arriving) {
			if(!arrivingList.contains(flight)) {
				arrivingList.add(flight);
			}
		} else if(arrivingList.contains(flight)) {
			arrivingList.remove(flight);
		}
	}
	
	public List<FlightData> getFlights() {
		return new ArrayList<>(arrivingList);
	}
}
