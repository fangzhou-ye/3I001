import java.util.Random;

public class ReponseRequete {
	private final static Random gene = new Random();
	private Client emetteur;
	private final int val;
	
	public ReponseRequete(Client c) {
		val = gene.nextInt(1000);
		emetteur = c;
	}
	
	public String toString() {
		return "Reponse pour " + emetteur + " : " + val;
	}
}
