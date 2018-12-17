
public class MainBis {

	public static void main(String[] args) throws InterruptedException {
		CordeBis c = new CordeBis(5);
		Position nord = Position.NORD;
		Position sud = Position.SUD;
		
		for(int i=1; i<=9; i++) {
			new Thread(new BabouinBis(i, nord, c)).start();;
			new Thread(new BabouinBis(i, sud, c)).start();
		}
	}

}