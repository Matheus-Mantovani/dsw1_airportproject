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
	private Long number;

	
	public UpdateStateCommand(FlightDataCollection database, Long number) {
		super();
		this.database = database;
		this.number = number;
	}


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		database.updateFlight(number);
		
		String redirect = request.getParameter("redirect");
		return "controller.do?action=" + redirect;
	}
}
