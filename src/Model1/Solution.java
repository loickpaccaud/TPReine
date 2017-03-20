package Model1;

import java.util.ArrayList;

/**
 * Created by Loick on 15/03/2017.
 */
public class Plateau {

    private ArrayList<Integer> damier;
    private int dimension;

    public Plateau(int n) {
        this.dimension = n;
        this.damier = new ArrayList<>();

        for (int i=0;i<n;i++)
            this.damier.add(0);
    }

    public Plateau(Plateau p){
        this.dimension = p.getSize();
        this.damier = new ArrayList<>();

        for(int i = 0; i< this.dimension; i++){
            this.damier.add(p.getCaseById(i));
        }
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
        return this.dimension;
    }

    public void remplir(){
        for (int i=0; i<dimension; i++)
            setCaseById(i, (int) Math.random() * dimension );
    }

}
