
public class SegAccueil {
	private final int id = 0;
	private boolean occupe;
	
	public SegAccueil() {
		occupe = false;
	}
	
	public void setOccupe(boolean b) {
		occupe = b;
	}
	
	public synchronized void reserver() throws InterruptedException {
		while(occupe) {
			this.wait();
		}
		occupe = true;
		System.out.println("reserver SegAccueil");
	}
	
	public synchronized void liberer() throws InterruptedException {
		while(!occupe) {
			this.wait();
		}
		this.notifyAll();
		occupe = false;
		System.out.println("liberer SegAccueil");
	}
}
