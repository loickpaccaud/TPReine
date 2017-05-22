package searchAlgorithm;

import modele.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Tabou extends ISearchAlgo {

    private Solution bestSolution;
    private int bestEvaluation;

    private int iteMax;
    private int iteCourante;

    private LinkedList<Mouvement> listTabou;

    public Tabou(Modele modele, int sizeTabou, int iteMax){
        super(modele);
        this.iteMax = iteMax;
        this.iteCourante = 0;

        this.bestSolution = this.modele.createSolution();
        this.bestEvaluation = this.bestSolution.evaluer();
        this.listTabou = new LinkedList<>() ;

        for(int i = 0; i< sizeTabou ; i++)
            listTabou.add(this.modele.createMouvement());
    }

    private void updateTabou(Mouvement mouvement){
        System.out.println("update_mouvement");
        if(this.listTabou.pollLast() != null)
            this.listTabou.addFirst(mouvement);
    }

    @Override
    public int search(){
        HashMap<Solution, Mouvement> voisinage;

        Solution solutionCourante = this.bestSolution;
        int evaluationCourante = this.bestEvaluation;

        Solution solutionSuivante;
        int evaluationSuivante;

        int evaluationTempo;
        int evaluation_optimal = modele.getBestScore();

        do
        {
            voisinage = solutionCourante.listVoisinsMouvement(this.listTabou, this.modele);

            //init solution suivante
            solutionSuivante = null;
            evaluationSuivante = Integer.MAX_VALUE;

            if(voisinage.size() > 0){
                // recherche meilleur voisin
                for(Map.Entry<Solution, Mouvement> solution : voisinage.entrySet()){
                    evaluationTempo = solution.getKey().evaluer();
                    if(evaluationTempo < evaluationSuivante){
                        solutionSuivante = solution.getKey();
                        evaluationSuivante = evaluationTempo;
                    }
                }

                // update de la listeTabou et de la meilleur solution
                if(evaluationSuivante >= evaluationCourante)
                    updateTabou(voisinage.get(solutionSuivante));
                else if(evaluationSuivante < this.bestEvaluation){
                    this.bestSolution = solutionSuivante;
                    this.bestEvaluation = evaluationSuivante;

                    if(bestEvaluation <= evaluation_optimal)
                        break;
                }

                // update de la solution focus
                solutionCourante = solutionSuivante;
                evaluationCourante = evaluationSuivante;
            }
            iteCourante++;
        }
        while ((iteCourante < iteMax) && (voisinage.size() > 0));

        return iteCourante;
    }

    @Override
    public String toString() {
        String retour = "-------------- Algorithm Tabou -------------- ";
        retour += "\nNombre d'itération : " + this.iteCourante;
        retour += "\nLa meilleure solution est : " + this.bestSolution.toString();
        retour +=  "\nL'évaluation est de : " + this.bestEvaluation;
        return retour;
    }
}
