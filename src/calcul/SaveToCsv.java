/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;

/**
 * Classe SaveToCsv pour sauvegarder les resultat des calcules de metriques des paquets et classes
 * dans un fichier paquet_"nomDuDossier".csv et un fichier classe_"nomDuDossier".csv
 * 
 * Les fichiers seront dans le dossier "resultats"
 */
public class SaveToCsv {
	
	private String nomDossier;
	private String nomFichierPaquet;
	private String nomFichierClasse;
	
	/**
	 * Contructeur de l'unite de sauvegarde
	 * 
	 * @param nomDossier  Le nom du dossier racine du projet
	 */
	public SaveToCsv(String nomDossier) {
		
		this.nomDossier = nomDossier;
		
		this.nomFichierClasse = "classe_" + nomDossier; 
		//TODO creer le fichier csv pour classe
		//TODO ajouter l'entete au fichier de classe
		
		this.nomFichierPaquet = "paquet_" + nomDossier;
		//TODO creer le fichier csv pour paquet
		//TODO ajouter l'entete au fichier de paquet
	}
	
	/**
	 * Ajoute une entree dans le fichier d'enregistrement des classes.
	 * 
	 * @param chemin  Chemin du dossier source du fichier de la classe.
	 * @param classe  Nom du fichier de la classe.
	 * @param classe_LOC  Nombre de lignes de code de la classe.
	 * @param classe_CLOC  Nombre de lignes de code qui contiennent des commentaires.
	 * @param classe_DC  Densite de comemntaire pour une classe: classe_DC = classe_CLOC / classe_LOC.
	 */
	public void AjoutClasse(String chemin, String classe, int classe_LOC, int classe_CLOC, int classe_DC) {
		//TODO ouvrir le fichier classe et lui ajouter la ligne pour encsuite le refermer
	}
	
	/**
	 * Ajoute une entree dans le fichier d"enregistrement des paquets.
	 * 
	 * @param chemin  Chemin du dossier source du paquet.
	 * @param paquet  Nom du dossier (paquet).
	 * @param paquet_LOC  Nombre de lignes de code du paquet : la somme des LOC de ses classes.
	 * @param paquet_CLOC  Nombre de lignes de code qui contiennent des commentaires.
	 * @param paquet_DC  Densite de comemntaire pour le paquet: paquet_DC = paquet_CLOC / paquet_LOC.
	 */
	public void AjoutPaquet(String chemin, String paquet, int paquet_LOC, int paquet_CLOC, int paquet_DC) {
		//TODO ouvrir le fichier paquet et lui ajouter la ligne pour encsuite le refermer
	}
	
	/**
	 * Imprimer dans la console le contenu du fichier de sauvegarde des paquets.
	 */
	public void ReadPaquet() {
		//TODO println de tout le fichier de paquet
		System.out.println("Voici le paquet");
	}
	
	/**
	 * Imprimer dans la console le contenu du fichier de sauvegarde des classes.
	 */
	public void ReadClasse() {
		//TODO println de tout le fichier de classe
		System.out.println("Voici la classet");
	}

	
	
	/**
	 * Pour verifier que nous avons bien la bonne unite de sauvegarde.
	 * 
	 * @return String contenant le nom du dossier racine
	 */
	public String getNomDossier() {
		return nomDossier;
	}
}
