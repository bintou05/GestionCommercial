package gestioncom.services;

import java.util.List;

import gestioncom.entity.Categorie;
import gestioncom.repository.memory.CategorieRepository;
public class CategorieService {
    private CategorieRepository repo;

    public CategorieService(CategorieRepository repo) {
        this.repo = repo;
    }

    public void addCategorie(Categorie c) {
        repo.add(c);
    }

    public List<Categorie> getAll() {
        return repo.findAll();
    }

    public Categorie getById(int id) {
        return repo.findById(id);
    }
}
