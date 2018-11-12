
public class Main {

	public static void main(String[] args) {
		EnsembleDonnees data = new EnsembleDonnees();
		
		Thread p1 = new Thread(new Producteur(data));
		Thread p2 = new Thread(new Producteur(data));
		Thread l1 = new Thread(new Lecteur(data));
		Thread l2 = new Thread(new Lecteur(data));
		Thread e1 = new Thread(new Effaceur(data));
		Thread e2 = new Thread(new Effaceur(data));
		
		p1.start();
		p2.start();
		l1.start();
		l2.start();
		e1.start();
		e2.start();
	}

}
