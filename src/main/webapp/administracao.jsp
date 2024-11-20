<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    var logado = false;
    if(session != null && session.getAttribute("nome") != null) {
        logado = true;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Novo Voo</title>
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous">
</head>
<body>
    <jsp:include page="/includes/base.html" />
    <div class="container d-flex justify-content-center align-items-center"
        style="min-height: 100vh;">
        <div class="row w-100">
            <div class="col-md-6 mx-auto">
                <% if (logado) { %>
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title text-center">Cadastrar Novo Voo</h4>
                            <form action="controller.do?action=administracao" method="post">
                                <div class="form-group">
                                    <label for="companhia">Companhia Aérea</label>
                                    <input type="text" class="form-control" id="companhia" name="companhia" 
                                        placeholder="Ex: Gol" required>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label for="idVoo">ID do Voo</label>
                                    <input type="text" class="form-control" id="idVoo" name="idVoo" 
                                        placeholder="Ex: 12345" required>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label for="horario">Horário de Chegada</label>
                                    <input type="time" class="form-control" id="horario" name="horario" required>
                                </div>
                                <br>
                                <div class="d-flex justify-content-between">
                                    <button type="submit" class="btn btn-primary w-48">Salvar</button>
                                    <button type="reset" class="btn btn-secondary w-48">Apagar</button>
                                </div>
                            </form>
                        </div>
                    </div>
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
