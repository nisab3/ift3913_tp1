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
	
	/** Les metriques de classe*/
	private String chemin;
	
	private boolean[] resultat;
	

	private String nomClasse;
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
		analyse(); 	// demande l'analyse de metrique
		
		
		/*// loader les properties
		Properties config = new Properties();
		try { 
			FileInputStream fis = new FileInputStream("src/calcul/config.properties");
			config.load(fis);
			
			String metriques = config.getProperty("MetriquesClasse");
			this.metriquesClasse = metriques.split(",");
			
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		for (int i = 0 ; i < this.metriquesClasse.length; i++) {
			switch (i){
				case 0 : this.metriquesClasse[i] = classe.getParent();
				case 1 : this.metriquesClasse[i] = classe.getName();
				default : this.metriquesClasse[i] = "0";
			}
		}*/
	}
	
	/** 
	 * Methode private pour ajouter les metriques d'une nouvelle classe trouver a la liste des classes
	 */
	private void saveMetrique() {
		SaveToCsv csvClasse = new SaveToCsv();
		//csvClasse.ajoutClasse(metriquesClasse);
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
		        if (resultat[0]) loc++;
		        if (resultat[1]) cloc++;
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
	
	/**
	 * Methode pour avoir la liste des metriques de la classe
	 * 
	 * @return String[] Les metriques de la classe
	 */
	public String[] getMetrique() {
		
		// return metriquesClasse;
		String[] fake = {"chemin", "classe", "10", "10", "10", "10", "10"};
		return fake;
	}
	
	public File getClasse() {
		return classe;
	}

	public String getChemin() {
		return chemin;
	}

	public String getNomClasse() {
		return nomClasse;
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
	
	
}
