<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Erro</title>
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous">
</head>
<body>
    <jsp:include page="/includes/base.jsp" />
    <div class="container d-flex justify-content-center align-items-center"
        style="min-height: 100vh;">
        <div class="row w-100">
            <div class="col-md-6 mx-auto">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center text-danger">Ocorreu um erro!</h4>
                        <p class="text-center">Desculpe, algo deu errado. Tente novamente mais tarde.</p>
                        <div class="d-flex justify-content-center">
                            <a href="index.jsp" class="btn btn-danger">Voltar</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
