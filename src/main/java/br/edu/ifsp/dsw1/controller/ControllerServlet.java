package br.edu.ifsp.dsw1.controller;

import java.io.IOException;

import br.edu.ifsp.dsw1.controller.command.AdicionarVooCommand;
import br.edu.ifsp.dsw1.controller.command.AdministracaoCommand;
import br.edu.ifsp.dsw1.controller.command.Command;
import br.edu.ifsp.dsw1.controller.command.DesembarqueCommand;
import br.edu.ifsp.dsw1.controller.command.Hall1Command;
import br.edu.ifsp.dsw1.controller.command.Hall2Command;
import br.edu.ifsp.dsw1.controller.command.EmbarqueCommand;
import br.edu.ifsp.dsw1.controller.command.ErrorCommand;
import br.edu.ifsp.dsw1.controller.command.IndexCommand;
import br.edu.ifsp.dsw1.controller.command.LoginCommand;
import br.edu.ifsp.dsw1.controller.command.LogoutCommand;
import br.edu.ifsp.dsw1.controller.command.UpdateStateCommand;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.totens.TotemAllFlights;
import br.edu.ifsp.dsw1.model.totens.TotemArriving;
import br.edu.ifsp.dsw1.model.totens.TotemBoarding;
import br.edu.ifsp.dsw1.model.totens.TotemTakingOff;
import br.edu.ifsp.dsw1.model.totens.TotemTookOff;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/controller.do")
public class ControllerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private FlightDataCollection database = new FlightDataCollection();
	
	private TotemAllFlights totemAllFlights = new TotemAllFlights();
	private TotemArriving totemArriving = new TotemArriving();
	private TotemBoarding totemBoarding = new TotemBoarding();
	private TotemTakingOff totemTakingOff = new TotemTakingOff();
	private TotemTookOff totemTookOff = new TotemTookOff();
	
	
	public ControllerServlet() {
		database.register(totemArriving);
		database.register(totemBoarding);
		database.register(totemTakingOff);
		database.register(totemTookOff);
	}


	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getParameter("action");
		Command comando = null;

		if("administracao".equals(action)) {
			comando = new AdministracaoCommand(totemAllFlights);
		} else if("embarque".equals(action)) {
			comando = new EmbarqueCommand(totemBoarding);
		} else if("hall1".equals(action)) {
			comando = new Hall1Command(totemTakingOff);
		} else if("hall2".equals(action)) {
			comando = new Hall2Command(totemTookOff);
		} else if("desembarque".equals(action)) {
			comando = new DesembarqueCommand(totemArriving);
		} else if("login".equals(action)) {
			comando = new LoginCommand();
		} else if("logout".equals(action)) {
			comando = new LogoutCommand();
		} else if("updateState".equals(action)) {
			comando = new UpdateStateCommand(database);
		} else if("index".equals(action)) {
			comando = new IndexCommand();
		} else if("adicionarVoo".equals(action)) {
			comando = new AdicionarVooCommand(database);
		} else {
			comando = new ErrorCommand();
		}

		String view = comando.execute(request, response);
		var dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	
}
