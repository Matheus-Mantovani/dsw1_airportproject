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
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/controller.do")
public class ControllerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private FlightDataCollection database = new FlightDataCollection();
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getParameter("action");
		Command comando = null;

		if("administracao".equals(action)) {
			comando = new AdministracaoCommand(database);
		} else if("embarque".equals(action)) {
			comando = new EmbarqueCommand(database);
		} else if("embarcando".equals(action)) {
			comando = new EmbarcandoCommand(database);
		} else if("desembarque".equals(action)) {
			comando = new DesembarqueCommand(database);
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
