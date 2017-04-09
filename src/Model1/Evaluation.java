package Model1;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by Loick on 15/03/2017.
 */
public class Evaluation {

    public static int evalutaionSimple (Solution solution){
        int valeur = 0;

        for(int i = 0; i< solution.getSize()-1; i++){
            for(int j = i+1; j< solution.getSize(); j++){
                //ligne
                if(solution.getCaseById(i) == solution.getCaseById(j))
                    valeur += 1;

                //digonales
                if(abs(solution.getCaseById(i) - solution.getCaseById(j)) == abs(i-j))
                    valeur += 1;

            }
        }

        return valeur;
    }

    public static Solution meilleureSolution(ArrayList<Solution> voisins) {
        Solution bestSolution = voisins.get(0);
        int bestEval = Evaluation.evalutaionSimple(bestSolution);
        int eval;
        for(Solution voisin : voisins){
            eval = Evaluation.evalutaionSimple(voisin);
            if(eval < bestEval){
                bestEval = eval;
                bestSolution = voisin;
            }
        }
        return bestSolution;
    }

    public static int genererDelta(int taille,int iteration){
        int moyenne =0;
        Solution solution = new Solution(taille);
        for(int k=0; k<iteration; k++){
            solution.melanger();
            moyenne += Evaluation.evalutaionSimple(solution);
        }
        moyenne /= iteration;
        return moyenne;
    }
}
