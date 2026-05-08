package br.ufscar.dsw.livraria.dao;

import br.ufscar.dsw.livraria.model.Editora;
import br.ufscar.dsw.livraria.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {
    public List<Editora> buscarTodos() {
        List<Editora> editoras = new ArrayList<>();
        String sql = "SELECT id, nome, cidade FROM editora ORDER BY nome";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
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
