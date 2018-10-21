
public class Chargeur implements Runnable {
	private AleaStock stock;
	private Chariot c;
	
	public Chargeur(Chariot c, AleaStock stock) {
		this.stock = stock;
		this.c = c;
	}
	
	public AleaStock getstock() {
		return stock;
	}
	
	public int getObjetPoidsMin() {
		return stock.getPoidsMin();
	}
	
	public int getNbStockRestant() {
		return stock.getListObjet().size();
	}
	
	/*
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
	}*/
	
	public AleaObjet prendObjet() {
		AleaObjet obj = stock.getListObjet().get(0).clone();
		stock.getListObjet().remove(0);
		return obj;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				c.charger(this);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
