import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MatriceEntiere {
	private int nbLignes;
	private int nbColonnes;
	private int[][]matrix;
	
	public MatriceEntiere(int lignes, int colonnes) {
		nbLignes = lignes;
		nbColonnes = colonnes;
		matrix = new int[lignes][colonnes];
	}
	
	public MatriceEntiere(File fichier) throws NumberFormatException, IOException{
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fichier));
			nbLignes = Integer.parseInt(in.readLine());
			nbColonnes = Integer.parseInt(in.readLine());
			matrix = new int[nbLignes][nbColonnes];
			String line;
			int cpt = 0;
			while((line = in.readLine()) != null) {
				String[] strArr = line.split(" ");
				for(int i=0; i<strArr.length; i++) {
					matrix[cpt][i] = Integer.parseInt(strArr[i]);
				}
				cpt++;
			}
		} catch(FileNotFoundException e) {
			System.out.println("Fichier non trouve dans le chemin indique !!!");
		} catch(NumberFormatException e){
			System.out.println(e.getMessage() + " le stirng lu doit etre decimal");
		} finally {
			in.close();
		}
	}
	
	public int getElem(int i, int j) {
		return matrix[i][j];
	}
	
	public void setElem(int i, int j, int val) {
		matrix[i][j] = val;
	}
	
	public int getLigne() {
		return nbLignes;
	}
	
	public int getColonne() {
		return nbColonnes;
	}

	public void affiche() {
		String s = "";
		for(int i=0; i<nbLignes; i++) {
			for(int j=0; j<nbColonnes; j++) {
				s += matrix[i][j] + " ";
			}
			s += "\n";
		}
		System.out.println(s);
	}
	
	public void initZero() {
		for(int i=0; i<nbLignes; i++) {
			for(int j=0; j<nbColonnes; j++) {
				matrix[i][j] = 0;
			}
		}
	}
	
	public MatriceEntiere transpose(){
		MatriceEntiere trans = new MatriceEntiere(this.nbColonnes, this.nbLignes);
		for(int i=0; i<this.nbLignes; i++) {
			for(int j=0; j<this.nbColonnes; j++) {
				trans.setElem(j, i, this.matrix[i][j]);
			}
		}
		return trans;
	}
	
	public static MatriceEntiere somme(MatriceEntiere mat1, MatriceEntiere mat2) throws TaillesNonConcordantesException{
		if(mat1.getLigne() != mat2.getLigne() || mat1.getColonne() != mat2.getColonne()) {
			throw new TaillesNonConcordantesException("La taille des deux matrices doit etre egale", "Taille non valide");
		}
		MatriceEntiere sum = new MatriceEntiere(mat1.getLigne(), mat1.getColonne());
		for(int i=0; i<mat1.getLigne(); i++) {
			for(int j=0; j<mat1.getColonne(); j++) {
				sum.setElem(i, j, mat1.getElem(i, j) + mat2.getElem(i, j));
			}
		}
		return sum;
	}
	
	public static MatriceEntiere produit(MatriceEntiere mat1, MatriceEntiere mat2) throws TaillesNonConcordantesException{
		if(mat1.getColonne() != mat2.getLigne()) {
			throw new TaillesNonConcordantesException("nbCol de mat1 doit etre egale a nbLig de mat2", "Taille non valide");
		}
		MatriceEntiere prod = new MatriceEntiere(mat1.getLigne(), mat2.getColonne());
		for(int i=0; i<prod.getLigne(); i++) {
			for(int j=0; j<prod.getColonne(); j++) {
				int res = 0;
				for(int k=0; k<mat1.nbColonnes; k++) {
					res += mat1.getElem(i, k)*mat2.getElem(k, j);
				}
				prod.setElem(i, j, res);
			}
		}
		return prod;
	}
	
}
