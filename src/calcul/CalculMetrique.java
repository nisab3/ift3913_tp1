/**
 * 
 */
package calcul;


/**
 * @author sylve
 *
 */
public class CalculMetrique {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SaveCsv csv1 = new SaveCsv("nom");
		System.out.println(csv1.getNomDossier());
	}

}
