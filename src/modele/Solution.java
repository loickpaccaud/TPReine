package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Solution{

    protected int dimensionDamier;

    public Solution(int dimensionDamier) {
        this.dimensionDamier = dimensionDamier;
    }

    public int getDimensionDamier() {

        return dimensionDamier;
    }

    public abstract int evaluer();
    public abstract Solution copieOf(Solution objet);
    public abstract void melanger();

    public abstract ArrayList<Solution> listVoisins();
    public abstract Solution getRandVoisin();
    public abstract HashMap<Solution, Mouvement> listVoisinsMouvement(List<Mouvement> listTabou);

    @Override
    public abstract String toString();

    public abstract Solution createSolution();
    public abstract Mouvement createMouvement();

    public abstract int getMeilleurScore();
}
