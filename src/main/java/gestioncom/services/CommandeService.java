package gestioncom.services;

import gestioncom.entity.Commande;
import gestioncom.repository.memory.CommandeRepository;
import java.util.List;

public class CommandeService {
    private final CommandeRepository repo;

    public CommandeService(CommandeRepository repo) {
        this.repo = repo;
    }

    public void addCommande(Commande c) {
        repo.add(c);
    }

    public List<Commande> getAll() {
        return repo.findAll();
    }

    public Commande getById(int id) {
        return repo.findById(id);
    }
}
