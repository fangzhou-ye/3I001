
public class Loco implements Runnable {
	private int id;
	private final static Object mutex = new Object();
	private static int cpt = 1;
	private SegAccueil sAccueil;
	private SegTournant sTournant;
	private PoolHangars pHangars;
	
	public Loco(SegAccueil sAccueil, SegTournant sTournant, PoolHangars pHangars) {
		synchronized(mutex) {
			id = cpt++;
		}
		this.sAccueil = sAccueil;
		this.sTournant = sTournant;
		this.pHangars = pHangars;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		try {
			sAccueil.reserver();
			sTournant.appeler(0);
			sTournant.attendrePositionOK();
			sTournant.entrer(id);
			sAccueil.liberer();
			sTournant.attendrePositionOK();
			pHangars.getHangar( sTournant.getPosition() ).entrer(id);
			sTournant.sortir(id);
		}catch(InterruptedException e) {
			System.out.println("Loco " + id + " interrompue (ne devrait pas arriver)");
		}
	}

}
