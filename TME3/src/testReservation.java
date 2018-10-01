
public class testReservation {

	public static void main(String[] args) {
		Salle s1 = new Salle(10, 10);
		Groupe g1 = new Groupe(8, s1);
		s1.affiche();
		Thread t1 = new Thread(g1);
		t1.start();
		s1.affiche();
	}

}
