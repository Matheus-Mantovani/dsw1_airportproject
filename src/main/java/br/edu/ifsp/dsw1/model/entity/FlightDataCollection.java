package br.edu.ifsp.dsw1.model.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifsp.dsw1.model.flightstates.Arriving;
import br.edu.ifsp.dsw1.model.flightstates.Boarding;
import br.edu.ifsp.dsw1.model.flightstates.TakingOff;
import br.edu.ifsp.dsw1.model.flightstates.TookOff;
import br.edu.ifsp.dsw1.model.observer.FlightDataObserver;
import br.edu.ifsp.dsw1.model.observer.FlightDataSubject;

public class FlightDataCollection implements FlightDataSubject{

	private List<FlightData> flights;
	private List<FlightDataObserver> observers;
	private FlightData lastUpdated;
	
	public FlightDataCollection() {
		this.flights = new LinkedList<FlightData>();
		this.observers = new LinkedList<FlightDataObserver>();
		adicionarAvioes();
	}

	@Override
	public void register(FlightDataObserver observer) {
		observers.add(observer);
	}

	@Override
	public void unregister(FlightDataObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		observers.stream()
			.forEach(observer -> observer.update(lastUpdated));
	}
	
	public void insertFlight(FlightData flight) {
		if (flight != null) {
			lastUpdated = flight;
			flights.add(flight);
			notifyObservers();
		}
	}
	
	public void updateFlight(Long flightNumber) {
		var flight = findByNumber(flightNumber);
		if (flight != null) {
			flight.getState().change(flight);
			if (flight.getState() instanceof TookOff) {
				flights.remove(flight);
			}
			lastUpdated = flight;
			notifyObservers();
		}
	}
	
	public List<FlightData> getAllFligthts() {
		return new ArrayList<FlightData>(flights);
	}
	
	private FlightData findByNumber(Long flightNumber) {
		return flights.stream()
				.filter(flight -> flight.getFlightNumber() == flightNumber)
				.findFirst()
				.orElse(null);
	}
	
	private void adicionarAvioes() { //TEMPORARIO, APAGAR DEPOIS
		FlightData a1 = new FlightData(123L, "Companhia1", "12:32");
		FlightData a2 = new FlightData(1432L, "Companhia2", "15:22");
		FlightData a3 = new FlightData(456L, "Companhia3", "08:12");
		FlightData a4 = new FlightData(634L, "Companhia4", "21:57");
		FlightData a5 = new FlightData(632L, "Companhia5", "23:23");
		
		a1.setState(Arriving.getInstance());
		a2.setState(Arriving.getInstance());
		a3.setState(Arriving.getInstance());
		a4.setState(Arriving.getInstance());
		a5.setState(Arriving.getInstance());
		
		flights.add(a1);
		flights.add(a2);
		flights.add(a3);
		flights.add(a4);
		flights.add(a5);
		
		updateFlight(123L);
		updateFlight(123L);
		updateFlight(1432L);
		//updateFlight(123L);
		updateFlight(634L);
		//updateFlight(634L);
		updateFlight(123L);
		updateFlight(1432L);
	}
}

