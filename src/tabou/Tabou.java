package tabou;

import modele.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Tabou {

    private int iteMax;
    private int iteCourante;

    private Solution bestSolution;
    private int bestEvaluation;
    private LinkedList<Mouvement> listTabou;

    public Tabou(Solution solution, int sizeTabou, int iteMax){
        this.iteMax = iteMax;
        this.iteCourante = 0;

        this.bestSolution = solution;
        this.bestEvaluation = this.bestSolution.evaluer();
        this.listTabou = new LinkedList<>() ;

        for(int i = 0; i< sizeTabou ; i++)
            listTabou.add(this.bestSolution.createMouvement());
    }

    private void updateTabou(Mouvement mouvement){
        this.listTabou.pollLast();
        this.listTabou.addFirst(mouvement);
    }

    public void search(){
        HashMap<Solution, Mouvement> voisinage;

        Solution solutionCourante = this.bestSolution;
        int evaluationCourante = this.bestEvaluation;

        Solution solutionSuivante;
        int evaluationSuivante;

        int evaluationTempo;
        int evaluation_optimal = solutionCourante.getMeilleurScore();

        do
        {
            voisinage = solutionCourante.listVoisinsMouvement(this.listTabou);

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

        System.out.println("-------------- Algorithm Tabou -------------- ");
        System.out.println("Nombre d'itération : " + this.iteCourante);
        System.out.println("La meilleure solution est : " + this.bestSolution.toString());
        System.out.println("L'évaluation est de : " + this.bestEvaluation);
    }
}
