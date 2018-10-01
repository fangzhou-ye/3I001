
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
					s += "True ";
				}else {
					s += "False ";
				}
			}
			s += "\n";
		}
		return s;
	}
	
	public void affiche() {
		System.out.println(this.toString());
	}
	
	private boolean capaciteOK(int n) {
		int cpt = 0;
		for(int i=0; i<nbRang; i++) {
			for(int j=0; j<nbPlacesParRang; j++) {
				if(placesLibres[i][j] == true) {
					cpt++;
				}
				if(cpt == n) {
					return true;
				}
			}
		}
		return false;
	}
	
	private int nContiguesAuRangI(int n, int i) {
		int cpt = 0;
		for(int j=0; j<placesLibres[i].length; j++) {
			if(placesLibres[i][j] == false) {
				cpt = 0;
			}else {
				cpt++;
				if(cpt>=n) {
					return j-n;
				}
			}
		}
		return -1;
	}
	
	private boolean reserverContigues(int n) {
		for(int i=0; i<nbRang; i++) {
			int res = nContiguesAuRangI(n, i);
			if(res != -1) {
				int cpt = 0;
				while(cpt != n) {
					placesLibres[i][res+cpt] = false;
					cpt++;
				}
				return true;
			}
		}
		return false;
	}
	
	private boolean reserver(int n) {
		int cpt = 0;
		if(capaciteOK(n)) {
			for(int i=0; i<nbRang; i++) {
				for(int j=0; j<nbPlacesParRang; j++) {
					if(placesLibres[i][j] == true) {
						cpt++;
						placesLibres[i][j] = false;
						if(cpt == n) {
							return true;
						}else {
							continue;
						}
					}else {
						continue;
					}
				}
			}
		}
		
		return false;
	}
	
	public synchronized boolean reserver(Groupe g) {
		if(reserverContigues(g.getNbPersonnes()) == false)
			return reserver(g.getNbPersonnes());
		return reserverContigues(g.getNbPersonnes());
	}
}
