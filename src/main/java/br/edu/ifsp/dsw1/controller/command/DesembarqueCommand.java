package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.flightstates.Arriving;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DesembarqueCommand implements Command{
	FlightDataCollection database;

	
	public DesembarqueCommand(FlightDataCollection database) {
		super();
		this.database = database;
	}


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<FlightData> avioes = database.getAllFligthts().stream()
				.filter(c -> c.getState() instanceof Arriving)
				.toList();
		
		request.setAttribute("listaAvioesDesembarque", avioes);
		
		return "desembarque.jsp";
	}

}
