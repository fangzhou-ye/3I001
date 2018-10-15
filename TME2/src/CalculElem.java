
public class CalculElem implements Runnable {
	private MatriceEntiere m1;
	private MatriceEntiere m2;
	private MatriceEntiere res;
	private int i;
	private int j;
	
	public CalculElem(MatriceEntiere m1, int i,MatriceEntiere m2, int j, MatriceEntiere res) {
		this.m1 = m1;
		this.i = i;
		this.m2 = m2;
		this.j = j;
		this.res = res;
	}
	
	@Override
	public void run() {
		try {
			res.setElem(i, j, MatriceEntiere.produitLigneColonne(m1, i, m2, j));
		} catch (TaillesNonConcordantesException e) {
			System.out.println(e.getMessage());
		}
	}

}
