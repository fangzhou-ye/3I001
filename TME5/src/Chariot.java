import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class Chariot {
	private int poidMax;
	private int nbMax;
	private ArrayList<AleaObjet> list;
	private ReentrantLock lock;
	private Condition estVide;
	private Condition estPlein;
	
	public Chariot(int poidMax, int nbMax) {
		this.poidMax = poidMax;
		this.nbMax = nbMax;
		list = new ArrayList<AleaObjet>();
		lock = new ReentrantLock();
		estVide = lock.newCondition();
		estPlein = lock.newCondition();
	}
	
	public void empiler(AleaObjet obj) {
		list.add(obj);
	}
	
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	public void charger(AleaObjet obj) {
		lock.lock();
		try {
			
		}catch(InterruptedException e) {
			
		}finally {
			lock.unlock();
		}
	}
}
