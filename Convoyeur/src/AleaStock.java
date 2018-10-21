import java.util.ArrayList;

public class AleaStock {
	private final int taille;
	private ArrayList<AleaObjet> listObjet;
	
	public AleaStock(int taille) {
		this.taille = taille;
		listObjet = new ArrayList<AleaObjet>(taille);
	}
	
	public ArrayList<AleaObjet> getListObjet(){
		return listObjet;
	}
	
	public boolean estPlein() {
		return listObjet.size() == taille;
	}
	
	public boolean estVide() {
		return listObjet.size() == 0;
	}
	
	public int getPoidsMin() {
		if(listObjet.size() != 0) {
			int min = listObjet.get(0).getPoids();
			for(AleaObjet obj : listObjet) {
				if(obj.getPoids() < min) {
					min = obj.getPoids();
				}
			}
			return min;
		}else {
			return -1;
		}
	}
	
	public void append(AleaObjet obj) {
		if(!estPlein()) {
			listObjet.add(obj);
		}else {
			System.out.println("AleaStock est deja plein");
		}
	}
	
	public void affiche() {
		System.out.println("Stock ----------");
		for(AleaObjet obj : listObjet) {
			System.out.print("[id:" + obj.getId() + " poids:" + obj.getPoids() + "]");
		}
		System.out.println();
		System.out.println();
	}
	
}
