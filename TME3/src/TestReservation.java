
public class TestReservation {

	public static void main(String[] args) {
		try {
			Salle s1 = new Salle(5, 5);
			s1.affiche();
			Thread t1 = new Thread(new Groupe(3, s1));
			Thread t2 = new Thread(new Groupe(4, s1));
			Thread t3 = new Thread(new Groupe(3, s1));
			Thread t4 = new Thread(new Groupe(2, s1));
			Thread t5 = new Thread(new Groupe(6, s1));
			Thread t6 = new Thread(new Groupe(4, s1));
			//depasse la capacite de salle
			Thread t7 = new Thread(new Groupe(4, s1));
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			t5.start();
			t6.start();
			t7.start();
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			
			s1.affiche();
		}catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

}
