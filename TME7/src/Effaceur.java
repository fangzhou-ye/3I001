import java.util.Random;

public class Effaceur implements Runnable {
	private final int id;
	private static int cpt = 1;
	private static final Object mutex = new Object();
	
	private EnsembleDonnees data;
	private Random rd;
	
	public Effaceur(EnsembleDonnees data) {
		synchronized(mutex) {
			id = cpt++;
		}
		this.data = data;
		rd = new Random();
	}
	
	@Override
	public void run() {
		try {
			for(int i=0; i<3; i++) {
				int val = rd.nextInt(100);
				data.supprimer(id, val);
				Thread.sleep(2000);
			}
		}catch(InterruptedException | DataNotFoundException e) {
			System.out.println("Exception " + e);
		}
	}
}
