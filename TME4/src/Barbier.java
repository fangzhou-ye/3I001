import java.util.Random;

public class Barbier implements Runnable{
	private Sallon s;
	
	public Barbier(Sallon s) {
		this.s = s;
	}
	
	public void prendreClient() {
		Client c = s.getTabClient()[0];
		System.out.println("Je prends le client " + c.getId());
		s.decalageGauche();
		s.set(s.getNbDispo()+1);
	}
	
	public void traiterClient() {
		Random rd = new Random();
		int time = rd.nextInt(5000);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while(true) {
				if(s.estVide()) {
					// blocage du barbier
					this.wait();
				}else {
					prendreClient();
					traiterClient();
					this.notifyAll();
				}
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
