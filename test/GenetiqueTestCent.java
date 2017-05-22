import modele.Modele;
import modele.ModeleSimple;
import searchAlgorithm.Genetique;
import searchAlgorithm.ISearchAlgo;

public class GenetiqueTestCent {
    public static void main(String[] args){
        int TAILLE_GRILLE = 10;
        int TAILLE_GENERATION = 1000;
        int GENERATION_MAX = 1000;

        double PROBA_MUTATION = 0.01;
        double PROBA_CROISSEMENT = 0.7;

        Modele modele = new ModeleSimple(TAILLE_GRILLE);
        ISearchAlgo genetique;

        int iteMoyenne = 0;
        double time = System.currentTimeMillis();

        for (int i =0; i<100; i++) {
            genetique = new Genetique(modele, TAILLE_GENERATION, PROBA_MUTATION, PROBA_CROISSEMENT, GENERATION_MAX);
            iteMoyenne += genetique.search();
        }

        System.out.println("Iteration moyenne : " + iteMoyenne/100);
        System.out.println("Temps moyen : " + (System.currentTimeMillis() - time)/100 +"ms");
    }

}
