import modele.Modele;
import modele.ModeleSimple;
import searchAlgorithm.ISearchAlgo;
import searchAlgorithm.Tabou;

public class AppelTabou {

    public static void main(String[] args){
        int TAILLE_GRILLE = 8;
        int TAILLE_LISTE_TABOU = 0;
        int ITERATION_MAX = 100;

        Modele modele  = new ModeleSimple(TAILLE_GRILLE);
        ISearchAlgo tabou = new Tabou(modele, TAILLE_LISTE_TABOU, ITERATION_MAX);
        System.out.println(tabou.search());
        System.out.println(tabou);
    }
}
