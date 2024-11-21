package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;

import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateStateCommand implements Command {
	// Dependência da coleção de dados de voos
	private FlightDataCollection database;

	// Construtor para inicializar a dependência do banco de voos
	public UpdateStateCommand(FlightDataCollection database) {
		super();
		this.database = database;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Obtém o parâmetro que indica para onde redirecionar após a execução
		String redirect = request.getParameter("redirect");
		String strNum = request.getParameter("number");

		Long number = parseLongParameter(strNum);

		if (number != null) {
			// Atualiza o estado do voo correspondente no banco de dados
			database.updateFlight(number);

			// Redireciona para a ação especificada no parâmetro 'redirect'
			return "controller.do?action=" + redirect;
		}

		request.setAttribute("idInvalido", "O id não é válido.");
		
		// Redireciona para a mesma página, mantendo a mensagem de erro visível
		return "controller.do?action=" + redirect;
	}

	// Método auxiliar para converter uma string em Long
	private Long parseLongParameter(String strNum) throws IOException {
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
