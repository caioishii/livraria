package br.ufscar.dsw.livraria.dao;

import br.ufscar.dsw.livraria.model.Editora;
import br.ufscar.dsw.livraria.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {
    public void salvar(Editora editora) {
        String sql = "INSERT INTO editora (nome, cidade) VALUES (?, ?)";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getCidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Editora editora) {
        String sql = "UPDATE editora SET nome = ?, cidade = ? WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getCidade());
            stmt.setLong(3, editora.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remover(Long id) {
        String sql = "DELETE FROM editora WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Editora> buscarTodos() {
        List<Editora> editoras = new ArrayList<>();
        String sql = "SELECT id, nome, cidade FROM editora ORDER BY nome";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                editoras.add(new Editora(rs.getLong("id"), rs.getString("nome"), rs.getString("cidade")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return editoras;
    }

    public Editora buscarPorId(Long id) {
        String sql = "SELECT id, nome, cidade FROM editora WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Editora(rs.getLong("id"), rs.getString("nome"), rs.getString("cidade"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
