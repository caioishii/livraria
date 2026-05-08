package br.ufscar.dsw.livraria.dao;

import br.ufscar.dsw.livraria.model.Editora;
import br.ufscar.dsw.livraria.model.Livro;
import br.ufscar.dsw.livraria.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    public void salvar(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, ano, preco, editora_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setBigDecimal(4, livro.getPreco());
            stmt.setLong(5, livro.getEditora().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Livro livro) {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, ano = ?, preco = ?, editora_id = ? WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setBigDecimal(4, livro.getPreco());
            stmt.setLong(5, livro.getEditora().getId());
            stmt.setLong(6, livro.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remover(Long id) {
        String sql = "DELETE FROM livro WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Livro buscarPorId(Long id) {
        String sql = "SELECT l.id, l.titulo, l.autor, l.ano, l.preco, e.id AS editora_id, e.nome, e.cidade FROM livro l INNER JOIN editora e ON e.id = l.editora_id WHERE l.id = ?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Editora editora = new Editora(rs.getLong("editora_id"), rs.getString("nome"), rs.getString("cidade"));
                    return new Livro(rs.getLong("id"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano"), rs.getBigDecimal("preco"), editora);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Livro> buscarTodos() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT l.id, l.titulo, l.autor, l.ano, l.preco, e.id AS editora_id, e.nome, e.cidade FROM livro l INNER JOIN editora e ON e.id = l.editora_id ORDER BY l.titulo";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Editora editora = new Editora(rs.getLong("editora_id"), rs.getString("nome"), rs.getString("cidade"));
                livros.add(new Livro(rs.getLong("id"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano"), rs.getBigDecimal("preco"), editora));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livros;
    }
}
