package mini_projet_mal;

// Le Creator abstrait
public abstract class UtilisateurCreator {

    // La Factory Method abstraite.
    // Chaque sous-classe concrète implémentera cette méthode
    // pour créer un type spécifique d'Utilisateur.
    public abstract Utilisateur creerUtilisateur(String nom);

    public Utilisateur enregistrerNouvelUtilisateur(String nom) {
        Utilisateur utilisateur = creerUtilisateur(nom); // Appel de la Factory Method
        System.out.println("Création et enregistrement d'un utilisateur : " + utilisateur.getNom());
        return utilisateur;
    }
}
