/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe pour calculer les metriques d'un paquet et de ses classes.
 * - compter LOC
 * - compter CLOC
 * - calculer DC
 * 
 * Nous passont au travers de tout ses classes pour ce faire.
 */
public class Paquet {
	
	/** extention du des fichier de code a parcourir*/
	private String extension;
	
	/** File qui represente le paquet a analyser*/
	private File paquet;
	
	/** nombre le lignes de code*/
	private int loc;
	
	/** nombre le lignes de commentaire*/
	private int cloc;
	
	/** Weighted classes per package*/
	private int wcp;

	
	/**
	 * Constructeur de la classe  Paquet
	 * 
	 * @param paquet  Objet java.io.File du paquet
	 */
	public Paquet(File paquet) {
		this.paquet = paquet;
		
		// loader les properties
		Properties config = new Properties();
		try { 
			FileInputStream fis = new FileInputStream("src/calcul/config.properties");
			config.load(fis);
			this.extension = config.getProperty("Extension");	
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		this.cloc = 0;
		this.loc = 0;
		this.wcp = 0;
		
		calcul();
		
	}
	

	
	/**
	 * Methode pour avoir le nombre le ligne de code dans le paquet
	 * 
	 * @return the paquet_LOC
	 */
	public int getPaquetLoc() {
		return loc;
	}



	/**
	 * Methode pour avoir le nombre le ligne de commentaire dans le paquet
	 * 
	 * @return the paquet_CLOC
	 */
	public int getPaquetCloc() {
		return cloc;
	}



	/**
	 * methode pour avoir le Weighted classes per package.  la somme WMC des tout les classes
	 *  et de tout les sous paquets.
	 *
	 * @return the wcp
	 */
	public int getWcp() {
		return wcp;
	}

	
	
	/**
	 * Methode pour avoir le la densite de commentaire du paquet
	 *  cloc / loc
	 *
	 * @return DC
	 */
	public double getPaquetDc() {
		
		double a = getPaquetCloc();
		double b = getPaquetLoc();
		double reponse = a / b;
		return reponse;
	}
	
	
	
	/**
	 * Methode pour avoir le degre selon le paquet est bien commentee
	 *  DC / WPC
	 *
	 * @return  BC
	 */
	public double getPaquetBc() {
		
		double a = getPaquetDc();
		double b = getWcp();
		double reponse = a / b;
		return reponse;
	}
	
	
	
	/**
	 * Methode pour avoir le chemin de ou se trouve le paquet
	 *
	 * @return chemin
	 */
	public String getChemin() {
		return paquet.getParent();
	}
	
	
	
	/**
	 * Methode pour avoir le nom du paquet
	 *
	 * @return Nom
	 */
	public String getNom() {
		return paquet.getName();
	}

	
	
	/*
	 * Methode private pour rechercher dans le paquet chaque classe et lancer le calcul sur chacune d'elle.
	 * 
	 * Sauvegarde dans la variable resultat les totaux des metriques pour le paquet 
	 */
	private void parcourir() {
		
		String[] listeFichier = paquet.list();
		
		// si la liste n'est pas vide nous allons cherche les dossier et fichier code
		if ((listeFichier != null) && (listeFichier.length >= 1)) {
			
			for (String i : listeFichier) {
				
				// recherche l'extention
				int index = i.lastIndexOf('.');
				
				// si c'est pas un dossier, compare a l'extention voulu
				if (index >= 0 && i.substring(index + 1).contentEquals(extension)) {
					File cl = new File(paquet.getPath() + "/" + i);
					Classe classe = new Classe(cl);
					System.out.println(paquet.getPath() + "/" + i);
					addMetriqueClasse(classe.getLoc(), classe.getCloc(), classe.getWmc());		
				}
				
				// si cest un dossier on parcourt 
				else {
					File filep = new File(paquet.getPath() + "/" + i + "/");
					Paquet p = new Paquet(filep);
					addMetriqueSousPaquet(p.getWcp());
				}	
			}
		}

	}
	
	/*
	 * Methode private pour ajouter les metriques d'une nouvelle classe trouver au total du paquet
	 * 
	 * @param loc  le nombre de ligne de code
	 * @param cloc  le nombre de ligne de commentaire
	 * @param wmc le weighted methods per class de la classe
	 */
	private void addMetriqueClasse(int loc, int cloc, int wmc) {
		this.loc += loc;
		this.cloc += cloc;
		this.wcp += wmc;	
	}
	
	
	
	/*
	 * Methode private pour ajouter les metriques d'un sous paquet trouver au total du paquet
	 * 
	 * @param wmc le weighted Classes per package du sous paquet
	 */
	private void addMetriqueSousPaquet(double wcp) {
		this.wcp += wcp;
	}
	
	
	
	/*
	 * Sauvegarde les metriques du paquet dans le fichier default pour paquet du programme.
	 * La sauvegar6de se fera seulement si le paquet contient du code (classe)
	 * Sinon il ne se passera rien
	 */
	private void savePaquet() {
		if (this.loc > 0) {
			SaveToCsv uniteSauvegarde = new SaveToCsv();
			uniteSauvegarde.ajoutPaquet(getChemin(), getNom(), getPaquetLoc(), getPaquetCloc(),
					getPaquetDc(), getWcp(), getPaquetBc());
		}
	}
	
	/**
	 * Methode pulbic pour calculer la liste des metriques du paquet et les sauvegarder dans le fichier
	 */
	private void calcul() {
		parcourir();
		System.out.println("debut calcul paquet " + paquet.getName());
		savePaquet();
	}
	

}
