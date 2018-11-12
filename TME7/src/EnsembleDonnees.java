import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EnsembleDonnees {
	private ArrayList<Integer> donnees;
	private final ReadWriteLock lock;
	
	public EnsembleDonnees() {
		donnees = new ArrayList<Integer>();
		lock = new ReentrantReadWriteLock();
	}
	
	public void ajouter(int id, int val) {
		lock.writeLock().lock();
		try {
			System.out.println("producteur " + id + " ajoute " + val);
			donnees.add(val);
		}finally {
			lock.writeLock().unlock();
		}
	}
	
	public void supprimer(int id, int val) throws DataNotFoundException {
		lock.writeLock().lock();
		try {
			int idx = donnees.lastIndexOf(val);
			System.out.println("Effaceur " + id + " supprime " + val);
			if(idx == -1) {
				throw new DataNotFoundException(val + " not found");
			}else {
				donnees.remove(idx);
			}
		}finally {
			lock.writeLock().unlock();
		}
	}
	
	public void lire(int id) {
		lock.readLock().lock();
		try {
			System.out.println("lecteur " + id + " lire les donnees");
			for(int i=0; i<donnees.size(); i++) {
				System.out.println("donnees[" + i + "] = " + donnees.get(i));
			}
		}finally {
			lock.readLock().unlock();
		}
	}
}
