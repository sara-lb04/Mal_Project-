package mini_projet_mal;

// Le Creator abstrait
public abstract class UtilisateurCreator {

    // La Factory Method abstraite.
    // Chaque sous-classe concr�te impl�mentera cette m�thode
    // pour cr�er un type sp�cifique d'Utilisateur.
    public abstract Utilisateur creerUtilisateur(String nom);

    public Utilisateur enregistrerNouvelUtilisateur(String nom) {
        Utilisateur utilisateur = creerUtilisateur(nom); // Appel de la Factory Method
        System.out.println("Cr�ation et enregistrement d'un utilisateur : " + utilisateur.getNom());
        return utilisateur;
    }
}
