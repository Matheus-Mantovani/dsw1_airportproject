package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtém a sessão atual, mas não cria uma nova caso não exista
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			session.invalidate(); // Invalida a sessão, encerrando o login do usuário
		}
		
		// Retorna a página inicial
		return "index.jsp";
	}
}
