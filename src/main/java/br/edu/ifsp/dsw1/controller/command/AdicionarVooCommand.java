package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.flightstates.Arriving;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdicionarVooCommand implements Command{
	private FlightDataCollection database;
	
	
	public AdicionarVooCommand(FlightDataCollection database) {
		super();
		this.database = database;
	}


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String companhia = request.getParameter("companhia");
		Long strId = parseLongParameter(request.getParameter("idVoo"));
		String horario = request.getParameter("horario");

		FlightData newFlight = new FlightData(strId, companhia, horario);
		newFlight.setState(Arriving.getInstance());
		
		database.insertFlight(newFlight);
		
		request.setAttribute("msgSucesso", "Avião adicionado à lista.");
		
		return "administracao.jsp";
	}

	private Long parseLongParameter(String strNum) 
	        throws IOException {
	    if (strNum != null && !strNum.isEmpty()) {
	        try {
	            return Long.parseLong(strNum);
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        }
	    }
	    return null;
	}
}
