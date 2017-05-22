package modele;


public abstract class Modele {
    protected int dimensionDamier;

    public Modele(int dimensionDamier) {
        this.dimensionDamier = dimensionDamier;
    }

    public abstract Solution createSolution();
    public abstract Mouvement createMouvement(Object[] args);
    public abstract Mouvement createMouvement();
    public abstract Solution createRandSolution();
    public abstract int getBestScore();

    public abstract Solution[] croiserSolution(Solution parent1, Solution parent2);

    public abstract int getWorstScore();

    public int getDimensionDamier() {
        return dimensionDamier;
    }
}
