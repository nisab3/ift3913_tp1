/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
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
		SaveToCsv csv1 = new SaveToCsv("nom");
		System.out.println(csv1.getNomDossier());
	}

}
