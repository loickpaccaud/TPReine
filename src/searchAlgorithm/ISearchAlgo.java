package searchAlgorithm;

import modele.Modele;

public abstract class ISearchAlgo{

    protected Modele modele;

    public ISearchAlgo(Modele modele) {
        this.modele = modele;
    }

    public abstract int search();

    @Override
    public abstract String toString();
}
