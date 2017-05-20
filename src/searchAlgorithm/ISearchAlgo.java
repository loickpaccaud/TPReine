package searchAlgorithm;

import modele.Modele;

/**
 * Created by MrPingouin on 18/05/2017.
 */
public abstract class ISearchAlgo{

    protected Modele modele;

    public ISearchAlgo(Modele modele) {
        this.modele = modele;
    }

    public abstract void search();

}
