/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Classe pour calculer les metriques d'une classe.
 * - compter LOC
 * - compter CLOC
 * - calculer DC
 * 
 * Nous passont au travers de tout ses classes pour ce faire.
 */
public class Classe {

	/** l'objet File de la classe passer en attribut*/
	private File classe;

	
	private boolean[] resultat;
	
	private int loc;
	private int cloc;
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
	 * Methode private pour ajouter les metriques d'une nouvelle classe trouver a la liste des classes
	 */
	private void saveMetrique() {
		SaveToCsv csvClasse = new SaveToCsv();
		csvClasse.ajoutClasse(getChemin(), getNomClasse(), getLoc() , getCloc(), getDc(), getWmc(), getBc());
	}
	
	
	
	/** 
	 * Methode private pour annalyser chaque ligne de la classe
	 * Les metriques de la classe seront sauvegarder dans l'objet
	 */
	private void analyse() {
		//TODO lire toute les ligne et les envoyer a AnalyseLigne et garder le cmpte si on est dans un commentaire ou non
		try {
		      Scanner lecteur = new Scanner(classe);
		      AnalyseLigne ligne = new AnalyseLigne();
		      while (lecteur.hasNextLine()) {
		        resultat = ligne.analyse(lecteur.nextLine());
		        if (resultat[1]) {
		        	loc++;
		        	cloc++;
		        }
		        else if (resultat[0]) loc++;
		        if (resultat[2]) wmc++;
		        
		      }
		      lecteur.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

		//sauvegarder les metriques dans le fichier
		saveMetrique();
	}

	
	public File getClasse() {
		return classe;
	}

	public String getChemin() {
		return classe.getParent();
	}

	public String getNomClasse() {
		return classe.getName();
	}

	public int getLoc() {
		return loc;
	}

	public int getCloc() {
		return cloc;
	}

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
