
public class Requete {
	private int numRequete;
	private static Object mutex = new Object();
	private static int cpt = 1;
	
	private Client emetteur;
	private int typeRequete;
	
	public Requete(Client c) {
		synchronized(mutex) {
			numRequete = cpt++;
		}
		emetteur = c;
		typeRequete = (emetteur.getNumClient()/3 != 0) ? 1: 2;
	}
}
