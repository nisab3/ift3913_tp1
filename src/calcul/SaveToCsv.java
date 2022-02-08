/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe SaveToCsv pour sauvegarder les resultat des calcules de metriques des paquets et classes
 * dans un fichier paquet_"nomDuDossier".csv et un fichier classe_"nomDuDossier".csv
 * 
 * Les fichiers seront dans le dossier "resultats"
 */
public class SaveToCsv {
	
	/** Chemin du dossier dans lequel se trouve les fichiers de sauvegarde */
	private String dossierSauvegarde;
	/** Le nom qui sera utiliser pour le fichier csv de paquets. */
	private String nomFichierPaquet;
	/** Le nom qui sera utiliser pour le fichier csv de classes. */
	private String nomFichierClasse;
	/** Le nombre de metrique dans une liste */
	private int longueurMetriques;
	/** Entete de la liste des metrique d'un paquet */
	private String[] metriquesPaquet;
	/** Entete de la liste des metrique d'une classe */
	private String[] metriquesClasse;
	
	
	/**
	 * Contructeur de l'unite de sauvegarde
	 * 
	 */
	public SaveToCsv() {
		
		// loader les properties
		Properties config = new Properties();
		try { 
			FileInputStream fis = new FileInputStream("src/calcul/config.properties");
			config.load(fis);
			
			this.dossierSauvegarde = config.getProperty("Sauvegarde") + "/";
			this.nomFichierPaquet = config.getProperty("NomFichierSauvegardeP");
			this.nomFichierClasse = config.getProperty("NomFichierSauvegardeC");
			String metriques = config.getProperty("MetriquesClasse");
			this.metriquesClasse = metriques.split(",");
			metriques = config.getProperty("MetriquesPaquet");
			this.metriquesPaquet = metriques.split(",");
			this.longueurMetriques = this.metriquesPaquet.length;
			
			
			
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		// essais de creer le fichier de sauvegarde des classes si ce n'est pas deja fait
		try {
			// ouvrir le fichier
			File csvClasse = new File(dossierSauvegarde + "/" + nomFichierClasse + ".csv");
			if(csvClasse.createNewFile()) {
				// si on creer le fichier:
				
				FileWriter fw = new FileWriter (csvClasse, true);
				BufferedWriter br = new BufferedWriter(fw);
				
				// Ajouter l'entete
				for (int i = 0; i < longueurMetriques; i++) {
					if (i + 1 == longueurMetriques) {			// pour la fin de ligne
						br.write(metriquesClasse[i] + "\n");
					} else {									// pour tout les autres
						br.write(metriquesClasse[i] + ", ");
					}
				}
				br.close();
				fw.close();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		
		// essais de creer le fichier de sauvegarde pour les paquets
		try {
			// ouvrir le fichier
			File csvPaquet = new File(dossierSauvegarde + "/" + nomFichierPaquet + ".csv");
			if(csvPaquet.createNewFile()) {
				
				// si on creer le fichier:
				FileWriter fw = new FileWriter (csvPaquet, true);
				BufferedWriter br = new BufferedWriter(fw);
				
				// Ajouter l'entete
				for (int i = 0; i < longueurMetriques; i++) {
					if (i + 1 == longueurMetriques) {			// pour la fin de ligne 
						br.write(metriquesPaquet[i] + "\n");
					} else {									// pour tous les autres
						br.write(metriquesPaquet[i] + ", ");
					}
				}
				br.close();
				fw.close();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Ajoute une entree dans le fichier d'enregistrement des classes.
	 * 
	 * @param metriques String[] :  Liste des metriques de la classe comme formater dans le fichier de properties
	 */
	public void ajoutClasse(String[] metriques) {
		
		// savegarde les metrique dans le fichier
		try {
			
			// ouvrir le fichier
			File csvClasse = new File(dossierSauvegarde + "/" + nomFichierClasse + ".csv");
			FileWriter fw = new FileWriter (csvClasse, true);
			BufferedWriter br = new BufferedWriter(fw);
			// pour compter les metriques car a la fin de ligne on mets \n
			int compte = 1;
			
			// ecrire dans le fichier
			for (String i : metriques) {
				br.write(i);
				if(compte == longueurMetriques) {
					br.write("\n");					// si fin de ligne
				} else {
					br.write(",");					// tous les autres
				}
				compte ++;
			}
			br.close();
			fw.close();
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Ajoute une entree dans le fichier d"enregistrement des paquets.
	 * 
	 * @param metriques  String[] :  formater comme les metriques de paquet dans le fichier de properties
	 */
	public void ajoutPaquet(String[] metriques) {

		// savegarde les metrique dans le fichier
		try {
			//ouvrir le fichier
			File csvPaquet = new File(dossierSauvegarde + "/" + nomFichierPaquet + ".csv");
			FileWriter fw = new FileWriter (csvPaquet, true);
			BufferedWriter br = new BufferedWriter(fw);
			
			// pour compter les metriques car a la fin de ligne on mets \n
			int compte = 1;
			
			// ecrire dans le fichier
			for (String i : metriques) {
				br.write(i);
				if(compte == longueurMetriques) {
					br.write("\n");					// si fin de ligne
				} else {
					br.write(",");					// tout les autres
				}
				compte ++;
			}
			br.close();
			fw.close();
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		
		//pour tester avec des fausses metriques.
		SaveToCsv test = new SaveToCsv();
		String[] donnee= {"chemin", "nom", "24", "654", "4.378", "0", "0"};
		test.ajoutClasse(donnee);
		
	}
}
