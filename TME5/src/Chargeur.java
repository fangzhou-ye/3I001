
public class Chargeur implements Runnable{
	private Chariot c;
	private AleaStock stock;
	
	public Chargeur(Chariot c, AleaStock stock) {
		this.c = c;
		this.stock = stock;
	}
	
	@Override
	public void run() {
		c.charger(stock.extrait());
	}
	
}
