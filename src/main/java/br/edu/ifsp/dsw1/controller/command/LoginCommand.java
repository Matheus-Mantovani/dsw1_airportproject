package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String redirect; // Define a página para redirecionar após o processamento
		
		if ("admin".equals(nome) && "admin".equals(senha)) {
			// Cria uma nova sessão ou obtém a sessão atual do usuário
			HttpSession session = request.getSession();
			
			// Define atributos na sessão para manter o estado do login
			session.setAttribute("nome", nome);
			session.setAttribute("senha", senha);
			
			// Redireciona para a área de administração em caso de login válido
			redirect = "controller.do?action=administracao";
		} else {
			// Redireciona para a página inicial em caso de erro de login
			redirect = "controller.do?action=index";
			
			// Define uma mensagem de erro como atributo da requisição
			request.setAttribute("msgErro", "Login inválido.");
		}
		
		// Retorna a URL para onde o controle deve ser redirecionado
		return redirect;
	}
}
