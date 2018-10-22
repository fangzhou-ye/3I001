import java.util.Random;

public class AleaObjet {
	private final int id;
	private static int cpt = 0;
	private final static Object mutex = new Object();
	private final int poids;
	private Random rd;
	private int min;
	private int max;
	
	public AleaObjet(AleaObjet obj) {
		this.id = obj.getId();
		this.poids = obj.getPoids();
	}
	
	public AleaObjet(int min, int max) {
		synchronized(mutex) {
			id = ++cpt;
		}
		rd = new Random();
		this.min = min;
		this.max = max;
		poids = rd.nextInt(max-min+1)+min;
	}
	
	/*public AleaObjet(int id, int poids) {
		this.id = id;
		this.poids = poids;
	}*/
	
	public int getId() {
		return id;
	}
	
	public int getPoids() {
		return poids;
	}
	
	public AleaObjet clone() {
		return new AleaObjet(this);
	}
	
	public String toString() {
		return "objet " + id + " dont poids " + poids;
	}
	
	
	
}
