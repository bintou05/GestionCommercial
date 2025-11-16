package gestioncom.services;

import gestioncom.entity.Categorie;
import gestioncom.entity.Produit;
import gestioncom.repository.memory.ProduitRepository;

import java.util.List;

public class ProduitService {

    private final ProduitRepository repo;
    private final CategorieService categorieService;

    public ProduitService(ProduitRepository repo, CategorieService categorieService) {
        this.repo = repo;
        this.categorieService = categorieService;
    }

    public void addProduit(Produit p) {
        repo.add(p);
    }

    public List<Produit> getAll() {
        return repo.findAll();
    }

    public Produit getById(int id) {
        return repo.findById(id);
    }

    public List<Produit> getByCategorie(int idCategorie) {
        return repo.findByCategorieId(idCategorie);
    }
}
