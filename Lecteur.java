package mini_projet_mal;

//Classe repr�sentant un Lecteur qui peut s�abonner aux notifications.
class Lecteur extends Utilisateur implements Observateur { // Impl�mente Observateur
	public Lecteur(String nom) { super(nom); }

    @Override
    public void notifier(String message) {
        System.out.println(nom + " a re�u une notification : " + message); // Impl�mentation de la notification
    }
}