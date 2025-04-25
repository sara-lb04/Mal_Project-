package mini_projet_mal;

public class Livre {
	 private String titre;
	 private String auteur;

 public Livre(String titre, String auteur) {
     this.titre = titre;
     this.auteur = auteur;
 }

 public String getTitre() { 
	 return titre; }
 public String getAuteur() { 
	 return auteur; }
}