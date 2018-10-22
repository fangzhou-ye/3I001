
public class MainConvoyeur {

	public static void main(String[] args) {

		AleaObjet o1 = new AleaObjet(1, 5);
		AleaObjet o2 = new AleaObjet(1, 5);
		AleaObjet o3 = new AleaObjet(1, 5);
		AleaObjet o4 = new AleaObjet(1, 5);
		AleaObjet o5 = new AleaObjet(1, 5);
		
		AleaStock stock = new AleaStock(5);
		stock.append(o1);
		stock.append(o2);
		stock.append(o3);
		stock.append(o4);
		stock.append(o5);
		stock.affiche();
		
		Chariot chariot = new Chariot(6, 3);
		Thread c = new Thread(new Chargeur(chariot, stock));
		Thread d = new Thread(new Dechargeur(chariot));
		c.start();
		d.start();
	}

}
