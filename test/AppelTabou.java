import modele.Solution;
import modele.SolutionSimple;
import tabou.Tabou;

public class AppelTabou {

    public static void main(String[] args){
        int TAILLE_GRILLE = 100;
        int TAILLE_LISTE_TABOU = 2;
        int ITERATION_MAX = 75;

        Solution solutionInitiale = new SolutionSimple(TAILLE_GRILLE);
        Tabou tabou = new Tabou(solutionInitiale, TAILLE_LISTE_TABOU, ITERATION_MAX);
        tabou.search();
    }
}
