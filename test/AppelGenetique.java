import modele.Modele;
import modele.ModeleSimple;
import searchAlgorithm.Genetique;
import searchAlgorithm.ISearchAlgo;


public class AppelGenetique{

    public static void main(String[] args){
        int TAILLE_GRILLE = 10;
        int TAILLE_GENERATION = 1000;
        int GENERATION_MAX = 1000;

        double PROBA_MUTATION = 0.1;
        double PROBA_CROISSEMENT = 0.9;

        Modele modele = new ModeleSimple(TAILLE_GRILLE);
        ISearchAlgo genetique = new Genetique(modele, TAILLE_GENERATION, PROBA_MUTATION, PROBA_CROISSEMENT, GENERATION_MAX);
        genetique.search();
        System.out.println(genetique);
    }

}
