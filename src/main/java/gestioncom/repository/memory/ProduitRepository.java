package gestioncom.repository.memory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gestioncom.core.JDBCUtils;
import gestioncom.entity.Categorie;
import gestioncom.entity.Produit;

public class ProduitRepository {

    private CategorieRepository categorieRepo = new CategorieRepository();

    public void add(Produit p) {
        String sql = "INSERT INTO produit (id, libelle, prix, quantite, categorie_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getId());
            ps.setString(2, p.getLibelle());
            ps.setDouble(3, p.getPrix());
            ps.setInt(4, p.getQuantite());
            ps.setObject(5, p.getCategorie() != null ? p.getCategorie().getId() : null);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produit> findAll() {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit";

        try (Connection conn = JDBCUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int cid = rs.getInt("categorie_id");
                Categorie cat = cid != 0 ? categorieRepo.findById(cid) : null;

                produits.add(new Produit(
                        rs.getInt("id"),
                        rs.getString("libelle"),
                        rs.getDouble("prix"),
                        rs.getInt("quantite"),
                        cat
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    public List<Produit> findByCategorieId(int categorieId) {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit WHERE categorie_id = ?";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, categorieId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Categorie cat = categorieRepo.findById(categorieId);

                produits.add(new Produit(
                        rs.getInt("id"),
                        rs.getString("libelle"),
                        rs.getDouble("prix"),
                        rs.getInt("quantite"),
                        cat
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produits;
    }

    public Produit findById(int id) {
        String sql = "SELECT * FROM produit WHERE id = ?";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int cid = rs.getInt("categorie_id");
                Categorie cat = cid != 0 ? categorieRepo.findById(cid) : null;

                return new Produit(
                        rs.getInt("id"),
                        rs.getString("libelle"),
                        rs.getDouble("prix"),
                        rs.getInt("quantite"),
                        cat
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
