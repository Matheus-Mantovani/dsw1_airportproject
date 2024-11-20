package br.edu.ifsp.dsw1.controller;

import java.io.IOException;

import br.edu.ifsp.dsw1.controller.command.AdministracaoCommand;
import br.edu.ifsp.dsw1.controller.command.Command;
import br.edu.ifsp.dsw1.controller.command.DesembarqueCommand;
import br.edu.ifsp.dsw1.controller.command.EmbarcandoCommand;
import br.edu.ifsp.dsw1.controller.command.EmbarqueCommand;
import br.edu.ifsp.dsw1.controller.command.ErrorCommand;
import br.edu.ifsp.dsw1.controller.command.LoginCommand;
import br.edu.ifsp.dsw1.controller.command.LogoutCommand;
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
			comando = new AdministracaoCommand();
		} else if("embarque".equals(action)) {
			comando = new EmbarqueCommand();
		} else if("embarcando".equals(action)) {
			comando = new EmbarcandoCommand();
		} else if("desembarque".equals(action)) {
			comando = new DesembarqueCommand();
		} else if("login".equals(action)) {
			comando = new LoginCommand();
		} else if("logout".equals(action)) {
			comando = new LogoutCommand();
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
