import modele.Modele;
import modele.ModeleSimple;
import searchAlgorithm.ISearchAlgo;
import searchAlgorithm.Tabou;

public class TabouTestCent {
    public static void main(String[] args){
        int TAILLE_GRILLE = 8;
        int TAILLE_LISTE_TABOU = 0;
        int ITERATION_MAX = 100;

        int iteMoyenne = 0;
        double time = System.currentTimeMillis();

        Modele modele = new ModeleSimple(TAILLE_GRILLE);
        ISearchAlgo tabou;

        for (int i =0; i<100; i++) {
            tabou = new Tabou(modele, TAILLE_LISTE_TABOU, ITERATION_MAX);
            iteMoyenne += tabou.search();
        }

        System.out.println("Iteration moyenne : " + iteMoyenne/100);
        System.out.println("Temps moyen : " + (System.currentTimeMillis()-time)/100 + "ms");
    }
}
