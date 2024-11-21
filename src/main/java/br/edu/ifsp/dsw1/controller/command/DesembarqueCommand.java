package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.flightstates.Arriving;
import br.edu.ifsp.dsw1.model.totens.TotemArriving;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DesembarqueCommand implements Command{
	private TotemArriving totem;

	
	public DesembarqueCommand(TotemArriving totem) {
		super();
		this.totem = totem;
	}


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<FlightData> avioes = totem.getFlights();
		
		request.setAttribute("listaAvioesDesembarque", avioes);
		
		return "desembarque.jsp";
	}

}
