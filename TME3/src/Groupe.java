import java.util.Arrays;
import java.util.Stack;

public class Groupe implements 	Runnable{
	private final int idGroupe;
	private static int cpt = 0;
	private static Object mutex = new Object();
	private final int nbPersonnes;
	private Salle salle;
	private Stack<Paire> places;
	
	public Groupe(int nb, Salle s) {
		synchronized(mutex) {
			idGroupe = cpt;
			cpt++;
			nbPersonnes = nb;
			salle = s;
			places = new Stack<Paire>();
		}
	}

	@Override
	public void run() {
		salle.reserver(this);
		System.out.println("Groupe " + idGroupe + " reserve " + nbPersonnes + " places");
		salle.affiche();
		salle.annuler(1, this);
		System.out.println("Groupe " + idGroupe + " annule " + "1  places");
		salle.affiche();
	}
	
	public int getNbPersonnes() {
		return nbPersonnes;
	}
	
	public Stack<Paire> getPlaces(){
		return places;
	}
	
	public void ajoutePlace(int i, int j) {
		places.push(new Paire(i, j));
	}
	
	public String toString() {
		return Arrays.toString(places.toArray());
	}
	
	public void affiche() {
		System.out.println(this.toString());
	}
}
