package gestioncom.repository.memory;

import gestioncom.entity.Commande;
import java.util.ArrayList;
import java.util.List;

public class CommandeRepository {
    private final List<Commande> commandes = new ArrayList<>();

    public void add(Commande c) {
        commandes.add(c);
    }

    public List<Commande> findAll() {
        return commandes;
    }

    public Commande findById(int id) {
        return commandes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
