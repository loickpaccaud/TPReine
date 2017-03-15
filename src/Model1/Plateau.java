package Model1;

import java.util.ArrayList;

/**
 * Created by Loick on 15/03/2017.
 */
public class Plateau {

    private ArrayList<Integer> damier;

    public Plateau(int n) {
        for (int i=0;i<n;i++)
            this.damier.add(0);
    }

    public ArrayList<Integer> getDamier() {
        return damier;
    }

    public int getCaseById(int n) {
        return damier.get(n);
    }

    public void setDamier( ArrayList<Integer> damier) {
        this.damier = damier;
    }

    public void setCaseById(int n, int valeur){
        this.damier.set(n,valeur);
    }

    public int getSize(){
        return this.damier.size();
    }
}
