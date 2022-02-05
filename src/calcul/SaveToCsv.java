/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;

import java.io.File;
import java.io.FileWriter;

/**
 * Classe SaveToCsv pour sauvegarder les resultat des calcules de metriques des paquets et classes
 * dans un fichier paquet_"nomDuDossier".csv et un fichier classe_"nomDuDossier".csv
 * 
 * Les fichiers seront dans le dossier "resultats"
 */
public class SaveToCsv {
	
	/** Chemin du dossier dans lequel se trouve les fichiers de sauvegarde */
	private String dossierSauvegarde = "sauvegarde" + '/';
	/** Le nom qui sera utiliser pour le fichier csv de paquets. */
	private String nomFichierPaquet;
	/** Le nom qui sera utiliser pour le fichier csv de classes. */
	private String nomFichierClasse;
	/** Le nombre de metrique dans une liste */
	private int longueurMetrique = 5;
	
	
	/**
	 * Contructeur de l'unite de sauvegarde
	 * 
	 */
	public SaveToCsv() {
		
		
		
		this.nomFichierClasse = "classes"; 
		//TODO creer le fichier csv pour classe
		//TODO ajouter l'entete au fichier de classe
		
		this.nomFichierPaquet = "paquets";
		//TODO creer le fichier csv pour paquet
		//TODO ajouter l'entete au fichier de paquet
	}
	
	/**
	 * Ajoute une entree dans le fichier d'enregistrement des classes.
	 * 
	 * @param metriques String[] :
	 * - Chemin du dossier source du fichier de la classe.
	 * - classe  Nom du fichier de la classe.
	 * - classe_LOC  Nombre de lignes de code de la classe.
	 * - classe_CLOC  Nombre de lignes de code qui contiennent des commentaires.
	 * - classe_DC  Densite de comemntaire pour une classe: classe_DC = classe_CLOC / classe_LOC.
	 */
	public void ajoutClasse(String[] metriques) {
		// essais de creer le fichier de sauvegarde si ce n'est pas deja fait
		try {
			// ouvrir le dossier
			File csvClasse = new File(dossierSauvegarde + "/" + nomFichierClasse + ".csv");
			if(csvClasse.createNewFile()) {
				//TODO ajouter l'entete au nouveau fichier
			} 
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		// savegarde les metrique dans le fichier
		try {
			FileWriter sauvegarde = new FileWriter (dossierSauvegarde + "/" + nomFichierClasse + ".csv");
			int compte = 1;
			
			for (String i : metriques) {
				sauvegarde.write(i);
				if(compte == longueurMetrique) {
					sauvegarde.write("\n");
				}
				else {
					sauvegarde.write(",");
				}
			}
			
			sauvegarde.close();
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
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
	public void ajoutPaquet(String chemin, String paquet, int paquet_LOC, int paquet_CLOC, int paquet_DC) {
		//TODO ouvrir le fichier paquet et lui ajouter la ligne pour encsuite le refermer
	}
	
	/**
	 * Imprimer dans la console le contenu du fichier de sauvegarde des paquets.
	 */
	public void readPaquet() {
		//TODO println de tout le fichier de paquet
		System.out.println("Voici le paquet");
	}
	
	/**
	 * Imprimer dans la console le contenu du fichier de sauvegarde des classes.
	 */
	public void readClasse() {
		//TODO println de tout le fichier de classe
		System.out.println("Voici la classet");
	}

	public static void main(String[] args) {
		SaveToCsv test = new SaveToCsv();
		String[] donnee= {"chemin", "nom", "24", "654", "4.378"};
		test.ajoutClasse(donnee);
		
	}
}
