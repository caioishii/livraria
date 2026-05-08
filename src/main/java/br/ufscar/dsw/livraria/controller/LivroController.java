package br.ufscar.dsw.livraria.controller;

import br.ufscar.dsw.livraria.dao.EditoraDAO;
import br.ufscar.dsw.livraria.dao.LivroDAO;
import br.ufscar.dsw.livraria.model.Editora;
import br.ufscar.dsw.livraria.model.Livro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

@WebServlet("/livros")
public class LivroController extends HttpServlet {
    private final LivroDAO livroDAO = new LivroDAO();
    private final EditoraDAO editoraDAO = new EditoraDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        if (acao == null) acao = "listar";

        switch (acao) {
            case "novo":
                req.setAttribute("editoras", editoraDAO.buscarTodos());
                req.getRequestDispatcher("/livro/formulario.jsp").forward(req, resp);
                break;
            case "editar":
                Long idEditar = Long.parseLong(req.getParameter("id"));
                req.setAttribute("livro", livroDAO.buscarPorId(idEditar));
                req.setAttribute("editoras", editoraDAO.buscarTodos());
                req.getRequestDispatcher("/livro/formulario.jsp").forward(req, resp);
                break;
            case "excluir":
                Long idExcluir = Long.parseLong(req.getParameter("id"));
                livroDAO.remover(idExcluir);
                resp.sendRedirect("livros?acao=listar");
                break;
            default:
                req.setAttribute("livros", livroDAO.buscarTodos());
                req.getRequestDispatcher("/livro/lista.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Livro livro = new Livro();
        livro.setTitulo(req.getParameter("titulo"));
        livro.setAutor(req.getParameter("autor"));
        livro.setAno(Integer.parseInt(req.getParameter("ano")));
        livro.setPreco(parsePreco(req.getParameter("preco")));
        Editora editora = new Editora();
        editora.setId(Long.parseLong(req.getParameter("editoraId")));
        livro.setEditora(editora);

        if (id == null || id.isBlank()) {
            livroDAO.salvar(livro);
        } else {
            livro.setId(Long.parseLong(id));
            livroDAO.atualizar(livro);
        }
        resp.sendRedirect("livros?acao=listar");
    }

    private BigDecimal parsePreco(String precoTexto) {
        if (precoTexto == null || precoTexto.isBlank()) {
            throw new IllegalArgumentException("Preco invalido");
        }

        String normalizado = precoTexto.replace("R$", "").trim();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        DecimalFormat format = new DecimalFormat("#,##0.00", symbols);
        format.setParseBigDecimal(true);
        try {
            return (BigDecimal) format.parse(normalizado);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Preco invalido: " + precoTexto, e);
        }
    }
}
