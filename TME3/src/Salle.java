import java.util.ArrayList;
import java.util.Hashtable;

public class Salle {
	private final int nbRang;
	private final int nbPlacesParRang;
	private boolean[][] placesLibres;
	private Hashtable t;
	
	public Salle(int nbRang, int nbPlacesParRang) {
		this.nbRang = nbRang;
		this.nbPlacesParRang = nbPlacesParRang;
		placesLibres = new boolean[this.nbRang][this.nbPlacesParRang];
		for(int i=0; i<this.nbRang; i++) {
			for(int j=0; j<this.nbPlacesParRang; j++) {
				placesLibres[i][j] = true;
			}
		}
	}
	
	public String toString() {
		String s = "";
		for(int i=0; i<nbRang; i++) {
			for(int j=0; j<nbPlacesParRang; j++) {
				if(placesLibres[i][j]) {
					s += "O ";
				}else {
					s += "F ";
				}
			}
			s += "\n";
		}
		return s;
	}
	
	public void set(boolean val, int i, int j) {
		placesLibres[i][j] = val;
	}
	
	public void affiche() {
		System.out.println(this.toString());
	}
	
	private int getNbLibre() {
		int cpt = 0;
		for(int i=0; i<nbRang; i++) {
			for(int j=0; j<nbPlacesParRang; j++) {
				if(placesLibres[i][j])
					cpt++;
			}
		}
		return cpt;
	}
	
	private boolean capaciteOK(int n) {
		return this.getNbLibre() >= n;
	}
	
	private int nContiguesAuRangI(int n, int i) {
		for(int j=0; j<nbPlacesParRang; j++) {
			int cpt = 0;
			while(cpt+j<nbPlacesParRang && placesLibres[i][j+cpt] && cpt<=n-1) {
				cpt++;
			}
			if(cpt == n) {
				cpt--;
				//effectuer la réservation de n places contigues au rang i
				while(cpt>=0) {
					placesLibres[i][j+cpt] = false;
					cpt--;
				}
				return j;
			}
		}
		return -1;
	}
	
	public boolean reserverContigues(int n) {
		for(int i=0; i<nbRang; i++) {
			if(nContiguesAuRangI(n, i) != -1)
				return true;
		}
		return false;
	}
	
	private void reserverNonContigues(int n) {
		int cpt = 0;
		for(int i=0; i<nbRang; i++) {
			for(int j=0; j<nbPlacesParRang; j++) {
				if(placesLibres[i][j]) {
					placesLibres[i][j] = false;
					cpt++;
					if(cpt == n)
						return;
				}
			}
		}
	}
	
	private boolean reserver(int n) {
		if(!capaciteOK(n)) {
			System.out.println("depasse la capacite de salle");
			return false;
		}else if(reserverContigues(n)) {
			return true;
		}else {
			reserverNonContigues(n);
			return true;
		}
	}
	
	public synchronized boolean reserver(Groupe g) {
		return reserver(g.getNbPersonnes());
	}
}
