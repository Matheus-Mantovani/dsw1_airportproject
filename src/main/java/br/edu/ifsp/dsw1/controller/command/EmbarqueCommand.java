package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.flightstates.Boarding;
import br.edu.ifsp.dsw1.model.flightstates.TakingOff;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmbarqueCommand implements Command{
	private FlightDataCollection database;

	
	public EmbarqueCommand(FlightDataCollection database) {
		super();
		this.database = database;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<FlightData> avioes = database.getAllFligthts().stream()
				.filter(c -> c.getState() instanceof Boarding)
				.toList();
		
		request.setAttribute("listaAvioesEmbarque", avioes);
		
		return "embarque.jsp";
	}
}
