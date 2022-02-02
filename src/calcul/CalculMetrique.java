/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;


/**
 * Calcule des metrique de la qualite logiciel par:
 * - la recherche des paquets et classes
 * - compter LOC
 * - compter CLOC
 * - calculer DC
 * 
 * Le calcul sera fait sur la structure qui se trouve dans le dossier data 
 * 
 * La sauvegarde des resultats sous forme de csv de fait dans le dossier resulat, un pour les paquet et un pour les classes
 */
public class CalculMetrique {
	
	/** unite de sauvegarde pour les fichiers csv */
	private static SaveToCsv uniteSauvegarde;
	
	
	/**
	 * sauvegarde des donnee recu dun paquet dans la fonction parcourir
	 */
	private void save(String[] infoPaquet) {
		//TODO sauvegarde des donnee recu dun paquet dans la fonction parcourir
	}
	
	
	/**
	 * Pour parcourir la structure a la recherche des paquets
	 */
	private void parcourir() {
		//TODO parcourir la structure pour trouver les paquets
	}

	/**
	 * entree dans le programme
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		uniteSauvegarde = new SaveToCsv("nomDuDossier");
		System.out.println(uniteSauvegarde.getNomDossier());
	}

}
