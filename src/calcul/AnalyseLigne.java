/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */

package calcul;


import java.io.IOException;
import java.io.InputStream;
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
	
	/** caracteres qui represente les comemntaires end_of_line */
	private ArrayList <String> endOfLine;
	
	/** caracteres qui represente le debut d'un bloc */
	private ArrayList <String> start;
	
	/** caracteres qui represente la fin d'un bloc */
	private ArrayList <String> end;
	
	/** caracteres qui represente le debut d'une methode */
	private ArrayList <String> startMethod;
	
	/** caracteres qui represente le debut d'un bloc */
	private ArrayList <String> endMethod;
	
	/** caracteres qui represente un noeud predicat */
	private ArrayList <String> predicat;
	
	/** boolean indicant la presence d'un bloc */
	private boolean bloc;
	
	/** int indicant le nombre de commentaires imbriques*/
	private int blocImbrique;
	
	/** int indicant le nombre de commentaires imbriques*/
	private int bracketImbrique;
	
	/** bool indicant la presence de code sur la ligne*/
	private boolean code;
	
	/** bool indicant la presence de commentaire sur la ligne*/
	private boolean commentaire;

	/** bool indicant la presence de wmc sur la ligne*/
	private boolean wmc;

	/**
	 * Constructeur de analyseLigne
	 * nous allons chercher dans le fichier config les caracteres qui represente les commentaires
	 */
	public AnalyseLigne() {
		this.bloc = false;
		this.blocImbrique =   0;
		this.bracketImbrique= 0;
		this.reset();
		
		// Creation des listes de parametres
		
		this.endOfLine = new ArrayList<String>();
		this.start = new ArrayList<String>();
		this.end = new ArrayList<String>();
		this.startMethod = new ArrayList<String>();
		this.endMethod = new ArrayList<String>();
		this.predicat = new ArrayList<String>();
		
		Properties config = new Properties();
		try { 
			InputStream fis = this.getClass().getResourceAsStream("config.properties");
			config.load(fis);
			
			String symbol;
			
			symbol= config.getProperty("CommentaireEndOfLine");
			this.endOfLine = creerListe(symbol.split(","));
			
			symbol = config.getProperty("CommentaireDebut");
			this.start =  creerListe(symbol.split(","));
			
			symbol = config.getProperty("CommentaireFin");
			this.end =  creerListe(symbol.split(","));
			
			symbol = config.getProperty("DebutMethode");
			this.startMethod =  creerListe(symbol.split(","));
			
			symbol = config.getProperty("FinMethode");
			this.endMethod =  creerListe(symbol.split(","));
			
			symbol = config.getProperty("Predicat");
			this.predicat =  creerListe(symbol.split(","));
			
			fis.close();
			
		} catch (IOException io) {
			io.printStackTrace();
		}

	}
	
	/** 
	 * Methode pour remettre les boolean a false.
	 */
	private void reset() {
		this.code = false;
		this.commentaire = false;
		this.wmc = false;
	}
	
	/* Methode qui prend en parametre un tableau de String et retourne
	 * une liste contenant les symbols du tableau
	 */
	private ArrayList <String> creerListe(String[] tab){
		ArrayList <String> liste = new ArrayList<String>();
		
		for (String symbol : tab) {
			symbol = symbol.trim();
			liste.add(symbol);
		}
		return liste;
	}
	
	/**
	 * Methode qui permet d'analyser s'il y a du code ou non
	 * ou s'il y a des commentaire ou non.
	 * Nous cherchons pour les pointeurs des commentaire en fin de ligne
	 * et aussi des bebut et fin de bloc de commentaire
	 * 
	 * @param ligne  String de la ligne de code a analyser
	 */
	public void analyse(String ligne) {
		
		this.reset();   		//remettre resultat a false
		ligne = ligne.trim();			//enlever les espaces de debut de ligne
		
		if (!vide(ligne)) {  	// ligne non vide
			code = code(ligne);			// ligne avec code	
			commentaire = commentaire(ligne);	// ligne avec commentaire
			if (code) wmc = wmc(ligne);	// presence d'un predicat
		} 
	
	}
	
	// 2 FONCTIONS TEST***
	public static void main() {
		AnalyseLigne test = new AnalyseLigne();
		print(test.end);
		
	}
	private static void print(ArrayList<String> list) {
		for(int i=0; i<=list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	/* Methode qui prend en parametre une ligne du fichier et retourne le bool indiquant
	 * si la ligne contient un element de WMC
	 */
	private boolean wmc(String ligne) {
		boolean pred = symbol(ligne, predicat);
		boolean debutMet  = symbol(ligne, startMethod);
		boolean finMet = symbol(ligne, endMethod);
		boolean retour = false;
		
		if (debutMet && bracketImbrique == 1) retour = true;
		if (pred) retour = true;
		if (debutMet) bracketImbrique++;
		if (finMet) bracketImbrique++;
		return retour;
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
		boolean blocComment = symbol(ligne,start); // presence d'un bloc
		boolean endComment = symbol(ligne,endOfLine); //presence endOfLine
		boolean endBloc = symbol(ligne,end); // fin d'un bloc
		imbrication(blocComment,endBloc);
		
		if (endComment|| blocComment || endBloc || bloc) return true;
		else return false;
	}
	
	/* Methode qui met a jour le nombre d'imbrication de commentaire et la 
	 * presence d'un bloc. Prend en parametre les bool represent la presence
	 * d'une ouverture de bloc et d'une fermeture de bloc respectivement
	 */
	private void imbrication(boolean blocComment, boolean endBloc) {
		if(blocComment) blocImbrique++;
		if(endBloc) blocImbrique--;
		if(blocImbrique==0) bloc= false; 
		else bloc=true;	
	}
	
	/* Methode determinant si le String du premier parametre contient un
	 * element de la liste passee en second parametre
	 */
	private boolean symbol(String ligne, ArrayList <String> symbols) {
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
	
	public boolean isCode() {
		return code;
	}

	public boolean isCommentaire() {
		return commentaire;
	}

	public boolean isWmc() {
		return wmc;
	}
}
