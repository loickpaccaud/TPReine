import modele.Modele;
import modele.ModeleSimple;
import searchAlgorithm.Genetique;
import searchAlgorithm.ISearchAlgo;


public class AppelGenetique{

    public static void main(String[] args){
        int TAILLE_GRILLE = 10;
        int TAILLE_GENERATION = 100;
        int GENERATION_MAX = 10000;

        double PROBA_MUTATION = 0;
        double PROBA_CROISSEMENT = 0;

        Modele modele = new ModeleSimple(TAILLE_GRILLE);
        ISearchAlgo genetique = new Genetique(modele, TAILLE_GENERATION, PROBA_MUTATION, PROBA_CROISSEMENT, GENERATION_MAX);
        genetique.search();
    }

}
