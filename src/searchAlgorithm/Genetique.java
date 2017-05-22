package searchAlgorithm;

import modele.Modele;
import modele.Solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Genetique extends ISearchAlgo{

    private Solution bestSolution;
    private int bestEvaluation;
    private int bestIteration;
    private boolean bestFinded;
    private int bestScore;

    private int generationMax;
    private int generationCourante;

    private double probaMutation;
    private double probaCroisement;

    private int tailleGeneration;
    private HashMap<Solution, Integer> solutionScore;
    private ArrayList<Solution> presentGeneration;
    private int totalScorePresent;
    private int totalScoreNext;

    private int constanteAjustement;
    private Random rand;

    public Genetique(Modele modele, int tailleGeneration, double probaMutation, double probaCroisement, int generationMax) {
        super(modele);
        this.tailleGeneration = tailleGeneration;
        this.constanteAjustement = modele.getWorstScore(); //Integer.MAX_VALUE

        this.probaMutation = probaMutation;
        this.probaCroisement = probaCroisement;
        this.generationMax = generationMax;
        this.generationCourante = 0;


        this.bestSolution =null;
        this.bestEvaluation = Integer.MAX_VALUE;

        this.bestFinded = false;
        this.bestScore = this.modele.getBestScore();
        this.totalScoreNext = 0;
        this.presentGeneration = new ArrayList<>();
        this.solutionScore= new HashMap<>();

        for(int i=0; i<this.tailleGeneration ; i++){
            bestFinded = bestFinded || testBestAndAddList(modele.createRandSolution(), presentGeneration, solutionScore );
        }

        this.totalScorePresent = this.totalScoreNext;

        this.rand = new Random();
    }

    @Override
    public int search() {
        Integer[] random = new Integer[2];
        Solution[] couple = new Solution[2];

        HashMap<Solution, Integer> nextSolutionScore;
        ArrayList<Solution> nextGeneration;


        while(!bestFinded && generationCourante < generationMax){

            nextSolutionScore = new HashMap<>();
            nextGeneration = new ArrayList<>();

            this.totalScoreNext = 0;

            // ajouter élements 2 par 2
            while(!bestFinded  && nextGeneration.size() < tailleGeneration){

                // Selectioner 2 solutions
                random[0] = rand.nextInt(this.totalScorePresent);
                random[1] = rand.nextInt(this.totalScorePresent);

                for(int i= 0 ; i < 2 ; i++){
                    for(Solution individu : presentGeneration){
                        random[i] -= solutionScore.get(individu);
                        if(random[i] <= 0){
                            couple[i] = individu;
                            break;
                        }
                    }
                }

                // Croisement des 2 solutions choisis
                if(rand.nextDouble() <= this.probaCroisement){
                    couple = modele.croiserSolution(couple[0], couple[1]);
                }

                //Mutation des 2 enfants
                if(rand.nextDouble() <= this.probaMutation){
                    couple[1] = couple[1].getRandVoisin();
                }
                if(rand.nextDouble() <= this.probaMutation){
                    couple[0] =couple[0].getRandVoisin();
                }

                // teste best + insertion
                bestFinded = testBestAndAddList(couple[1],nextGeneration, nextSolutionScore) || testBestAndAddList(couple[0],nextGeneration, nextSolutionScore);
            }

            presentGeneration = nextGeneration;
            solutionScore = nextSolutionScore;
            totalScorePresent = totalScoreNext;
            generationCourante++;

            System.out.println("Ite = " + this.generationCourante + " - score total = " + this.totalScorePresent);
        }
        return this.generationCourante;
    }

    private boolean testBestAndAddList(Solution solution, ArrayList<Solution> list, HashMap<Solution, Integer> score) {
        if(score.get(solution) == null){
            int evaluation = solution.evaluer();
            // ajouter element dans hashMap score
            score.put(solution, this.constanteAjustement - evaluation);

            // tester best
            if(evaluation < this.bestEvaluation){
                this.bestSolution = solution;
                this.bestEvaluation = evaluation;
                this.bestIteration = this.generationCourante;

                if(evaluation == bestScore){
                    return true;
                }
            }
        }

        // ajouter element dans liste
        this.totalScoreNext += score.get(solution);
        list.add(solution);
        return false;
    }

    @Override
    public String toString() {
        String retour = "-------------- Algorithm Genetique -------------- ";
        retour += "\nNombre d'itération : " + this.generationCourante;
        retour += "\nLa meilleure solution est : " + this.bestSolution.toString();
        retour += "\nNombre d'itération total : " + this.generationCourante;
        retour +=  "\nL'évaluation est de : " + this.bestIteration;
        return retour;
    }
}
