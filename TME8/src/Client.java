import java.util.Random;

public class Client implements Runnable {
	private final int monID;
	private static int cpt = 1;
	private final Object mutex = new Object();
	private Serveur s;
	private Requete req_courant;
	private final Random gen;
	
	public Client(Serveur s) {
		synchronized(mutex) {
			monID = cpt++;
		}
		this.s = s;
		req_courant = null;
		gen = new Random();
	}
	
	public int getMonID() {
		return monID;
	}
	
	private void trace(String msg) {
		System.out.println("Client " + monID + " : " + msg);
	}
	
	public String toString() {
		return "Client " + monID;
	}
	
	public void requeteServie(ReponseRequete r) {
		this.notifyAll();
		req_courant.setReponseRequete(r);
	}
	
	@Override
	public void run() {
		this.trace("initialisé");
		try {
			for(int i=0; i<5; i++) {
				//ToDo
				while(req_courant == null) {
					this.wait();
				}
				int millis = gen.nextInt(3000);
				this.trace("travaille localement pendant " + millis + " ms");
				Thread.sleep(millis);
				req_courant = null;
			}
		} catch(InterruptedException e) {
			System.out.println("Interrupted " + e);
		}
	}

}
