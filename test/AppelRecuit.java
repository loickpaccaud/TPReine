import modele.Modele;
import modele.ModeleSimple;
import searchAlgorithm.ISearchAlgo;
import searchAlgorithm.Recuit;

public class AppelRecuit {

    public static void main(String[] args){
        int TAILLE_GRILLE = 50;
        double PROBA_INITIALE = 0.8; //p0
        double VARIATION_TEMPERATURE = 0.9; //u
        int NOMBRE_SOLUTION_DELTA = 20;

        Modele modele = new ModeleSimple(TAILLE_GRILLE);
        ISearchAlgo recuit = new Recuit(modele, PROBA_INITIALE, VARIATION_TEMPERATURE, NOMBRE_SOLUTION_DELTA);
        recuit.search();
    }
}
