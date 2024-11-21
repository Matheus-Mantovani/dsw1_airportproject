<%@page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
var logado = false;
var attLista = request.getAttribute("listaAvioesDesembarque");
List<FlightData> listaVoos = new ArrayList<>();

if(session != null && session.getAttribute("nome") != null) {
    logado = true;
}

if (attLista != null) {
	listaVoos = (List<FlightData>) attLista;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tabela de Voos</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="/includes/base.html" />
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-12">
				<h3 class="text-center">Tabela de Voos em DESEMBARQUE</h3>
				<table class="table table-striped table-bordered mt-4">
					<thead class="table-dark text-center">
						<tr>
							<th>Companhia Aérea</th>
							<th>ID do Voo</th>
							<th>Tempo</th>
							<%if(logado) {%>
							<th>Ações</th>
							<%} %>
						</tr>
					</thead>
					<tbody>
						<%for (FlightData voo : listaVoos) { %>
						<tr>
							<td><%=voo.getCompany()%></td>
							<td><%=voo.getFlightNumber()%></td>
							<td><%=voo.getTime()%></td>
							<%if(logado) {%>
							<td><a href="controller.do?action=updateState&number=<%=voo.getFlightNumber()%>&redirect=desembarque">Enviar para embarque</a></td>
							<%} %>
						</tr>
						<%} %>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
