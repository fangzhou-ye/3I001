import java.util.Arrays;

public class Sallon {
	private final int nbTotal;
	private int nbDispo;
	private Client[] tab;
	
	public Sallon(int n) {
		nbTotal = n;
		nbDispo = nbTotal;
		tab = new Client[n];
	}
	
	public void affiche() {
		System.out.println(Arrays.toString(tab));
	}
	
	public void appendClient(Client c) {
		for(int i=0; i<tab.length; i++) {
			if(tab[i] == null) {
				tab[i] = c;
			}
		}
	}
	
	public boolean estPlein() {
		return nbDispo == 0;
	}
	
	public boolean estVide() {
		return nbDispo == nbTotal;
	}
	
	public void decalageGauche() {
		for(int i=1; i<tab.length; i++) {
			tab[i-1] = tab[i];
		}
	}
	
	public Client[] getTabClient() {
		return tab;
	}
	
	public int getNbDispo() {
		return nbDispo;
	}
	
	public void set(int val) {
		nbDispo = val;
	}
	
}
