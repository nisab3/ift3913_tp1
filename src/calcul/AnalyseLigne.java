/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
package calcul;
import java.util.Arrays;
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
	
	/** liste de bool de l'analyse {code ou rien, comment end_of_line, start bloc, end bloc}. */
	private boolean[] resultat;
	
	/** caracteres qui represente les comemntaires end_of_line */
	private String[] endOfLine;
	
	/** caracteres qui represente le debut d'un bloc */
	private String[] start;
	
	/** caracteres qui represente la fin d'un bloc */
	private String[] end;

	/**
	 * Constructeur de analyseLigne
	 * nous allons chercher dans le fichier config les caracteres qui represente les commentaires
	 */
	public AnalyseLigne() {
		this.resultat = new boolean[4];
		this.reset();
		
		//TODO aller chercher dans le fichier config les caractere qui qui represente un commentaire
		// et les mettre dans les variables de la classe
	}
	
	/** 
	 * Methode pour remettre la liste de resultat a false.
	 */
	private void reset() {
		Arrays.fill(resultat, Boolean.FALSE);
	}
	
	
	/**
	 * Methode qui permet d'analyser s'il y a du code ou non
	 * ou s'il y a des commentaire ou non.
	 * Nous cherchons pour les pointeurs des commentaire en fin de ligne
	 * et aussi des bebut et fin de bloc de commentaire
	 * 
	 * @param ligne  String de la ligne de code a analyser
	 * @return  LA liste des resultats trouver dans la ligne {code ou rien, comment end_of_line, start bloc, end bloc}
	 */
	public boolean[] analyse(String ligne) {
		this.reset(); 					//remettre resultat a false
		//TODO lire la ligne pour retourner ce qui est trouver
		return resultat;
	}
	
	
}
