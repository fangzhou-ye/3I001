
public class Lecteur implements Runnable {
	private final int id;
	private static int cpt = 1;
	private static final Object mutex = new Object();
	
	private EnsembleDonnees data;
	
	public Lecteur(EnsembleDonnees data) {
		synchronized(mutex) {
			id = cpt++;
		}
		this.data = data;
	}
	
	@Override
	public void run() {
		try {
			for(int i=0; i<3; i++) {
				data.lire(id);
				Thread.sleep(2000);
			}
		}catch(InterruptedException e) {
			System.out.println("Interrupted Exception " + e);
		}
	}

}
