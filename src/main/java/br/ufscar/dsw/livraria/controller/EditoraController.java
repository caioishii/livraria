package br.ufscar.dsw.livraria.controller;

import br.ufscar.dsw.livraria.dao.EditoraDAO;
import br.ufscar.dsw.livraria.model.Editora;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editoras")
public class EditoraController extends HttpServlet {
    private final EditoraDAO editoraDAO = new EditoraDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        if (acao == null) acao = "listar";

        switch (acao) {
            case "novo":
                req.getRequestDispatcher("/editora/formulario.jsp").forward(req, resp);
                break;
            case "editar":
                Long idEditar = Long.parseLong(req.getParameter("id"));
                req.setAttribute("editora", editoraDAO.buscarPorId(idEditar));
                req.getRequestDispatcher("/editora/formulario.jsp").forward(req, resp);
                break;
            case "excluir":
                Long idExcluir = Long.parseLong(req.getParameter("id"));
                editoraDAO.remover(idExcluir);
                resp.sendRedirect("editoras?acao=listar");
                break;
            default:
                req.setAttribute("editoras", editoraDAO.buscarTodos());
                req.getRequestDispatcher("/editora/lista.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Editora editora = new Editora();
        editora.setNome(req.getParameter("nome"));
        editora.setCidade(req.getParameter("cidade"));

        if (id == null || id.isBlank()) {
            editoraDAO.salvar(editora);
        } else {
            editora.setId(Long.parseLong(id));
            editoraDAO.atualizar(editora);
        }
        resp.sendRedirect("editoras?acao=listar");
    }
}
