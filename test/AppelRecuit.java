import modele.Modele;
import modele.ModeleSimple;
import searchAlgorithm.ISearchAlgo;
import searchAlgorithm.Recuit;

public class AppelRecuit {

    public static void main(String[] args){
        int TAILLE_GRILLE = 50;
        double PROBA_INITIALE = 0.1;
        double VARIATION_TEMPERATURE = 0.1;

        Modele modele = new ModeleSimple(TAILLE_GRILLE);
        ISearchAlgo recuit = new Recuit(modele, PROBA_INITIALE, VARIATION_TEMPERATURE);
        recuit.search();
        System.out.println(recuit);
    }
}
