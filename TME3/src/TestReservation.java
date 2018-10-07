
public class TestReservation {

	public static void main(String[] args) {
		try {
			Salle s1 = new Salle(5, 5);
			s1.affiche();
			Thread t0 = new Thread(new Groupe(3, s1));
			Thread t1 = new Thread(new Groupe(5, s1));
			Thread t2 = new Thread(new Groupe(12, s1));
			t0.start();
			t0.join();
			t1.start();
			t1.join();
			t2.start();
			t2.join();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

}
