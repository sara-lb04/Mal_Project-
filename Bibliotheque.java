package mini_projet_mal;
import java.util.ArrayList;

public class Bibliotheque {
    private static Bibliotheque instance;
    private ArrayList<Livre> livres;
    private ArrayList<Utilisateur> utilisateurs;
    private ArrayList<Emprunt> emprunts; //  Liste des emprunts
    private NotificationSystem notificationSystem;
    
    
    private Bibliotheque() {
        livres = new ArrayList<>();
        utilisateurs = new ArrayList<>();
        emprunts = new ArrayList<>();
        notificationSystem = new NotificationSystem(); // 

    }

    public static Bibliotheque getInstance() {
        if (instance == null) {
            instance = new Bibliotheque();
        }
        return instance;
    }
    
 // Méthode pour permettre aux observateurs de s'abonner
    public void abonnerObservateur(Observateur obs) {
        notificationSystem.ajouterObservateur(obs);
        // Caster l'Observateur en Utilisateur pour accéder à getNom()
        if (obs instanceof Utilisateur) {
             System.out.println(((Utilisateur)obs).getNom() + " est abonné aux notifications de nouveaux livres.");
        } else {
             System.out.println("Un observateur (non-utilisateur) est abonné aux notifications de nouveaux livres.");
        }

    }

    
    
    // Inscription d’un utilisateur 
    public void inscrireUtilisateur(String nom, String type) {
        UtilisateurCreator creator = null; // Déclarer une variable pour le creator

        // Choisir le ConcreteCreator approprié en fonction du type
        if (type.equalsIgnoreCase("admin")) {
            creator = new AdminCreator(); // Instancier le Creator pour Admin
        } else if (type.equalsIgnoreCase("lecteur")) {
            creator = new LecteurCreator(); // Instancier le Creator pour Lecteur
        } else {
            System.out.println(" Type d’utilisateur invalide !");
            return; 
        }

        // Utiliser la Factory Method du creator sélectionné pour créer l'utilisateur
        // Ici, j'ai appelé creerUtilisateur()
        Utilisateur utilisateur = creator.creerUtilisateur(nom);

        if (utilisateur != null) { // La création réussir si le type est valide
            utilisateurs.add(utilisateur);
            System.out.println(" Utilisateur inscrit : " + utilisateur.getNom());
             // Si l'utilisateur est un Lecteur, l'abonner aux notifications
            if (utilisateur instanceof Lecteur) {
                 abonnerObservateur((Lecteur) utilisateur);
            }
        }
    }
    
    

    //  Vérifier si l’utilisateur est inscrit
    public Utilisateur connecterUtilisateur(String nom) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getNom().equals(nom)) {
                System.out.println("Connexion réussie : " + nom);
                return utilisateur;
            }
        }
        System.out.println("Utilisateur non trouvé !");
        return null;
    }



    public void ajouterLivre(Livre livre) {
        livres.add(livre);
        System.out.println("Livre ajouté : " + livre.getTitre());
        // Notifier les observateurs quand un livre est ajouté
        notificationSystem.notifierTous("Nouveau livre disponible : " + livre.getTitre() + " par " + livre.getAuteur());
    }

    public void supprimerLivre(String titre) {
        for (int i = 0; i < livres.size(); i++) {
            if (livres.get(i).getTitre().equals(titre)) { 
                livres.remove(i);
                System.out.println("Livre supprimé : " + titre);
                return;
            }
        }
        System.out.println("Livre non trouvé !");
    }

	    public ArrayList<Livre> rechercherLivres(String titreRecherche) {
	        ArrayList<Livre> resultats = new ArrayList<>();
	
	        for (Livre livre : livres) {
	            if (livre.getTitre().equals(titreRecherche)) { 
	                resultats.add(livre);
	                }
	            }
	        		return resultats;
	    }
	    

	    public void afficherLivres() {
	        if (livres.isEmpty()) {
	            System.out.println("Aucun livre disponible pour le moment.");
	        } else {
	            System.out.println("Liste des livres disponibles :");
	            for (Livre livre : livres) {
		            System.out.println("- " + livre.getTitre() + " de " + livre.getAuteur());
		            }
		        }
	    }
		    public ArrayList<Livre> getLivres() {
		        return livres;
		     }
	    // Emprunter un livre
	    public void emprunterLivre(Utilisateur utilisateur, String titre) {
	        if (!(utilisateur instanceof Lecteur)) {
	            System.out.println(" Seuls les lecteurs peuvent emprunter des livres.");
	            return;
	        }

	        for (Livre livre : livres) {
	            if (livre.getTitre().equalsIgnoreCase(titre)) { // Vérification insensible à la casse
	                emprunts.add(new Emprunt(utilisateur, livre));
	                livres.remove(livre); //  Retire le livre emprunté de la liste
	                System.out.println(" Livre emprunté par " + utilisateur.getNom() + " : " + livre.getTitre());
	                return;
	            }
	        }

	        System.out.println(" Livre non disponible !");
	    }

	    //  Afficher les emprunts
	    public void afficherEmprunts() {
	        System.out.println("\n📌 Liste des emprunts :");
	        for (Emprunt emprunt : emprunts) {
	            System.out.println(emprunt);
	        }
	    }
	

}