/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe pour calculer les metriques d'une classe.
 * - compter LOC
 * - compter CLOC
 * - calculer DC
 * - calculer WMC
 * - calculer BC
 * 
 * Nous passont au travers de tout ses lignes pour ce faire.
 */
public class Classe {

	/** l'objet File de la classe passer en attribut*/
	private File classe;

	/** Nombre de ligne de code*/
	private int loc;
	
	/** Nombre de ligne de commentaire*/
	private int cloc;
	
	/** Weighted Methods pr Class*/
	private int wmc;
	
	

	/**
	 * Constructeur de la classe Classe
	 * 
	 * @param classe  Fichier File de la classe a analyser
	 */
	public Classe(File classe) {
		this.classe = classe; 
		this.loc = 0;
		this.cloc = 0;
		this.wmc = 0;
				
		analyse(); 	// demande l'analyse de metrique
		
	}
	
	
	
	/** 
	 * Methode private pour sauvegarder les metriques de la classe
	 * dans le fichier de sauvegarde de classe
	 */
	private void saveMetrique() {
		SaveToCsv csvClasse = new SaveToCsv();
		csvClasse.ajoutClasse(getChemin(), getNomClasse(), getLoc(),
				getCloc(), getDc(), getWmc(), getBc());
	}
	
	
	
	/** 
	 * Methode private pour annalyser chaque ligne de la classe
	 * Les metriques de la classe seront sauvegarder dans l'objet
	 */
	private void analyse() {
		try {
			//ouvrir le fichier
		    Scanner lecteur = new Scanner(classe);
		    //analiseur de ligne
		    AnalyseLigne ligne = new AnalyseLigne();
		    
			boolean[] resultat;
			
		    // lecture ligne par ligne
		    while (lecteur.hasNextLine()) {
		    resultat = ligne.analyse(lecteur.nextLine());
		    
		    //enregistrement des resultats
		    if (resultat[1]) {
		    	loc++;
		    	cloc++;
		    }
		    else if (resultat[0]) loc++;
		    if (resultat[2]) wmc++;
		    }
		    
		    // fermer le fichier
		    lecteur.close();
		    
		} catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}

		//sauvegarder les metriques dans le fichier
		saveMetrique();
	}

	/**
	 * Methode pour avoir le chemin du paquet de la classe
	 * 
	 * @return chemin
	 */
	public String getChemin() {
		return classe.getParent();
	}
	
	
	
	/**
	 * Methode pour avoir le nom de la classe
	 * @return nom
	 */
	public String getNomClasse() {
		return classe.getName();
	}

	
	/**
	 * Methode pour avoir le nombre de ligne de code de la classe
	 * @return loc
	 */
	public int getLoc() {
		return loc;
	}

	
	/**
	 * Methode pour avoir le nombre le ligne de commentaire de la classe
	 * @return cloc
	 */
	public int getCloc() {
		return cloc;
	}

	
	/**
	 * Methode pour avoir le Weighted Methods per Class
	 * @return WMC
	 */
	public int getWmc() {
		return wmc;
	}
	
	
	
	/**
	 * Methode pour avoir le la densite de commentaire de la classe
	 *  cloc / loc
	 *
	 * @return DC
	 */
	public double getDc() {
		
		double a = getCloc();
		double b = getLoc();
		double reponse = a / b;
		return reponse;
	}
	
	
	/**
	 * Methode pour avoir le degre selon la classe est bien commentee
	 *  DC / Wmc
	 *
	 * @return  BC
	 */
	public double getBc() {
		
		double a = getDc();
		double b = getWmc();
		if (b == 0) {
			b = 1.0;
		}
		double reponse = a / b;
		return reponse;
	}
	
	
}
