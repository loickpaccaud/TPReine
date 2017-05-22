package searchAlgorithm;

import modele.Modele;

public abstract class ISearchAlgo{
    protected int bestEvaluation;
    protected Modele modele;

    public ISearchAlgo(Modele modele) {
        this.modele = modele;
    }

    public int getBestEvaluation() {
        return bestEvaluation;
    }

    public abstract int search();

    @Override
    public abstract String toString();
}
