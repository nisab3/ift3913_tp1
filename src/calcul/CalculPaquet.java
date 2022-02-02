/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;

/**
 * Classe pour calculer les metriques d'un paquet et de ses classes.
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
	public CalculPaquet(String chemin, String nomPaquet) {
		this.chemin = chemin;
		this.nomPaquet = nomPaquet;
	}
	
	/**
	 * Methode pour avoir la liste des metriques du paquet et de ses classes
	 * 
	 * @return String[] ou le premier element est les metriques du paquet, ensuite de tout les classes.
	 */
	public String[] getMetrique() {
		//TODO concatenner les metriques du paquet et des classes
		String[] resultat = {"paquet", "classe", "classe"};
		return resultat;
	}
	
	/** 
	 * Methode private pour recehrcher dans le paquet chaque classe et lancer le calcul sur chacune d'elle.
	 * Les metriques des classes et celle du paquet seront sauvegarder dans l'objet
	 */
	private void parcourir() {
		//TODO rechercher tout les classes et les annalyser une apres l'autre
		//TODO enregistrer les metriques de classes
		//TODO enresistrer les metrique de paquet
	}
	
	/** 
	 * Methode private pour ajouter les metriques d'une nouvelle classe trouver au total du paquet
	 */
	private void addMetriquePaquet(String[] classe) {
		//TODO additionner les metriques de cette classe a celle total du paquet
	}
	
	/** 
	 * Methode private pour ajouter les metriques d'une nouvelle classe trouver a la liste des classes
	 */
	private void addMetriqueClasse(String[] classe) {
		//TODO additionner les metriques de cette classe a la liste des classes
	}

}
