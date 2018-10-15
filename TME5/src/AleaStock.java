
public class AleaStock {
	private int taille;
	private int nbObj;
	private AleaObjet[] tabObjets;
	
	public AleaStock(int taille) {
		this.taille = taille;
		nbObj = 0;
		tabObjets = new AleaObjet[taille];
	}
	
	public void append(AleaObjet obj) {
		if(nbObj < taille) {
			for(int i=0; i<tabObjets.length; i++) {
				if(tabObjets[i] == null) {
					tabObjets[i] = obj;
					nbObj++;
					break;
				}
			}
		}
	}
	
	public AleaObjet extrait() {
		if(this.estVide()) {
			return null;
		}else {
			int i;
			for(i=0; i<tabObjets.length; i++) {
				if(tabObjets[i] == null) {
					break;
				}
			}
			AleaObjet tmp = tabObjets[i-1];
			tabObjets[i-1] = null;
			nbObj--;
			return tmp;
		}
	}
	
	public boolean estVide() {
		for(int i=0; i<tabObjets.length; i++) {
			if(tabObjets[i] != null)
				return false;
		}
		return true;
	}
	
	
}
