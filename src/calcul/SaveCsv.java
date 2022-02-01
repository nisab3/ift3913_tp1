package calcul;



/**
 * @author Nicolas Sabourin
 * @author Dave Sanon-Abraham
 *
 */
public class SaveCsv {
	
	private String nomDossier;
	private String nomFichierPaquet;
	private String nomFichierClasse;
	
	/**
	 * @param nomDossier
	 */
	public SaveCsv(String nomDossier) {
		
		this.nomDossier = nomDossier;
		this.nomFichierClasse = "classe_" + nomDossier;
		this.nomFichierPaquet = "paquet_" + nomDossier;
	}
	
	/**
	 * @param chemin
	 * @param classe
	 * @param classe_LOC
	 * @param classe_CLOC
	 * @param classe_DC
	 */
	public void AjoutClasse(String chemin, String classe, int classe_LOC, int classe_CLOC, int classe_DC) {
		
	}
	
	/**
	 * @param chemin
	 * @param paquet
	 * @param paquet_LOC
	 * @param paquet_CLOC
	 * @param paquet_DC
	 */
	public void AjoutPaquet(String chemin, String paquet, int paquet_LOC, int paquet_CLOC, int paquet_DC) {
		
	}
	
	/**
	 * 
	 */
	public void ReadPaquet() {
		System.out.println("Voici le paquet");
	}
	
	/**
	 * 
	 */
	public void ReadClasse() {
		System.out.println("Voici la classet");
	}

	/**
	 * @return
	 */
	public String getNomDossier() {
		return nomDossier;
	}
}
