package mini_projet_mal;

//Interface permettant aux objets d��tre inform�s des mises � jour.
public interface Observateur {
    void notifier(String message);
}
