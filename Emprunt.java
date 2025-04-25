package mini_projet_mal;

public class Emprunt {
    private Utilisateur lecteur;
    private Livre livre;

    public Emprunt(Utilisateur lecteur, Livre livre) {
        this.lecteur = lecteur;
        this.livre = livre;
    }

    public Utilisateur getLecteur() {
        return lecteur;
    }

    public Livre getLivre() {
        return livre;
    }

    @Override
    public String toString() {
        return "" + lecteur.getNom() + " a emprunté : " + livre.getTitre();
    }
}
