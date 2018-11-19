
public class Requete {
	private static int cpt = 1;
	private final int num;
	private Client c;
	private final int type;
	private ReponseRequete rep;
	
	public Requete(Client c) {
		num = cpt++;
		if(this.c.getMonID()%3 != 0) {
			type = 1;
		}else {
			type = 2;
		}
		rep = null;
	}
	
	public ReponseRequete getReponseRequete() {
		return rep;
	}
	
	public void setReponseRequete(ReponseRequete rep) {
		this.rep = rep;
	}
	
}
