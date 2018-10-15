import java.io.File;
import java.io.IOException;

public class MainMartrice {
	public static void main(String[] args) throws TaillesNonConcordantesException {
		String filePath = new File("").getAbsolutePath() + "/src/matrix.txt";
		File fichier = new File(filePath);
		try {
			MatriceEntiere mc = new MatriceEntiere(fichier);
			mc.affiche();
			MatriceEntiere trans = mc.transpose();
			trans.affiche();
			MatriceEntiere.produit(mc, mc.transpose()).affiche();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(filePath);
	}
}
