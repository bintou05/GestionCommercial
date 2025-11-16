package gestioncom.repository.memory;

import gestioncom.core.JDBCUtils;
import gestioncom.entity.Categorie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CategorieRepository {
    public void add(Categorie c) {
        String sql = "INSERT INTO categorie (id, libelle) VALUES (?, ?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getId());
            ps.setString(2, c.getLibelle());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Categorie> findAll() {
        List<Categorie> categories = new ArrayList<>();
        String sql = "SELECT * FROM categorie";

        try (Connection conn = JDBCUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                categories.add(new Categorie(rs.getInt("id"), rs.getString("libelle")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Categorie findById(int id) {
        String sql = "SELECT * FROM categorie WHERE id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Categorie(rs.getInt("id"), rs.getString("libelle"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
