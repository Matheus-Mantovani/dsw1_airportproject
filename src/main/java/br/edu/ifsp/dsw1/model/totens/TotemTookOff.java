package br.edu.ifsp.dsw1.model.totens;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.flightstates.TookOff;
import br.edu.ifsp.dsw1.model.observer.FlightDataObserver;

public class TotemTookOff implements FlightDataObserver {
	private List<FlightData> tookOffList;
	
	
	public TotemTookOff() {
		this.tookOffList = new ArrayList<>();
	}
	
	
	@Override
	public void update(FlightData flight) {
		if(flight.getState() instanceof TookOff) {
			if(!tookOffList.contains(flight)) {
				tookOffList.add(flight);
			}
		}
	}
	
	public List<FlightData> getFlights() {
		return new ArrayList<>(tookOffList);
	}
}
