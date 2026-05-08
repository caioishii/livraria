<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Editoras</title></head>
<body>
<h1>Lista de Editoras</h1>
<p><a href="${pageContext.request.contextPath}/editoras?acao=novo">Nova Editora</a></p>
<p><a href="${pageContext.request.contextPath}/index.jsp">Início</a></p>
<table border="1" cellpadding="5">
<tr><th>ID</th><th>Nome</th><th>Cidade</th><th>Ações</th></tr>
<c:forEach var="editora" items="${editoras}">
<tr>
<td>${editora.id}</td><td>${editora.nome}</td><td>${editora.cidade}</td>
<td><a href="${pageContext.request.contextPath}/editoras?acao=editar&id=${editora.id}">Editar</a> <a href="${pageContext.request.contextPath}/editoras?acao=excluir&id=${editora.id}">Excluir</a></td>
</tr>
</c:forEach>
</table>
</body></html>
