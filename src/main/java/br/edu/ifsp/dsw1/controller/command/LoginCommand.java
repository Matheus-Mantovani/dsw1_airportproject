package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String redirect;
		
		if("admin".equals(nome) && "admin".equals(senha)) {
			HttpSession session = request.getSession();
			
			session.setAttribute("nome", nome);
			session.setAttribute("senha", senha);
			
			redirect = "controller.do?action=administracao";
		} else {
			redirect = "controller.do?action=index";
			request.setAttribute("msgErro", "Login inválido.");
		}
		
		return redirect;
	}

}
