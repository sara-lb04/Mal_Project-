package mini_projet_mal;

// ConcreteCreator pour les Admins
public class AdminCreator extends UtilisateurCreator {

    @Override
    public Utilisateur creerUtilisateur(String nom) {
        return new Admin(nom);
    }
}