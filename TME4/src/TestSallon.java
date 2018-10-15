
public class TestSallon {

	public static void main(String[] args) {
		try {
			Sallon s = new Sallon(5);
			s.affiche();
			Thread barbier = new Thread(new Barbier(s));
			Thread client1 = new Thread(new Client(s));
			Thread client2 = new Thread(new Client(s));
			Thread client3 = new Thread(new Client(s));
			Thread client4 = new Thread(new Client(s));
			barbier.start();
			barbier.join();
			client1.start();
			client1.join();
			client2.start();
			client2.join();
			client3.start();
			client3.join();
			client4.start();
			client4.join();
			barbier.interrupt();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
