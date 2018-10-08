
public class Client implements Runnable{
	private final int id;
	private static int cpt = 0;
	private static Object mutex = new Object();
	private Sallon s;
	
	public Client(Sallon s) {
		synchronized(mutex) {
			id = cpt;
			cpt++;
			this.s = s;
		}
	}
	
	public String toString() {
		return "client " + id;
	}
	
	public int getId() {
		return id;
	}
	
	public synchronized void entrer() {
		if(s.estPlein()) {
			System.out.println("Salle est plein !!!");
		}
		if(s.estVide()) {
			// réveil du barbier
			s.notifyAll();
		}
		s.appendClient(this);
		s.set(s.getNbDispo()-1);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		entrer();
		try {
			// blocage du client
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
