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
		
		File racine = new File(chemin);
		String[] listeFichier = racine.list();
		
		// si la liste n'est pas vide nous allons cherche les dossier et fichier code
		if ((listeFichier != null) && (listeFichier.length >= 1)) {
			
			for (String i : listeFichier) {
				
				// recherche l'extention
				int index = i.lastIndexOf('.');
				
				// si c'est pas un dossier, compare a l'extention voulu
				if (index >= 0 && i.substring(index + 1).contentEquals(extension)) {
					// demande l'analyse de la classe
					File cl = new File(racine.getPath() + "/" + i);
					Classe classe = new Classe(cl);		
					System.out.println(classe.getNomClasse());
				}
				
				// si cest un dossier on parcourt 
				else {	
					File filep = new File(racine.getPath() + "/" + i + "/");
					Paquet paquet = new Paquet(filep);
					System.out.println(paquet.getNom());
				}	
			}
			
		  // si ya rien dans le dossier
		} else {
			System.out.println("rien dans le dossier " + racine.getPath());
		}
	}

	/**
	 * entree dans le programme
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("bienvenue dans le programme de calcul de metrique");
		Scanner myObj = new Scanner(System.in);  // Creer le sanner
	    System.out.println("Quel est le dossier a analyser :");

	    data = myObj.nextLine();  // lire le input
		parcourir(data);
		myObj.close();
		System.out.println("FINI");
		
	}

}
 