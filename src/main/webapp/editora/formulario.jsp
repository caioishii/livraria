<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Formulário Editora</title></head>
<body>
<h1><c:choose><c:when test="${editora != null}">Editar</c:when><c:otherwise>Nova</c:otherwise></c:choose> Editora</h1>
<form action="${pageContext.request.contextPath}/editoras" method="post">
<input type="hidden" name="id" value="${editora.id}">
<p>Nome: <input type="text" name="nome" value="${editora.nome}" required></p>
<p>Cidade: <input type="text" name="cidade" value="${editora.cidade}" required></p>
<button type="submit">Salvar</button>
</form>
<p><a href="${pageContext.request.contextPath}/editoras?acao=listar">Voltar</a></p>
</body></html>
