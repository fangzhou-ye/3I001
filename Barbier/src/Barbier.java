
public class Barbier implements Runnable {
	private Salon s;
	
	public Barbier(Salon s) {
		this.s = s;
	}
	
	public void trace(String msg) {
		System.out.println("Barbier : " + msg);
		Thread.yield();
	}
	
	@Override
	public void run() {
		while(true) {
			s.prendClient(this);
		}
	}

}
