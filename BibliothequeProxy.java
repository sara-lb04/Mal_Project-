package mini_projet_mal;

//Proxy contrôlant l’accès aux fonctionnalités sensibles de la bibliothèque.
public class BibliothequeProxy {
    private Bibliotheque bibliotheque;
    private Utilisateur utilisateur;

    public BibliothequeProxy(Utilisateur utilisateur) {
        this.bibliotheque = Bibliotheque.getInstance();
        this.utilisateur = utilisateur;
    }

    public void ajouterLivre(Livre livre) {
        if (utilisateur instanceof Admin) {
            bibliotheque.ajouterLivre(livre);
        } else {
            System.out.println("Accès refusé : Seuls les admins peuvent ajouter des livres.");
        }
    }

    public void supprimerLivre(String titre) {
        if (utilisateur instanceof Admin) {
            bibliotheque.supprimerLivre(titre);
        } else {
            System.out.println("Accès refusé : Seuls les admins peuvent supprimer des livres.");
        }
    }

    public void afficherLivres() {
        bibliotheque.afficherLivres();
    }
}