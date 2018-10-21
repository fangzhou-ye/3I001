import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chariot {
	private final int poidsMax;
	private final int nbMax;
	private int nb_dispo;
	private int poids_dispo;
	private ArrayList<AleaObjet> elements;
	
	private boolean nb_ok;
	private boolean poids_ok;
	
	private final Lock lock = new ReentrantLock();
	private final Condition chargement = lock.newCondition();
	private final Condition dechargement = lock.newCondition();
	
	public Chariot(int poidsMax, int nbMax) {
		this.poidsMax = poidsMax;
		this.nbMax = nbMax;
		nb_ok = true;
		poids_ok = true;
		nb_dispo = nbMax;
		poids_dispo = poidsMax;
		elements = new ArrayList<AleaObjet>(nbMax);
	}
	
	public int getNbDispo() {
		return nb_dispo;
	}
	
	public int getPoidsDispo() {
		return poids_dispo;
	}
	
	public void setPoidsDispo(int p) {
		poids_dispo = p;
	}
	
	public boolean estPlein() {
		return nb_dispo == 0;
	}
	
	public void charger(Chargeur c) throws InterruptedException {
		lock.lock();
		try {
			while(!nb_ok) {
				chargement.await();
			}
			while(!poids_ok) {
				chargement.await();
			}
			AleaObjet obj = c.prendObjet();
			if(obj != null) {
				elements.add(obj);
				nb_dispo--;
				poids_dispo-=obj.getPoids();
				if(nb_dispo == 0) {
					nb_ok = false;
				}
				if(poids_dispo < c.getObjetPoidsMin()) {
					poids_ok = false;
				}
			}else {
				poids_ok = false;
				//ToDo: signalAll sur dechargement
			}
		}finally {
			lock.unlock();
		}
	}
}
