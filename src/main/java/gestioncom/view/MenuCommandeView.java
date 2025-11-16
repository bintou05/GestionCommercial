package gestioncom.view;

import gestioncom.entity.Commande;
import gestioncom.entity.LigneCommande;
import gestioncom.entity.Produit;
import gestioncom.services.CommandeService;
import gestioncom.services.ProduitService;

import java.util.Scanner;

public class MenuCommandeView {

    private final CommandeService commandeService;
    private final ProduitService produitService;

    public MenuCommandeView(CommandeService commandeService, ProduitService produitService) {
        this.commandeService = commandeService;
        this.produitService = produitService;
    }

    public void afficherMenu() {
        Scanner sc = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n--- BOUTIQUIER ---");
            System.out.println("1 - Enregistrer une commande");
            System.out.println("2 - Lister les commandes");
            System.out.println("3 - Voir les détails d'une commande");
            System.out.println("4 - Quitter");
            System.out.print("Votre choix : ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1 -> enregistrerCommande(sc);
                case 2 -> listerCommandes();
                case 3 -> voirDetails(sc);
                case 4 -> System.out.println("Retour...");
                default -> System.out.println("Choix invalide.");
            }

        } while (choix != 4);
    }

    private void enregistrerCommande(Scanner sc) {
        System.out.print("ID Commande : ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nom du client : ");
        String client = sc.nextLine();
        Commande c = new Commande(id, client);

        String continuer = "o"; // ✅ initialisation obligatoire
        do {
            System.out.print("ID Produit : ");
            int pid = sc.nextInt();
            sc.nextLine();
            Produit p = produitService.getById(pid);
            if (p == null) {
                System.out.println("Produit non trouvé !");
                continue;
            }
            System.out.print("Quantité : ");
            int qte = sc.nextInt();
            sc.nextLine();

            c.addLigne(new LigneCommande(p, qte));

            System.out.print("Ajouter un autre produit ? (o/n) : ");
            continuer = sc.nextLine();
        } while (continuer.equalsIgnoreCase("o"));

        commandeService.addCommande(c);
        System.out.println("Commande enregistrée !");
    }

    private void listerCommandes() {
        System.out.println("\n--- Liste des commandes ---");
        commandeService.getAll().forEach(System.out::println);
    }

    private void voirDetails(Scanner sc) {
        System.out.print("ID Commande : ");
        int id = sc.nextInt();
        sc.nextLine();
        Commande c = commandeService.getById(id);
        if (c == null) {
            System.out.println("Commande introuvable !");
        } else {
            System.out.println("Commande ID " + c.getId() + " - Client : " + c.getClient());
            c.getLignes().forEach(System.out::println);
        }
    }
}
