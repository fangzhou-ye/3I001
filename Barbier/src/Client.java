
public class Client implements Runnable {
	private Salon s;
	private int id;
	private static int cpt = 0;
	private final Object mutex = new Object();
	
	public Client(Salon s) {
		synchronized(mutex){
			id = ++cpt;
		}
		this.s = s;
	}
	
	public void trace(String msg) {
		System.out.println("Client " + id + " : " + msg);
		Thread.yield();
	}
	
	public Salon getSalon() {
		return s;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		s.entrer(this);
	}

}
