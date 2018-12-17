
public class Main {

	public static void main(String[] args) throws InterruptedException {
		Corde c = new Corde(5);
		Position nord = Position.NORD;
		Position sud = Position.SUD;
		
		for(int i=1; i<=7; i++) {
			new Thread(new Babouin(i, nord, c)).start();;
			new Thread(new Babouin(i, sud, c)).start();
		}
	}

}
