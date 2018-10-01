
public class Groupe implements 	Runnable{
	private final int idGroupe;
	private static int cpt = 0;
	private static Object mutex = new Object();
	private final int nbPersonnes;
	private Salle salle;
	
	public Groupe(int nb, Salle s) {
		synchronized(mutex) {
			idGroupe = cpt;
			cpt++;
			nbPersonnes = nb;
			salle = s;
		}
	}

	@Override
	public void run() {
		salle.reserver(this);
	}
	
	public int getNbPersonnes() {
		return nbPersonnes;
	}
}
