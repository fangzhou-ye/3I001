import java.util.ArrayList;
import java.util.Arrays;

public class MainConvoyeur {

	public static void main(String[] args) {
		// initialisation des aleaobjets
		/*AleaObjet o1 = new AleaObjet(1, 5);
		AleaObjet o2 = new AleaObjet(1, 5);
		AleaObjet o3 = new AleaObjet(1, 5);
		AleaObjet o4 = new AleaObjet(1, 5);
		AleaObjet o5 = new AleaObjet(1, 5);
		AleaObjet o6 = new AleaObjet(1, 5);
		
		System.out.println(o1);
		System.out.println(o2);
		System.out.println(o3);
		System.out.println(o4);
		System.out.println(o5);
		System.out.println(o6);
		
		AleaStock stock = new AleaStock(5);
		stock.append(o1);
		stock.append(o2);
		stock.append(o3);
		stock.append(o4);
		stock.append(o5);
		stock.append(o6);
		stock.affiche();*/
		
		ArrayList<Integer> l = new ArrayList<Integer>(5);
		for(int i=0; i<6; i++) {
			l.add(i);
		}
		System.out.println("size: " + l.size());
		System.out.println(Arrays.toString(l.toArray()));
		l.remove(2);
		System.out.println("size: " + l.size());
		System.out.println(Arrays.toString(l.toArray()));
		
	}

}
