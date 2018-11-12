import java.util.Random;

public class Producteur implements Runnable {
	private final int id;
	private static int cpt = 1;
	private static final Object mutex = new Object();
	
	private EnsembleDonnees data;
	private Random rd;
	
	public Producteur(EnsembleDonnees data) {
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
				for(int j=0; j<4; j++) {
					int val = rd.nextInt(100);
					data.ajouter(id, val);
				}
				Thread.sleep(2000);
			}
		}catch(InterruptedException e) {
			System.out.println("Interrupted Exception " + e);
		}
	}
}
