package mini_projet_mal;

//Classe représentant un Lecteur qui peut s’abonner aux notifications.
class Lecteur extends Utilisateur implements Observateur { // Implémente Observateur
	public Lecteur(String nom) { super(nom); }

    @Override
    public void notifier(String message) {
        System.out.println(nom + " a reçu une notification : " + message); // Implémentation de la notification
    }
}