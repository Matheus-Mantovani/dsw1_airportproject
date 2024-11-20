package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdministracaoCommand implements Command{
	private FlightDataCollection database;

	
	public AdministracaoCommand(FlightDataCollection database) {
		super();
		this.database = database;
	}

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<FlightData> avioes = database.getAllFligthts();
		
		request.setAttribute("listaAvioes", avioes);
		
		return "administracao.jsp";
	}

}
