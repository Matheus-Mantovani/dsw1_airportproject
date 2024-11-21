package br.edu.ifsp.dsw1.model.totens;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.flightstates.TakingOff;
import br.edu.ifsp.dsw1.model.observer.FlightDataObserver;

public class TotemTakingOff implements FlightDataObserver {
	private List<FlightData> takingOffList;
	
	
	public TotemTakingOff() {
		this.takingOffList = new ArrayList<>();
	}

	
	@Override
	public void update(FlightData flight) {
		if(flight.getState() instanceof TakingOff) {
			if(!takingOffList.contains(flight)) {
				takingOffList.add(flight);
			}
		} else if(takingOffList.contains(flight)) {
			takingOffList.remove(flight);
		}
	}
	
	public List<FlightData> getFlights() {
		return new ArrayList<>(takingOffList);
	}
}
