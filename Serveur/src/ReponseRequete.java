import java.util.Random;

public class ReponseRequete {
	private Client emetteur;
	private int numRequete;
	private static int cpt = 1;
	private int val;
	private Random gene =  new Random();
	
	public ReponseRequete() {
		numRequete = cpt++;
		val = gene.nextInt(1000);
	}
	
	public String toString() {
		return "(Reponse " + numRequete + " : " + val + ") renvoye a " + emetteur;
	}
}
