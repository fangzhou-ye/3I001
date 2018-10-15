import java.util.Random;
public class AleaObjet {
	private int poid;
	
	public AleaObjet(int min, int max) {
		Random rd = new Random();
		poid = rd.nextInt(max-min+1)+min;
	}
	
	public int getPoid() {
		return poid;
	}
	
	public String toString() {
		return "Poids : " + poid;
	}
	
	public void afficher() {
		System.out.println(this.toString());
	}
}
