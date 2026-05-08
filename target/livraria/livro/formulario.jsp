<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Formulário Livro</title></head>
<body>
<h1><c:choose><c:when test="${livro != null}">Editar</c:when><c:otherwise>Novo</c:otherwise></c:choose> Livro</h1>
<form action="${pageContext.request.contextPath}/livros" method="post">
<input type="hidden" name="id" value="${livro.id}">
<p>Título: <input type="text" name="titulo" value="${livro.titulo}" required></p>
<p>Autor: <input type="text" name="autor" value="${livro.autor}" required></p>
<p>Ano: <input type="number" name="ano" value="${livro.ano}" required></p>
<p>Preço: <input type="text" name="preco" value="${livro.preco}" required></p>
<p>Editora:
<select name="editoraId" required>
<c:forEach var="editora" items="${editoras}">
<option value="${editora.id}" ${livro != null && livro.editora.id == editora.id ? 'selected' : ''}>${editora.nome}</option>
</c:forEach>
</select>
</p>
<button type="submit">Salvar</button>
</form>
<p><a href="${pageContext.request.contextPath}/livros?acao=listar">Voltar</a></p>
</body></html>
