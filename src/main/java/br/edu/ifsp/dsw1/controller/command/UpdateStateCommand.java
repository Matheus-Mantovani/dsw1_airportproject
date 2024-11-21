package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.flightstates.Arriving;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateStateCommand implements Command{
	private FlightDataCollection database;

	
	public UpdateStateCommand(FlightDataCollection database) {
		super();
		this.database = database;
	}


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String redirect = request.getParameter("redirect");
		String strNum = request.getParameter("number");
		Long number = parseLongParameter(strNum);

		if(number != null) {
			database.updateFlight(number);
			return "controller.do?action=" + redirect;
		}
		
		request.setAttribute("idInvalido", "O id não é válido.");
		return "controller.do?action=" + redirect;
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
