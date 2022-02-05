/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


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
	
	/** Nom du fichier source du projet a calculer*/
	private static String data;
	
	
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
		
//		BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
//		while ((row = csvReader.readLine()) != null) {
//		    String[] data = row.split(",");
//		    // do something with the data
//		}
//		csvReader.close();
		
		File arbre = new File("fichier/");
		String[] listeFichier = arbre.list();
		System.out.println(listeFichier[0]);
		File arbre2 = new File("fichier/"+listeFichier[0]);
		System.out.println(arbre2.getAbsolutePath());
		String[] listeFichier2 = arbre2.list();
		System.out.println(listeFichier2[0]);
		int index = listeFichier2[0].lastIndexOf('.');
		if (index > 0) {
			System.out.println(listeFichier2[0].substring(index + 1));
		}
			
		
//		uniteSauvegarde = new SaveToCsv("nomDuDossier");
//		System.out.println(uniteSauvegarde.getNomDossier());
	}

}
 