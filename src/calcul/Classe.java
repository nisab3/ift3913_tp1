/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;

/**
 * Classe pour calculer les metriques d'une classe.
 * - compter LOC
 * - compter CLOC
 * - calculer DC
 * 
 * Nous passont au travers de tout ses classes pour ce faire.
 */
public class Classe {

	/** chemin pour trouver la classe (n'inclut pas le nom de la classe)*/
	private String chemin;
	
	/** Nom de la classe*/
	private String nomClasse;
	
	/** Liste des metriques de classe*/
	private String[] classe;

	/**
	 * Constructeur de la classe Classe
	 * 
	 * @param chemin  Le chemin a partir du dossier source du projet pour trouver la classe (n'inclut pas la classe)
	 * @param nomClasse  Le nom du fichier de la classe
	 */
	public Classe(String chemin, String nomClasse) {
		this.chemin = chemin;
		this.nomClasse = nomClasse;
		this.classe = new String[5];   //TODO remplacer le 5 par la longueur de al structure dans le fichier config
		this.classe[0] = chemin;
		this.classe[1] = nomClasse;
	}
	
	/** 
	 * Methode private pour ajouter les metriques d'une nouvelle classe trouver a la liste des classes
	 */
	private void saveMetrique(String[] ligne) {
		//TODO sauvegarde metrique dans le fichier 
	}
	
	/** 
	 * Methode private pour annalyser chaque ligne de la classe
	 * Les metriques de la classe seront sauvegarder dans l'objet
	 */
	private void analyse() {
		//TODO lire toute les ligne et les envoyer a AnalyseLigne et garder le cmpte si on est dans un commentaire ou non
		//TODO enregistrer les metriques de la classe
		
	}
	
	/**
	 * Methode pour avoir la liste des metriques de la classe
	 * 
	 * @return String[] Les metriques de la classe
	 */
	public String[] getMetrique() {
		String[] fake = {"chemin", "classe", "10", "10", "10", "10", "10"};
		return fake;
	}
	
	
	
}
