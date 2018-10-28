
public class Main {

	public static void main(String[] args) {
		SegAccueil sa=new SegAccueil();
		PoolHangars ph=new PoolHangars(5);
		SegTournant t = new SegTournant(ph);
		Thread st = new Thread(t);
		st.start();
		for(int i=0; i<5; i++) {
			new Thread(new Loco(sa, t, ph)).start();
		}
	}

}
