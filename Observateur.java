package mini_projet_mal;

//Interface permettant aux objets d’être informés des mises à jour.
public interface Observateur {
    void notifier(String message);
}
