import java.util.Stack;

public class Salle {
	private final int nbRang;
	private final int nbPlacesParRang;
	private boolean[][] placesLibres;
	
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
	
	private int nContiguesAuRangI(int n, int i, Groupe g) {
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
					g.ajoutePlace(i, j+cpt);
					cpt--;
				}
				return j;
			}
		}
		return -1;
	}
	
	public boolean reserverContigues(int n, Groupe g) {
		for(int i=0; i<nbRang; i++) {
			if(nContiguesAuRangI(n, i, g) != -1)
				return true;
		}
		return false;
	}
	
	private void reserverNonContigues(int n, Groupe g) {
		int cpt = 0;
		for(int i=0; i<nbRang; i++) {
			for(int j=0; j<nbPlacesParRang; j++) {
				if(placesLibres[i][j]) {
					placesLibres[i][j] = false;
					g.ajoutePlace(i, j);
					cpt++;
					if(cpt == n)
						return;
				}
			}
		}
	}
	
	private boolean reserver(int n, Groupe g) {
		if(!capaciteOK(n)) {
			System.out.println("depasse la capacite de salle");
			return false;
		}else if(reserverContigues(n, g)) {
			return true;
		}else {
			reserverNonContigues(n, g);
			return true;
		}
	}
	
	public synchronized boolean reserver(Groupe g) {
		return reserver(g.getNbPersonnes(), g);
	}
	
	public void annuler(int n, Groupe g) {
		Stack<Paire> places = g.getPlaces();
		if(!places.isEmpty() && places.size() >= n) {
			for(int i=0; i<n; i++) {
				Paire p = places.pop();
				placesLibres[p.getI()][p.getJ()] = true;
			}
		}
	}
	
	public void annulerTout(Groupe g) {
		annuler(g.getNbPersonnes(), g);
	}
	
	
}
