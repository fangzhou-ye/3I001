import java.util.Random;

public class Babouin implements Runnable {
	private final int monID;
	private Corde laCorde;
	private Position position;
	private final Random gene = new Random();
	
	public Babouin(int id, Position pos, Corde c) {
		monID = id;
		position = pos;
		laCorde = c;
	}
	
	@Override
	public void run() {
		try {
			laCorde.acceder(position, this);
			System.out.println(this.toString() + " a pris la corde.");
			traverser();
			System.out.println(this.toString() + " est arrive.");
			laCorde.lacher(position, this);
		} catch(InterruptedException e) {
			System.out.println("Pb babouin !");
		}
	}

	private void traverser() throws InterruptedException {
		Thread.sleep(gene.nextInt(3000) + 2000);
	}
	
	public String toString() {
		return "Babouin " + monID + " : " + position;
	}

}
