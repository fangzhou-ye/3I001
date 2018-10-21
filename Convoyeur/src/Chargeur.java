
public class Chargeur implements Runnable {
	private AleaStock stock;
	private Chariot c;
	
	public Chargeur(Chariot c, AleaStock stock) {
		this.stock = stock;
		this.c = c;
	}
	
	public int getObjetPoidsMin() {
		return stock.getPoidsMin();
	}
	
	public AleaObjet prendObjet() {
		if(!stock.estVide()) {
			for(AleaObjet obj : stock.getListObjet()) {
				if(!c.estPlein() && c.getPoidsDispo() >= obj.getPoids()) {
					AleaObjet o = obj.clone();
					stock.getListObjet().remove(obj);
					return o;
				}
			}
			System.out.println("fin de chargement : plus de marchandise approprie");
			return null;
		}
		System.out.println("fin de chargement : stock est vide");
		return null;
	}
	
	@Override
	public void run() {
		try {
			c.charger(this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
