/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;

/**
 * Classe pour calculer les metriques d'un paquet.
 * - compter LOC
 * - compter CLOC
 * - calculer DC
 * 
 * Nous passont au travers de tout ses classes pour ce faire.
 */
public class CalculPaquet {
	
	/** chemin pour trouver le paquet (n'inclut pas celui du paquet)*/
	private String chemin;
	
	/** Nom du paquet*/
	private String nomPaquet;
	
	/** Liste des metriques de classes*/
	private String[] classes;
	
	/** Liste des metriques du paquet*/
	private String[] paquet;

	/**
	 * Constructeur de la classe  CalculPaquet
	 * 
	 * @param chemin  Le chemin a partir du dossier source du projet pour trouver le paquet (n'inclut pas celui du paquet)
	 * @param nomPaquet  Nom du paquet
	 */
	public calculPaquet(String chemin, String nomPaquet) {
		super();
		this.chemin = chemin;
		this.nomPaquet = nomPaquet;
	}
	
	public get
	
}
