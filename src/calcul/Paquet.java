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
	
	/** chemin pour trouver le paquet (n'inclut pas celui du paquet)*/
	private String chemin;
	
	//TODO aller chercher les variables dans el fichier properties
	/** type de separateur*/
	private String separator;
	
	/** Nom du paquet*/
	private String nomPaquet;
	
	
	/** Liste des metriques du paquet*/
	private String[] paquet;

	/**
	 * Constructeur de la classe  Paquet
	 * 
	 * @param paquet  Objet java.io.File du paquet
	 */
	public Paquet(File paquet) {
		this.chemin = paquet.getParent();
		this.nomPaquet = paquet.getName();
	}
	
	/**
	 * Methode pour avoir la liste des metriques du paquet et les sauvegarder dans le fichier
	 * 
	 * @return String[] ou le premier element est les metriques du paquet, ensuite de tout les classes.
	 */
	public String[] getMetrique() {
		//TODO concatenner les metriques du paquet et sauvegarder
		String[] resultat = {"paquet", "classe", "classe"};
		return resultat;
	}
	
	/** 
	 * Methode private pour rechercher dans le paquet chaque classe et lancer le calcul sur chacune d'elle.
	 * 
	 */
	private void parcourirClasse() {
		
		File racine = new File(chemin + separator + nomPaquet);
		String[] listeFichier = racine.list();
		for (String i : listeFichier) {
			
		}
		
		//TODO enresistrer les metrique de paquet
	}
	
	/** 
	 * Methode private pour ajouter les metriques d'une nouvelle classe trouver au total du paquet
	 */
	private void addMetriquePaquet(String[] classe) {
		//TODO additionner les metriques de cette classe a celle total du paquet
	}
	
	/**
	 * Methode pulbic pour calculer la liste des metriques du paquet et les sauvegarder dans le fichier
	 */
	public void calcul() {
		//TODO appeler parcourir classe
		System.out.println("debut calcul paquet " + nomPaquet);
		
	}
	

}
