import java.util.concurrent.Semaphore;

public class CordeBis {
	private int capacite;
	private Position sensUtilisation;
	private int cpt;
	private Semaphore mutex;
	private Semaphore condAccess;
	
	public CordeBis(int cap) {
		capacite = cap;
		sensUtilisation = null;
		cpt = 0;
		mutex = new Semaphore(1);
		condAccess= new Semaphore(0, true);
	}

	public void acceder(Position position, BabouinBis b) throws InterruptedException {
		mutex.acquire();
		try {
			while(!((sensUtilisation == null) || (sensUtilisation != null && sensUtilisation == position && cpt < capacite))) {
				mutex.release();
				condAccess.acquire();
				mutex.acquire();
			}
			cpt++;
			if(sensUtilisation == null) {
				sensUtilisation = position;
			}
		} finally {
			mutex.release();
		}
	}

	public void lacher(Position position, BabouinBis b) throws InterruptedException {
		mutex.acquire();
		try {
			cpt--;
			if(cpt == 0) {
				sensUtilisation = null;
				mutex.release();
				condAccess.release(capacite);
				mutex.acquire();
				System.out.println("la corde est vide");
			}
		} finally {
			mutex.release();
		}
	}

}
