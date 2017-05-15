package Tabou;

import Model1.*;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Loick on 15/03/2017.
 */
public class Tabou {

    private int sizeDamier;
    private LinkedList<Mouvement> tabou;
    private Solution solMin;
    private int evalMin;

    public Tabou(int sizeTabou, int sizeDamier){

        this.sizeDamier = sizeDamier;

        this.solMin = new Solution(sizeDamier);
        this.evalMin = Evaluation.evalutaionSimple(solMin);

        this.tabou = new LinkedList<>() ;

        for(int i = 0; i< sizeTabou ; i++)
            tabou.add(new Mouvement());
    }

    public int search(int iteMax){
        int ite = 0;
        Solution solY;
        Solution solX = this.solMin;
        ArrayList<Solution> voisins = trouverVoisin(solX);

        while(ite++ < iteMax || voisins.size() > 0){
             solY = Evaluation.meilleureSolution(voisins);

             //Solution moins bonne
             if(Evaluation.evalutaionSimple(solY) - Evaluation.evalutaionSimple(solX) >= 0)
                ajouterTabou(new Mouvement(solY, solX));

            // Meilleure solution
             if(Evaluation.evalutaionSimple(solY) < evalMin){
                 evalMin = Evaluation.evalutaionSimple(solY);
                 solMin = solY;
             }

            solX = solY;
            voisins = trouverVoisin(solX);
        }

        return ite;
    }

    public void ajouterTabou(Mouvement mouv){
        tabou.removeLast();
        tabou.addFirst(mouv);
    }

    public ArrayList<Solution> trouverVoisin(Solution sol){
        ArrayList<Solution> voisin =  Voisin.listVoisins(sol);

        return voisin;
    }

}
