
public class Hangar {
	private final int id;
	private int idLoco;
	private static final Object mutex = new Object();
	private static int cpt = 0;
	
	public Hangar() {
		synchronized(mutex) {
			id = ++cpt;
		}
		this.idLoco = -1;
	}
	
	public int getLoco() {
		return idLoco;
	}
	
	public void setLoco(int i) {
		this.idLoco = i;
	}
	
	public String toString() {
		return "Hangar " + id + " porte loco " + idLoco; 
	}
	
	public void entrer(int id) {
		idLoco = id;
		System.out.println("loco " + id + " entrer dans le hangar " + this.id);
	}
}
