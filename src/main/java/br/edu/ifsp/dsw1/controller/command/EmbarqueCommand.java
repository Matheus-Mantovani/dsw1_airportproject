package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.flightstates.Boarding;
import br.edu.ifsp.dsw1.model.totens.TotemBoarding;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmbarqueCommand implements Command{
	private TotemBoarding totem;

	
	public EmbarqueCommand(TotemBoarding totem) {
		super();
		this.totem = totem;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<FlightData> avioes = totem.getFlights();
		
		request.setAttribute("listaAvioesEmbarque", avioes);
		
		return "embarque.jsp";
	}
}
