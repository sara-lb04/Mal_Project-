package mini_projet_mal;

// ConcreteCreator pour les Lecteurs(factory method)
public class LecteurCreator extends UtilisateurCreator {

    @Override
    public Utilisateur creerUtilisateur(String nom) {
        return new Lecteur(nom);
    }
}