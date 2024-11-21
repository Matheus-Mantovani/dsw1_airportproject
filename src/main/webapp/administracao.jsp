<%@page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    var logado = false;
    var attLista = request.getAttribute("listaAvioes");
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
    <jsp:include page="/includes/base.jsp" />
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-12">
                <% if (logado) { %>
                    <h3 class="text-center">Tabela de Voos</h3>
                    <% if (request.getAttribute("idInvalido") != null) { %>
                        <p class="text-center" style="color: red; font-weight: bold;">
                            <%= request.getAttribute("idInvalido") %>
                        </p>
                    <% } %>
                    <table class="table table-striped table-bordered mt-4">
                        <thead class="table-dark text-center">
                            <tr>
                                <th>Companhia Aérea</th>
                                <th>ID do Voo</th>
                                <th>Tempo</th>
                                <th>Estado</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (FlightData voo : listaVoos) { %>
                            <tr>
                                <td><%= voo.getCompany() %></td>
                                <td><%= voo.getFlightNumber() %></td>
                                <td><%= voo.getTime() %></td>
                                <td><%= voo.getState().getClass().getSimpleName() %></td>
                                <td>
                                    <a href="controller.do?action=updateState&number=<%= voo.getFlightNumber() %>&redirect=administracao">
                                        Atualizar
                                    </a>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } else { %>
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title text-center">Acesso Restrito</h4>
                            <p class="text-center" style="color: red; font-weight: bold;">
                                Você precisa estar logado para acessar esta página.
                            </p>
                            <div class="d-flex justify-content-center">
                                <a href="controller.do?action=index" class="btn btn-primary w-50">Voltar para o Login</a>
                            </div>
                        </div>
                    </div>
                <% } %>
            </div>
        </div>
    </div>
</body>
</html>
