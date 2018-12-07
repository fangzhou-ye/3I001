
public class Client implements Runnable {
	private int numClient;
	private static int cpt = 1;
	private static Object mutex = new Object();
	
	private Serveur serveur;
	
	public Client(Serveur serv) {
		synchronized(mutex) {
			numClient = cpt++;
		}
		serveur = serv;
	}
	
	public int getNumClient() {
		return numClient;
	}
	
	public void requeteServie(ReponseRequete r) {
		
	}
	
	@Override
	public void run() {
		serveur.soumettre();
	}
	
}
