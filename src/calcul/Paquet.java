/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;

import java.io.File;

/**
 * Classe pour calculer les metriques d'un paquet et de ses classes.
 * - compter LOC
 * - compter CLOC
 * - calculer DC
 * 
 * Nous passont au travers de tout ses classes pour ce faire.
 */
public class Paquet {
	
	/** extention du des fichier de code a parcourir*/
	private static String extention = "java";
	
	/** chemin pour trouver le paquet (n'inclut pas celui du paquet)*/
	private File paquet;
	
	//TODO aller chercher les variables dans el fichier properties

	/** Longueur de la liste des metriques du paquet*/
	private int longueur;
	
	/** Liste des metriques du paquet*/
	private String[] resultat;
	
	

	
	/**
	 * Constructeur de la classe  Paquet
	 * 
	 * @param paquet  Objet java.io.File du paquet
	 */
	public Paquet(File paquet) {
		this.paquet = paquet;
		//TODO aller chercher la longeure de la liste des metrique dans properties
		this.resultat = new String[5];
		this.resultat[0] = paquet.getParent();
		this.resultat[1] = paquet.getName();
	}
	
	/**
	 * Methode pour avoir la liste des metriques du paquet et les sauvegarder dans le fichier
	 * 
	 * @return String[] ou le premier element est les metriques du paquet, ensuite de tout les classes.
	 */
	public String[] getMetrique() {
		
		return resultat;
	}
	
	/** 
	 * Methode private pour rechercher dans le paquet chaque classe et lancer le calcul sur chacune d'elle.
	 * 
	 * Sauvegarde dans la variable resultat les totaux des metriques pour le paquet 
	 */
	private void parcourirClasse() {
		
		String[] listeFichier = paquet.list();
		for (String i : listeFichier) {
			
			// recherche l'extention
			int index = i.lastIndexOf('.');
			
			// si c'est pas un dossier, compare a l'extention voulu
			if (index >= 0 && i.substring(index + 1).contentEquals(extention)) {
				
				// demande l'analyse de la classe
				Classe classe = new Classe(paquet.getPath(), i);
				String[] retour = classe.getMetrique();
				addMetriquePaquet(retour);
			}
		}
	}
	
	/** 
	 * Methode private pour ajouter les metriques d'une nouvelle classe trouver au total du paquet
	 * 
	 * @param mClasse  Les metriques d'une classe sous forme String[]
	 */
	private void addMetriquePaquet(String[] mClasse) {
		
		// somme LOC
		resultat[2] = Integer.toString(Integer.valueOf(resultat[2]) + Integer.valueOf(mClasse[2]));
		// somme CLOC
		resultat[3] = Integer.toString(Integer.valueOf(resultat[3]) + Integer.valueOf(mClasse[3]));
		// Calcul DC
		resultat[4] = Double.toString(Double.valueOf(resultat[3]) / Double.valueOf(resultat[2]));
	}
	
	/** 
	 * Sauvegarde les metriques du paquet dans le fichier default pour paquet du programme
	 */
	private void savePaquet() {
		SaveToCsv uniteSauvegarde = new SaveToCsv();
	}
	
	/**
	 * Methode pulbic pour calculer la liste des metriques du paquet et les sauvegarder dans le fichier
	 */
	public void calcul() {
		parcourirClasse();
		System.out.println("debut calcul paquet " + paquet.getName());
		savePaquet();
	}
	

}
