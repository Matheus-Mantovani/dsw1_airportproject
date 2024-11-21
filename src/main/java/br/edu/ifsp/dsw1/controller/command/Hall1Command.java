package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.totens.TotemTakingOff;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Hall1Command implements Command{
	private TotemTakingOff totem;

	
	public Hall1Command(TotemTakingOff totem) {
		super();
		this.totem = totem;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<FlightData> avioes = totem.getFlights();
		
		request.setAttribute("listaAvioesEmbarcando", avioes);
		
		return "hall1.jsp";
	}

}
