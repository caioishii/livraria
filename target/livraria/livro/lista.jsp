<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Livros</title></head>
<body>
<h1>Lista de Livros</h1>
<p><a href="${pageContext.request.contextPath}/livros?acao=novo">Novo Livro</a></p>
<p><a href="${pageContext.request.contextPath}/index.jsp">Início</a></p>
<table border="1" cellpadding="5">
<tr><th>ID</th><th>Título</th><th>Autor</th><th>Ano</th><th>Preço</th><th>Editora</th><th>Ações</th></tr>
<c:forEach var="livro" items="${livros}">
<tr>
<td>${livro.id}</td><td>${livro.titulo}</td><td>${livro.autor}</td><td>${livro.ano}</td><td>R$ ${livro.preco}</td><td>${livro.editora.nome}</td>
<td><a href="${pageContext.request.contextPath}/livros?acao=editar&id=${livro.id}">Editar</a> <a href="${pageContext.request.contextPath}/livros?acao=excluir&id=${livro.id}">Excluir</a></td>
</tr>
</c:forEach>
</table>
</body></html>
