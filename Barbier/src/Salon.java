import java.util.Random;

public class Salon {
	private Random rd;
	private int nb_dispo;
	private final int capacite;
	private Client[] tabAttente;
	
	private boolean finiCoupe;
	private Client clientTraitant;
	private Object file = new Object();
	
	public Salon(int n) {
		rd = new Random();
		nb_dispo = n;
		capacite = n;
		finiCoupe = true;
		clientTraitant = null;
		tabAttente = new Client[n];
	}
	
	public boolean estVide() {
		return nb_dispo == capacite;
	}
	
	public boolean estPlein() {
		return nb_dispo == 0;
	}
	
	public void append(Client c) {
		for(int i=0; i<tabAttente.length; i++) {
			if(tabAttente[i] == null) {
				tabAttente[i] = c;
				return;
			}
		}
	}
	
	public void decalage() {
		for(int i=1; i<tabAttente.length; i++) {
			if(tabAttente[i] != null) {
				tabAttente[i-1] = tabAttente[i];
			}
			tabAttente[i] = null;
		}
	}
	
	public String toString() {
		String s = "";
		for(int i=0; i<tabAttente.length; i++) {
			if(tabAttente[i] != null) {
				s += ("c"+tabAttente[i].getId());
				s += " ";
			}else {
				s += "* ";
			}
		}
		return s;
	}
	
	public void affiche(String s) {
		System.out.println(s);
	}
	
	// blocage du barbier
	public void prendClient(Barbier b) {
		synchronized(file) {
			// par la prise en charge d'un client
			while(!finiCoupe) {
				try {
					b.trace("occupe de client " + clientTraitant.getId());
					file.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// par la salle vide
			while(estVide()) {
				try {
					b.trace("Salon vide, j'attends le client");
					file.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			b.trace("je me reveil");
			this.affiche(this.toString());
			clientTraitant = new Client(tabAttente[0].getSalon());
			clientTraitant.setId(tabAttente[0].getId());
			tabAttente[0] = null;
			this.decalage();
			this.affiche(this.toString());
			nb_dispo++;
			
			int duree = rd.nextInt(5000);
			System.out.println("Barbier traite le client " + clientTraitant.getId() + " pour " + duree/1000 + "s");
			try {
				Thread.sleep(duree);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Client " + clientTraitant.getId() +" fini");
			finiCoupe = true;
			// la fin de client traitant reveil le prochain client
			file.notifyAll();
		}
	}
	
	// blocage du client
	public void entrer(Client c) {
		synchronized(file) {
			if(!estPlein()) {
				this.append(c);
				nb_dispo--;
				c.trace("je rentre dans le salon");
				// reveil de barbier par nouveau client entrant dans la salon vide
				if(nb_dispo == capacite-1) {
					file.notifyAll();
				}
			}else {
				System.out.println("Salon deja plein");
			}
		}
	}
	
}
