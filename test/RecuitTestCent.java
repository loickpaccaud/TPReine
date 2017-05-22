import modele.Modele;
import modele.ModeleSimple;
import searchAlgorithm.ISearchAlgo;
import searchAlgorithm.Recuit;

public class RecuitTestCent {
    public static void main(String[] args){
        int TAILLE_GRILLE = 100;
        double PROBA_INITIALE = 0.1;
        double VARIATION_TEMPERATURE = 0.1;


        Modele modele = new ModeleSimple(TAILLE_GRILLE);
        ISearchAlgo recuit;

        int iteMoyenne = 0;
        double time = System.currentTimeMillis();

        for (int i =0; i<100; i++) {
            recuit = new Recuit(modele, PROBA_INITIALE, VARIATION_TEMPERATURE);
            iteMoyenne += recuit.search();
        }

        System.out.println("Iteration moyenne : " + iteMoyenne/100);
        System.out.println("Temps moyen : " + (System.currentTimeMillis()-time)/100 +"ms");
    }
}
