/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */

package calcul;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.ArrayList;


/**
 * Classe pour faire l'analyse des lignes de code pour savoir s'il y a:
 * - du code ou rien
 * - un commentaire end_of_line
 * - le debut d'un bloc de commentaire
 * - la fin d'un bloc de commentaire
 *
 * C'est a vous a gerer a suivre le debut d'un bloc de commentaire pour trouver la fin sur les lignes suivantes
 * car nous retounons seulement la vue d'un signe de debut ou fin dun commentaire bloc.
 * ex: ou 8 est *
 *    /8 debut du commentaire
 *    8  ligne de code
 *    8  ligne de code
 *    8/ fin d'un commentaire
 */

public class AnalyseLigne {
	
	/** liste de bool de l'analyse {code, commentaire , bloc}. */
	private boolean[] resultat;
	
	/** caracteres qui represente les comemntaires end_of_line */
	private ArrayList <String> endOfLine;
	
	/** caracteres qui represente le debut d'un bloc */
	private ArrayList <String> start;
	
	/** caracteres qui represente la fin d'un bloc */
	private ArrayList <String> end;
	
	/** boolean indicant la presence d'un bloc */
	private boolean bloc;
	
	/** int indicant le nombre de commentaires imbriques*/
	private int imbrique;

	/**
	 * Constructeur de analyseLigne
	 * nous allons chercher dans le fichier config les caracteres qui represente les commentaires
	 */
	public AnalyseLigne() {
		this.resultat = new boolean[3]; // {code, commentaire, bloc}
		this.bloc = false;
		this.imbrique = 0;
		this.reset();
		

		/*// loader les properties
		Properties config = new Properties();
		try { 
			FileInputStream fis = new FileInputStream("src/calcul/config.properties");
			config.load(fis);
			
			String commentaire = config.getProperty("CommentaireEndOfLine");
			this.endOfLine = commentaire.split(",");
			commentaire = config.getProperty("CommentaireDebut");
			this.start = commentaire.split(",");
			commentaire = config.getProperty("CommentaireFin");
			this.end = commentaire.split(",");
			
		} catch (IOException io) {
			io.printStackTrace();
		}
*/
		//TODO aller chercher dans le fichier config les caractere qui qui represente un commentaire
		// et les mettre dans les variables de la classe
		
		// hardcode for the moment
		this.endOfLine.add("//");
		this.start.add("/*");
		this.start.add("/**");
		this.end.add("*/");

	}
	
	/** 
	 * Methode pour remettre la liste de resultat a false.
	 */
	private void reset() {
		resultat[0]= false;
		resultat[1]= false;
		resultat[2]= bloc;
	}
	
	
	/**
	 * Methode qui permet d'analyser s'il y a du code ou non
	 * ou s'il y a des commentaire ou non.
	 * Nous cherchons pour les pointeurs des commentaire en fin de ligne
	 * et aussi des bebut et fin de bloc de commentaire
	 * 
	 * @param ligne  String de la ligne de code a analyser
	 * @return  LA liste des resultats trouver dans la ligne 
	 *          {code ou rien, comment ou rien, start bloc, end bloc}
	 */
	public boolean[] analyse(String ligne) {
		
		this.reset();   		//remettre resultat a false
		ligne.trim();			//enlever les espaces de debut de ligne
		
		if (vide(ligne)) return resultat;  	// ligne vide
		resultat[0]= code(ligne);			// ligne avec code	
		resultat[1]= commentaire(ligne);	// ligne avec commentaire
		resultat[2]= bloc;					// presence d'un bloc
		return resultat; 
	
	}
	

	public static void main(String[] args) {
		AnalyseLigne test = new AnalyseLigne();
		
	}

	/* Methode qui prend en parametre une ligne du fichier et retourne le bool indiquant
	 * si la ligne contient du code
	 */
	private boolean code(String ligne) {
		if (bloc==false && commentaireDebut(ligne)==false ) return true;
		return false;
	}
	
	/* Methode qui prend en parametre une ligne du fichier et retourne le bool indiquant
	 * si la ligne contient un commentaire
	 */
	private boolean commentaire (String ligne) {
		boolean blocComment = commentaire(ligne,start); // presence d'un bloc
		boolean endComment = commentaire(ligne,endOfLine); //presence endOfLine
		boolean endBloc = commentaire(ligne,end); // fin d'un bloc
		imbrication(blocComment,endBloc);
		
		if (endComment|| blocComment || endBloc || bloc) return true;
		else return false;
	}
	
	/* Methode qui met a jour le nombre d'imbrication de commentaire et la 
	 * presence d'un bloc. Prend en parametre les bool represent la presence
	 * d'une ouverture de bloc et d'une fermeture de bloc respectivement
	 */
	private void imbrication(boolean blocComment, boolean endBloc) {
		if(blocComment) imbrique++;
		if(endBloc) imbrique--;
		if(imbrique==0) bloc= false; 
		else bloc=true;
		
	}
	
	/* Methode determinant si le String du premier parametre contient un
	 * element de la liste passee en second parametre
	 */
	private boolean commentaire(String ligne, ArrayList <String> symbols) {
		for(int i=0; i<symbols.size(); i++) {
			if (ligne.contains(symbols.get(i))) return true;
		}
		return false;
	}
	
	/* Methode determinant si le String en parametre est vide */
	private boolean vide (String ligne) {
		if(ligne=="") return true;
		else return false;
	}
	
	/* Methode retournant le bool reprensant si le String en parametre contient 
	 * un symbole de commentaire en debut de ligne
	 */
	private boolean commentaireDebut(String ligne) {
		for (int i = 0; i< start.size(); i++) {
			if(ligne.indexOf(start.get(i))== 0) return true;
		}
		return false;
	}

	
}
