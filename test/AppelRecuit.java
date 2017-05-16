import modele.Solution;
import modele.SolutionSimple;
import recuit.Recuit;

public class AppelRecuit {

    public static void main(String[] args){
        int TAILLE_GRILLE = 50;
        double PROBA_INITIALE = 0.8; //p0
        double VARIATION_TEMPERATURE = 0.9; //u
        int NOMBRE_SOLUTION_DELTA = 20;

        Solution solutionInitiale = new SolutionSimple(TAILLE_GRILLE);
        Recuit recuit = new Recuit(solutionInitiale, PROBA_INITIALE, VARIATION_TEMPERATURE, NOMBRE_SOLUTION_DELTA);
        recuit.search();
    }
}
