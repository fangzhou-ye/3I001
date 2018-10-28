import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SegTournant implements Runnable {
	private int pos; // le numero de rangar se tourner vers 0 pour SegAccueil
	private int idLoco; // num loco dans le SegTournant -1 par defaut
	private PoolHangars ph;
	
	private ReentrantLock lock;
	private final Condition attendAppel;
	private final Condition locomotive;
	private final Condition attendPos;
	private final Condition attendEntree;
	private final Condition attendVide;
	
	private boolean appel_ok;
	private boolean pos_ok;
	private boolean entree_ok;
	private boolean vider_ok;
	
	public SegTournant(PoolHangars ph) {
		pos = -1;
		idLoco = -1;
		this.ph = ph;
		lock = new ReentrantLock();
		attendAppel = lock.newCondition();
		locomotive = lock.newCondition();
		attendPos = lock.newCondition();
		attendEntree = lock.newCondition();
		attendVide = lock.newCondition();
		appel_ok = false;
		pos_ok = false;
		entree_ok = false;
		vider_ok = false;
	}
	
	public void trace(String msg) {
		System.out.println("SegTournant : " + msg);
	}
	
	private void attendreAppel() throws InterruptedException {
		lock.lock();
		try {
			this.trace("attend appel de loco");
			while(!appel_ok) {
				attendAppel.await();
			}
		}finally {
			lock.unlock();
		}
	}
	
	public void appeler(int id) throws InterruptedException {
		lock.lock();
		try {
			while(appel_ok) {
				locomotive.await();
			}
			pos = id;
			this.trace("etre appele et tournant vers id " + pos);
			appel_ok = true;
			attendAppel.signalAll();
			pos_ok = false;
		}finally {
			lock.unlock();
		}
	}
	
	private void seDeplacer() throws InterruptedException {
		lock.lock();
		try {
			this.trace("tourner vers " + pos + " dans 3s");
			Thread.sleep(3000);
			pos_ok = true;
			attendPos.signalAll();
		}finally {
			lock.unlock();
		}
	}
	
	public void attendrePositionOK() throws InterruptedException {
		lock.lock();
		try {
			this.trace("attend pos OK");
			while(!pos_ok) {
				attendPos.await();
			}
		}finally {
			lock.unlock();
		}
	}
	
	private void attendreEntree() throws InterruptedException {
		lock.lock();
		try {
			while(!entree_ok) {
				attendEntree.await();
			}
		}finally {
			lock.unlock();
		}
	}
	
	public void entrer(int id) {
		lock.lock();
		try {
			pos = ph.chercheLibre()+1;
			idLoco = id;
			entree_ok = true;
			attendEntree.signalAll();
			this.trace("fait entrer loco " + idLoco);
		}finally {
			lock.unlock();
		}
	}
	
	private void attendreVide() throws InterruptedException {
		lock.lock();
		try {
			while(!vider_ok) {
				attendVide.await();
			}
			vider_ok = false;
		}finally{
			lock.unlock();
		}
	}
	
	public void sortir(int id) {
		lock.lock();
		try {
			vider_ok = true;
			attendVide.signalAll();
			locomotive.signalAll();
			appel_ok = false;
			pos_ok = false;
			entree_ok = false;
			this.trace("fait sortir loco " + id);
			idLoco = -1; //remis le num actuel par defaut
		}finally {
			lock.unlock();
		}
	}
	
	public int getPosition() {
		return pos;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				attendreAppel();
				seDeplacer();
				attendreEntree();
				seDeplacer();
				attendreVide();
			}
		}
		catch (InterruptedException e) {
			System.out.println("Terminaison sur interruption du segment tournant");
		}
	}

}
