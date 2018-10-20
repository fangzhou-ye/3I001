
public class Test {

	public static void main(String[] args) throws InterruptedException {
		Salon s = new Salon(5);
		Thread b = new Thread(new Barbier(s));
		b.start();
		for(int i=0; i<3; i++) {
			Thread t = new Thread(new Client(s));
			t.start();
		}
		
		Thread.sleep(15000);
		System.out.println("salon va fermer");
	}

}
