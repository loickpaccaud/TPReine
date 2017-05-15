package Model1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Loick on 15/03/2017.
 */
public class Solution {

    private ArrayList<Integer> damier;
    private int dimension;

    public Solution(int n) {
        this.dimension = n;
        this.damier = new ArrayList<>();

        for (int i=0;i<n;i++)
            this.damier.add(i);
    }

    public Solution(Solution p){
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

    public void melanger(){
        int temp;
        int j;
        for(int i=0; i<dimension; i++){
            temp = getCaseById(i);
            j = (int)(Math.random() * dimension);
            setCaseById(i,getCaseById(j));
            setCaseById(j,temp);
        }
    }

    public void afficher(){
        System.out.println("Solution : "+Arrays.toString(damier.stream().mapToInt(sol -> sol).toArray()));
        int conflit = Evaluation.evalutaionSimple(this);
        if(conflit!=0)
            System.out.println("Nombre de conflits : "+conflit);
    }

}
