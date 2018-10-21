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
	private boolean stock_ok;
	
	private final Lock lock = new ReentrantLock();
	private final Condition chargement = lock.newCondition();
	private final Condition dechargement = lock.newCondition();
	
	public Chariot(int poidsMax, int nbMax) {
		this.poidsMax = poidsMax;
		this.nbMax = nbMax;
		nb_ok = true;
		poids_ok = true;
		stock_ok = true;
		nb_dispo = this.nbMax;
		poids_dispo = this.poidsMax;
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
	
	public ArrayList<AleaObjet> getElements(){
		return elements;
	}
	
	public void reinitElems() {
		elements = new ArrayList<AleaObjet>(nbMax);
	}
	
	public boolean estPlein() {
		return nb_dispo == 0;
	}
	
	public void affiche() {
		System.out.println("     chariot ----------");
		for(AleaObjet obj : elements) {
			System.out.print("     [id:" + obj.getId() + " poids:" + obj.getPoids() + "]");
		}
		System.out.println();
		System.out.println();
	}
	
	public void charger(Chargeur c) throws InterruptedException {
		lock.lock();
		try {
			while(!nb_ok) {
				System.out.println("nb non ok");
				chargement.await();
			}
			while(!poids_ok) {
				System.out.println("poids non ok");
				chargement.await();
			}
			while(!stock_ok) {
				System.out.println("stock non ok");
				chargement.await();
			}
			AleaObjet obj = c.prendObjet();
			elements.add(obj);
			System.out.println("Deplacer de " + obj);
			c.getstock().affiche();
			this.affiche();
			nb_dispo--;
			poids_dispo-=obj.getPoids();
			if(nb_dispo == 0) {
				nb_ok = false;
				dechargement.signalAll();
			}
			if(poids_dispo < c.getObjetPoidsMin()) {
				poids_ok = false;
				dechargement.signalAll();
			}
			if(c.getNbStockRestant() == 0) {
				stock_ok = false;
				dechargement.signalAll();
			}
		}finally {
			lock.unlock();
		}
	}
	
	public void decharger(Dechargeur d) throws InterruptedException {
		lock.lock();
		try {
			while(poids_ok && nb_ok) {
				System.out.println("Dechargeur attend le chariot");
				dechargement.await();
			}
			d.decharger();
			d.affiche();
			nb_dispo = this.nbMax;
			poids_dispo = this.poidsMax;
			poids_ok = true;
			nb_ok = true;
			chargement.signalAll();
		}finally {
			lock.unlock();
		}
	}
}
