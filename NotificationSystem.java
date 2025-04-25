package mini_projet_mal;

import java.util.ArrayList;

//Système de notification pour informer les lecteurs des nouveaux livres.
public class NotificationSystem {
 private ArrayList<Observateur> observateurs = new ArrayList<>();

 public void ajouterObservateur(Observateur obs) {
     observateurs.add(obs);
 }

 public void notifierTous(String message) {
     for (Observateur obs : observateurs) {
         obs.notifier(message);
     }
 }
}