package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.flightstates.Arriving;
import br.edu.ifsp.dsw1.model.totens.TotemAllFlights;
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
		
		// Obtem os parametros da requisição
		String companhia = request.getParameter("companhia");
		Long strId = parseLongParameter(request.getParameter("idVoo"));
		String horario = request.getParameter("horario");
		
		if(companhia != null && strId != null && horario != null) {
			FlightData newFlight = new FlightData(strId, companhia, horario);
			// Define o estado do voo como "Arriving" (desembarque)
			newFlight.setState(Arriving.getInstance());
			
			database.insertFlight(newFlight);
			
			request.setAttribute("msgSucesso", "Avião adicionado à lista.");
		}
		return "adicionarVoo.jsp";
	}

	// Método auxiliar para converter uma String em Long
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
