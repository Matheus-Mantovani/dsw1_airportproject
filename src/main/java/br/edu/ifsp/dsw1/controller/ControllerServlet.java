package br.edu.ifsp.dsw1.controller;

import java.io.IOException;

import br.edu.ifsp.dsw1.controller.command.AdministracaoCommand;
import br.edu.ifsp.dsw1.controller.command.Command;
import br.edu.ifsp.dsw1.controller.command.DesembarqueCommand;
import br.edu.ifsp.dsw1.controller.command.EmbarcandoCommand;
import br.edu.ifsp.dsw1.controller.command.EmbarqueCommand;
import br.edu.ifsp.dsw1.controller.command.ErrorCommand;
import br.edu.ifsp.dsw1.controller.command.IndexCommand;
import br.edu.ifsp.dsw1.controller.command.LoginCommand;
import br.edu.ifsp.dsw1.controller.command.LogoutCommand;
import br.edu.ifsp.dsw1.controller.command.UpdateStateCommand;
import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.flightstates.Arriving;
import br.edu.ifsp.dsw1.model.flightstates.Boarding;
import br.edu.ifsp.dsw1.model.flightstates.TakingOff;
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
		
		//temporario gambiarra apagar depois
		FlightData fd11 = new FlightData(1232L, "aaa", "13:32");
		fd11.setState(Arriving.getInstance());
		FlightData fd12 = new FlightData(1232L, "bbb", "42:32");
		fd12.setState(TakingOff.getInstance());
		FlightData fd13 = new FlightData(1232L, "ccc", "23:12");
		fd13.setState(Boarding.getInstance());
		FlightData fd14 = new FlightData(1232L, "ddd", "12:31");
		fd14.setState(TakingOff.getInstance());
		
		database.insertFlight(fd11);
		database.insertFlight(fd12);
		database.insertFlight(fd13);
		database.insertFlight(fd14);
	}


	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getParameter("action");
		Command comando = null;

		if("administracao".equals(action)) {
			comando = new AdministracaoCommand(database); //
		} else if("embarque".equals(action)) {
			comando = new EmbarqueCommand(totemBoarding);
		} else if("embarcando".equals(action)) {
			comando = new EmbarcandoCommand(totemTakingOff);
		} else if("desembarque".equals(action)) {
			comando = new DesembarqueCommand(totemArriving);
		} else if("login".equals(action)) {
			comando = new LoginCommand();
		} else if("logout".equals(action)) {
			comando = new LogoutCommand();
		} else if("updateState".equals(action)) {
			String strNum = request.getParameter("number");
			Long number = parseLongParameter(strNum);
			if(number != null) {
				comando = new UpdateStateCommand(database, number);
			}
		} else if("index".equals(action)) {
			comando = new IndexCommand();
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
