import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Corde {
	private int capacite;
	private final Lock lock = new ReentrantLock();
	private final Condition accessOK = lock.newCondition();
	private Position sensUtilisation;
	private int cpt;
	
	public Corde(int cap) {
		capacite = cap;
		sensUtilisation = null;
		cpt = 0;
	}

	public void acceder(Position position, Babouin b) throws InterruptedException {
		lock.lock();
		try {
			while(!((sensUtilisation == null) || (sensUtilisation != null && sensUtilisation == position && cpt < capacite))) {
				accessOK.await();
			}
			cpt++;
			if(sensUtilisation == null) {
				sensUtilisation = position;
			}
		} finally {
			lock.unlock();
		}
	}

	public void lacher(Position position, Babouin b) {
		lock.lock();
		try {
			cpt--;
			if(cpt == 0) {
				sensUtilisation = null;
				accessOK.signalAll();
				System.out.println("la corde est vide");
			}
		} finally {
			lock.unlock();
		}
	}

}
