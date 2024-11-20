package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.flightstates.TakingOff;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmbarcandoCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FlightDataCollection collection = new FlightDataCollection();
		
		List<FlightData> avioes = collection.getAllFligthts().stream()
				.filter(c -> c.getState() instanceof TakingOff)
				.toList();
		
		request.setAttribute("listaAvioesEmbarcando", avioes);
		
		return "embarcando.jsp";
	}

}
