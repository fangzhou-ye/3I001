import java.util.ArrayList;

public class Dechargeur implements Runnable {
	private Chariot c;
	private ArrayList<AleaObjet> elems;
	
	public Dechargeur(Chariot c) {
		this.c = c;
		elems = null;
	}
	
	public void decharger() {
		elems = new ArrayList<AleaObjet>(c.getElements());
		c.reinitElems();
	}
	
	public void affiche() {
		System.out.println("dechargeur ----------");
		for(AleaObjet obj : elems) {
			System.out.print("[id:" + obj.getId() + " poids:" + obj.getPoids() + "]");
		}
		System.out.println();
		System.out.println();
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				c.decharger(this);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
