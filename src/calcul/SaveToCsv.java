/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe SaveToCsv pour sauvegarder les resultat des calcules de metriques des paquets et classes
 * dans un fichier .csv chacun.
 * 
 * Les fichiers seront dans le dossier defini dans les properties
 */
public class SaveToCsv {
	
	
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
			
			InputStream fis = this.getClass().getResourceAsStream("config.properties");
			config.load(fis);
			
			this.nomFichierPaquet = config.getProperty("NomFichierSauvegardeP");
			this.nomFichierClasse = config.getProperty("NomFichierSauvegardeC");
			String metriques = config.getProperty("MetriquesClasse");
			this.metriquesClasse = metriques.split(",");
			metriques = config.getProperty("MetriquesPaquet");
			this.metriquesPaquet = metriques.split(",");
			this.longueurMetriques = this.metriquesPaquet.length;
			
			
			fis.close();
			
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		// essais de creer le fichier de sauvegarde des classes si ce n'est pas deja fait
		try {
			// ouvrir le fichier
			File csvClasse = new File(nomFichierClasse + ".csv");
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
			File csvPaquet = new File( nomFichierPaquet + ".csv");
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
	 * @param chemin  String Le paquet de la classe
	 * @param nom     String Le nom de la classe
	 * @param loc     Int Le nombre de ligne de code de la classe
	 * @param cloc    Int Le nombre de ligne de commentaire de la classe
	 * @param dc      Double La densite de commentaire
	 * @param wmc     Int Le weighted Methods per Class
	 * @param bc      Double Le degre selon lequel la classe est bien commente
	 */
	public void ajoutClasse(String chemin, String nom, int loc, int cloc, double dc, int wmc, double bc) {
		
		// savegarde les metrique dans le fichier
		try {
			
			// ouvrir le fichier
			File csvClasse = new File(nomFichierClasse + ".csv");
			FileWriter fw = new FileWriter (csvClasse, true);
			BufferedWriter br = new BufferedWriter(fw);
				
			// changer tout les \ du chemin en . 
			String cheminClean;
			if (chemin != null) {
				cheminClean = chemin.replaceAll("\\\\", ".");
			} else {
				cheminClean = "";
			}
			
						
			// ecrire dans le fichier
			br.write(cheminClean + ",");
			br.write(nom + ",");
			br.write(Integer.toString(loc) + ",");
			br.write(Integer.toString(cloc) + ",");
			br.write(Double.toString(dc) + ",");
			br.write(Integer.toString(wmc) + ",");
			br.write(Double.toString(bc) + "\n");
			
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
	 * @param chemin  String Le chemin du paquet
	 * @param nom     String Le nom du paquet
	 * @param loc     Int Le nombre de ligne de code du paquet
	 * @param cloc    Int Le nombre de ligne de commentaire du paquet
	 * @param dc      Double La densite de commentaire
	 * @param wcp     Int Le weighted classes per package
	 * @param bc      Double Le degre selon lequel le paquet est bien commente
	 */
	public void ajoutPaquet(String chemin, String nom, int loc, int cloc, double dc, int wcp, double bc) {

		// savegarde les metrique dans le fichier
		try {
			//ouvrir le fichier
			File csvPaquet = new File( nomFichierPaquet + ".csv");
			FileWriter fw = new FileWriter (csvPaquet, true);
			BufferedWriter br = new BufferedWriter(fw);
			
			// changer tout les \ du chemin en . 
			String cheminClean = chemin.replaceAll("\\\\", ".");
			
			// ecrire dans le fichier
			br.write(cheminClean + ",");
			br.write(nom + ",");
			br.write(Integer.toString(loc) + ",");
			br.write(Integer.toString(cloc) + ",");
			br.write(Double.toString(dc) + ",");
			br.write(Integer.toString(wcp) + ",");
			br.write(Double.toString(bc) + "\n");
			
			br.close();
			fw.close();
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
