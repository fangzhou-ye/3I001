import java.io.File;
import java.io.IOException;

public class TestProduitParallele {

	public static void main(String[] args) throws TaillesNonConcordantesException, InterruptedException {
		String filePath = new File("").getAbsolutePath() + "/src/matrix.txt";
		File fichier = new File(filePath);
		try {
			MatriceEntiere m1 = new MatriceEntiere(fichier);
			m1.affiche();
			MatriceEntiere m2 = m1.transpose();
			m2.affiche();
			
			MatriceEntiere res = new MatriceEntiere();
			res.initZero(m1.getNbLignes(), m2.getNbColonnes());
			int cpt = 0;
			System.out.println("je suis le thread " + cpt);
			cpt++;
			for(int i=0;i<res.getNbLignes(); i++) {
				for(int j=0; j<res.getNbColonnes(); j++) {
					Thread t = new Thread(new CalculElem(m1, i, m2, j, res));
					t.start();
					System.out.print("je suis le thread " + cpt);
					cpt++;
					System.out.print(" je cree le thread " + cpt);
					t.join();
					System.out.println(" je termine");
				}
			}
			res.affiche();
			
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
