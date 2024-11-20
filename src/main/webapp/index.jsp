<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
//se o usuario ja estiver logado, a pagina de login nao aparecera
var logado = false;

if ("true".equals(request.getParameter("logado"))) {
	logado = true;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
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
				<%if (!logado) { %>
				<div class="card">
					<div class="card-body">
						<h4 class="card-title text-center">Login</h4>
						<form action="controller.do?action=login">
							<div class="form-group">
								<label for="nome">Nome</label>
								<input type="text" class="form-control" id="nome" name="nome" placeholder="Seu nome" required>
							</div>
							<br>
							<div class="form-group">
								<label for="senha">Senha</label> 
								<input type="password" class="form-control" id="senha" name="senha" placeholder="Sua senha" required>
							</div>
							<br>
							<div class="d-flex justify-content-between">
								<button type="submit" class="btn btn-primary w-48">Entrar</button>
								<button type="reset" class="btn btn-secondary w-48">Apagar</button>
							</div>
						</form>
					</div>
				</div>
				<%} else {%>
					<h4 class="card-title text-center">Você já está logado!</h4>
					<br>
					<a href="controller.do?action=logout" class="btn btn-danger w-100">Deslogar</a>
				<%} %>
			</div>
		</div>
	</div>
</body>
</html>