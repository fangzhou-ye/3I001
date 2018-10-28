
public class PoolHangars {
	private Hangar[] tab;
	
	public PoolHangars(int taille) {
		tab = new Hangar[taille];
		for(int i=0; i<tab.length; i++) {
			tab[i] = new Hangar();
		}
	}
	
	public int chercheLibre() {
		for(int i=0; i<tab.length; i++) {
			if(tab[i].getLoco() != -1) {
				return i;
			}
		}
		return -1;
	}
	
	public Hangar getHangar(int i) {
		return tab[i];
	}
}
