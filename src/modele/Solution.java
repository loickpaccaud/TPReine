package modele;

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

    // fonction static de création aléatoire

    public abstract int evaluer();
    public abstract Solution createCopy();

    public abstract Solution getRandVoisin();
    public abstract HashMap<Solution, Mouvement> listVoisinsMouvement(List<Mouvement> listTabou, Modele modele);

    @Override
    public abstract String toString();

    public abstract Solution[] croiser(Solution solution);
}
