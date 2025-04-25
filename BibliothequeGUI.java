package mini_projet_mal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BibliothequeGUI extends JFrame {
    private Bibliotheque bibliotheque;
    private JTextField titreField, auteurField, rechercheField, nomField;
    private JComboBox<String> roleComboBox;
    private JTextArea livresArea;
    private Utilisateur utilisateurConnecte;
    private JButton emprunterBtn;
    
    
    public BibliothequeGUI() {
    	
    	bibliotheque = Bibliotheque.getInstance();

        setTitle("Bibliothèque Universitaire");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        //Initialisation des champs
        nomField = new JTextField(10);
        roleComboBox = new JComboBox<>(new String[] {"admin", "lecteur"});
        
        
        JButton inscriptionBtn = new JButton("Inscription");
        JButton connexionBtn = new JButton("Connexion");
        
     // Action de l'inscription
        inscriptionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText();
                String role = (String) roleComboBox.getSelectedItem();
                
                if (nom.isEmpty() || role.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
                    return; // Arrêter si un champ est vide
                }
                
                if (!role.equals("admin") && !role.equals("lecteur")) {
                    JOptionPane.showMessageDialog(null, " Rôle invalide ! Choisissez admin ou lecteur.");
                    return;
                }
                
                // Inscription dans la bibliothèque
                bibliotheque.inscrireUtilisateur(nom, role);
                JOptionPane.showMessageDialog(null, "Utilisateur inscrit : " + nom);
            }
        });
        
        
        connexionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomField == null || bibliotheque == null) {
                    JOptionPane.showMessageDialog(null, "Erreur interne : les objets ne sont pas initialisés !");
                    return;
                }

                String nom = nomField.getText(); 

                if (nom.isEmpty()) {
                    JOptionPane.showMessageDialog(null, " Veuillez entrer un nom !");
                    return;
                }

                // Connexion de l'utilisateur
                utilisateurConnecte = bibliotheque.connecterUtilisateur(nom);

                if (utilisateurConnecte != null) {
                    JOptionPane.showMessageDialog(null, "Connexion réussie : " + utilisateurConnecte.getNom());
                } else {
                    JOptionPane.showMessageDialog(null, " Utilisateur non trouvé !");
                }
            }
        });
        
        JButton emprunterBtn = new JButton("Emprunter Livre");
        emprunterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (utilisateurConnecte instanceof Lecteur) {
                    String titre = rechercheField.getText();
                    if (!titre.isEmpty()) {
                        bibliotheque.emprunterLivre(utilisateurConnecte, titre);
                        JOptionPane.showMessageDialog(null, " Livre emprunté : " + titre);
                        afficherLivres(); //  Met à jour l'affichage des livres disponibles
                    }
                } else {
                    JOptionPane.showMessageDialog(null, " Seuls les lecteurs peuvent emprunter des livres.");
                }
            }
        });

        
        // Champs de saisie pour ajouter un livre
        titreField = new JTextField(15);
        auteurField = new JTextField(15);
        JButton ajouterBtn = new JButton("Ajouter Livre");

        // Champs de recherche et de suppression
        rechercheField = new JTextField(15);
        JButton rechercherBtn = new JButton("Rechercher Livre");
        JButton supprimerBtn = new JButton("Supprimer Livre");

        // Zone d'affichage des livres
        livresArea = new JTextArea(15, 40);
        livresArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(livresArea);

     
        // Rechercher un livre
        rechercherBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recherche = rechercheField.getText();
                if (!recherche.isEmpty()) {
                    rechercherLivre(recherche);
                }
            }
        });

        // Supprimer un livre
        supprimerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(utilisateurConnecte instanceof Admin)) { // Vérifie si l'utilisateur n'est pas un Admin
                    JOptionPane.showMessageDialog(null, " Seuls les admins peuvent supprimer des livres.");
                    return; // STOP l'exécution après l'affichage du message
                }

                String titre = rechercheField.getText();
                if (!titre.isEmpty()) {
                    bibliotheque.supprimerLivre(titre);
                    afficherLivres();
                }
            }
        });

     // Empêcher les Lecteurs d'ajouter des livres
        ajouterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (utilisateurConnecte instanceof Admin) { // Vérifie si c’est un Admin
                    String titre = titreField.getText();
                    String auteur = auteurField.getText();
                    if (!titre.isEmpty() && !auteur.isEmpty()) {
                        bibliotheque.ajouterLivre(new Livre(titre, auteur));
                        afficherLivres();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seuls les admins peuvent ajouter des livres.");
                }
            }
        });

        // Empêcher les Lecteurs de supprimer des livres
        supprimerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (utilisateurConnecte instanceof Admin) { // Vérifie si c’est un Admin
                    String titre = rechercheField.getText();
                    if (!titre.isEmpty()) {
                        bibliotheque.supprimerLivre(titre);
                        afficherLivres();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seuls les admins peuvent supprimer des livres.");
                }
            }
        });
        
        // Permettre aux Lecteurs d'emprunter des livres
        emprunterBtn = new JButton("Emprunter Livre");

        // Ajout de l'action
        emprunterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (utilisateurConnecte instanceof Lecteur) {
                    String titre = rechercheField.getText();
                    if (!titre.isEmpty()) {
                        bibliotheque.emprunterLivre(utilisateurConnecte, titre);
                        JOptionPane.showMessageDialog(null, " Livre emprunté : " + titre);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seuls les lecteurs peuvent emprunter des livres.");
                }
            }
        });


        // Ajout des composants
       
        
        add(new JLabel("Nom :"));
        add(nomField);
        add(new JLabel("Rôle :"));
        add(roleComboBox);

        add(inscriptionBtn);
        add(connexionBtn);
        
        add(new JLabel("Titre:"));
        add(titreField);
        add(new JLabel("Auteur:"));
        add(auteurField);
        add(ajouterBtn);
        add(new JLabel("Rechercher/Supprimer:"));
        add(rechercheField);
        add(rechercherBtn);
        add(supprimerBtn);
        add(scrollPane);

        afficherLivres();
        setVisible(true);
        add(emprunterBtn);
    }

    // Affichage des livres
    private void afficherLivres() {
        livresArea.setText("");
        bibliotheque.afficherLivres();
    }

    // Recherche d'un livre
    private void rechercherLivre(String titre) {
        ArrayList<Livre> livresTrouves = bibliotheque.rechercherLivres(titre);
        livresArea.setText("");
        for (Livre livre : livresTrouves) {
            livresArea.append(livre.getTitre() + " - " + livre.getAuteur() + "\n");
        }
    }

    // Suppression d'un livre
    private void supprimerLivre(String titre) {
        bibliotheque.supprimerLivre(titre);
    }

    public static void main(String[] args) {
        new BibliothequeGUI();
    }
}
