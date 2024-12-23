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
<title>Aeroporto DSW1</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<nav class="navbar navbar-expand-lg bg-secondary-subtle">
			<div class="container-fluid">
				<a class="navbar-brand" href="index.jsp">Airport DSW1</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<%if(logado) {%>
						<li class="nav-item"><a class="nav-link" href="controller.do?action=administracao">
								Administração</a></li>
						<li class="nav-item"><a class="nav-link" href="controller.do?action=adicionarVoo">
								Adicionar Voo</a></li>
						<%} %>
						<li class="nav-item"><a class="nav-link" href="controller.do?action=desembarque"> Sala
								de Desembarque</a></li>
						<li class="nav-item"><a class="nav-link" href="controller.do?action=embarque"> Sala
								de Embarque</a></li>
						<li class="nav-item"><a class="nav-link" href="controller.do?action=hall1">Hall 1</a></li>
						<li class="nav-item"><a class="nav-link" href="controller.do?action=hall2">Hall 2</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>
