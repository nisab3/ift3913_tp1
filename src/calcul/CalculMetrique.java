/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


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
	
	/** extention du des fichier de code a parcourir*/
	private static String extension;
	
	
	/**
	 * Pour parcourir recurcivement la structure a la recherche des paquets
	 * 
	 * @param chemin  String du chemin ou commencer a parcourir
	 */
	private static void parcourir(String chemin) {
		//TODO parcourir la structure pour trouver les paquets
		
		File racine = new File(chemin);
		String[] listeFichier = racine.list();
		
		// verifier si on est dans le dossier racine
		// car s'il y a des fichier code ils ne seront pas dans un paquet.
		Boolean dossierRacine = false;
		if (racine.getName().contentEquals(data)) {
			dossierRacine = true;
		}
		
		// si la liste n'est pas vide nous allons cherche les dossier et fichier code
		if ((listeFichier != null) && (listeFichier.length >= 1)) {
			
			// si on a trouver un fichier code = true
			Boolean fichierCode = false;
			
			for (String i : listeFichier) {
				
				// recherche l'extention
				int index = i.lastIndexOf('.');
				
				// si c'est pas un dossier, compare a l'extention voulu
				if (index >= 0 && i.substring(index + 1).contentEquals(extension)) {
					fichierCode = true;			
				}
				
				// si cest un dossier on parcourt 
				else {					
					parcourir(chemin + i + "/");
				}	
			}
			
			// si on a trouver un fichier de code on calcul
			if (fichierCode) {
			
				if (dossierRacine == false) {					// pas dans la racine
					Paquet paquet = new Paquet(racine);
					paquet.calcul();
					System.out.println("fini calcul paquet " + racine.getPath());
					
				} else {										// dans la racine
					for (String i : listeFichier) {
						
						// recherche l'extention
						int index = i.lastIndexOf('.');
						
						// si c'est pas un dossier, compare a l'extention voulu
						if (index >= 0 && i.substring(index + 1).contentEquals(extension)) {
							
							// demande l'analyse de la classe
							File cl = new File(racine.getPath() + "/" + i);
							Classe classe = new Classe(cl);
							String[] retourInutil = classe.getMetrique();
						}
					}
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
		
		// loader les properties
		Properties config = new Properties();
		try { 
			FileInputStream fis = new FileInputStream("src/calcul/config.properties");
			config.load(fis);
			data = config.getProperty("Root") + '/';
			extension = config.getProperty("Extension");
			
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		

		System.out.println("bienvenue dans le programme de calcul de metrique");
		parcourir(data);	
	}

}
 