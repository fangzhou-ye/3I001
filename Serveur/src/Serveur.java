
public class Serveur implements Runnable{
	private boolean recu;
	private final Object traiter = new Object();
	
	public Serveur() {
		recu = false;
	}
	
	private void attendreRequete() {
		synchronized()
	}
	
	private void traiterRequete() {
		
	}
	
	public void soumettre() {
		
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				attendreRequete();
				traiterRequete();
			}
		} catch(InterruptedException e) {
			System.out.println("Serveur interrompue");
		}
	}
}
