/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;

import java.io.File;
import java.util.Scanner;


/**
 * Calcule des metrique de la qualite logiciel par:
 * - la recherche des paquets et classes
 * - compter LOC
 * - compter CLOC
 * - calculer DC
 * - calculer WMC\WCP
 * - calculer BC
 * 
 * Le calcul sera fait sur la structure qui se trouve dans le dossier racine dans le fichier properties
 */
public class CalculMetrique {
	
	
	/** Nom du fichier source du projet a calculer*/
	private static String data;
	
	/** Extension des classes a analyser*/
	private static String extension = "java";
	
	/**
	 * Pour parcourir recurcivement la structure a la recherche des paquets
	 * 
	 * @param chemin  String du chemin ou commencer a parcourir
	 */
	private static void parcourir(String chemin) {
		
		
		// recherche l'extention
		int index = chemin.lastIndexOf('.');
		// si c'est pas un dossier, compare a l'extention voulu
		if (index >= 0 && chemin.substring(index + 1).contentEquals(extension)) {
			// demande l'analyse de la classe
			File racine = new File(chemin);
			Classe classe = new Classe(racine);		
			System.out.println(classe.getNomClasse());
		}
		// si cest un dossier on parcourt 
		else {	
		
			File filep = new File(chemin + "/");
			Paquet paquet = new Paquet(filep);
			System.out.println(paquet.getNom());
		}	
		
	}

	/**
	 * entree dans le programme
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("bienvenue dans le programme de calcul de metrique");
//		Scanner myObj = new Scanner(System.in);  // Creer le sanner
	    System.out.println("Quel est le dossier a analyser ou le fichier de la classe (avec l'extention) :");

//	    data = myObj.nextLine();  // lire le input
	    data = "jfreechart";
		parcourir(data);
//		myObj.close();
		System.out.println("FINI");
		
	}

}
 