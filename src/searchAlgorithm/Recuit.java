package searchAlgorithm;

import modele.*;

public class Recuit extends ISearchAlgo{

    private Solution xmin;

    private int n1;
    private int n2;

    private int delta;
    private double temperature;
    private double variationTemperature; //u

    public Recuit(Modele modele, double probaInitiale, double variationTemperature) {
        super(modele);

        xmin = this.modele.createRandSolution();
        bestEvaluation = xmin.evaluer();

        this.variationTemperature = variationTemperature;
        this.delta = 2*modele.getDimensionDamier()/3;
        this.temperature = -(delta/Math.log(probaInitiale));


        this.n1 = 2*modele.getDimensionDamier();
        this.n2 = n1*4;
    }

    @Override
    public int search(){
        Solution x = xmin;
        Solution y;

        int evaluationX = bestEvaluation;
        int evaluationY;

        int evaluation_optimal = modele.getBestScore();

        for(int k=0 ; k < n1 ; k++){
            for(int l=0 ; l < n2 ; l++){

                y = x.getRandVoisin();
                evaluationY = y.evaluer();
                delta = evaluationY - evaluationX;

                if(delta <= 0){
                    x = y;
                    evaluationX=evaluationY;
                    if(evaluationX < bestEvaluation) {
                        bestEvaluation = evaluationX;
                        xmin = x;
                    }
                }else if(Math.random() <= Math.exp((-delta)/temperature)){
                    x = y;
                    evaluationX=evaluationY;
                }

                if(bestEvaluation == evaluation_optimal) {
                    return k+1;
                }

            }
            this.temperature *= this.variationTemperature;
        }
        return n1;
    }

    private int genererDelta(int nombreSolution){
        int moyenne =0;

        Solution solution;

        for(int k = 0; k < nombreSolution; k++){
            solution = this.modele.createRandSolution();
            moyenne += solution.evaluer();
        }
        moyenne /= nombreSolution;
        return moyenne;
    }

    @Override
    public String toString() {
        String retour = "-------------- Algorithm Recuit -------------- ";
        retour += "\nNb d'itération n1 : "+Integer.toString(n1);
        retour += "\nNb d'itération n1 * n2 : "+Integer.toString(n1 * n2);
        retour += "\nLa meilleure solution est : " + this.xmin.toString();
        retour = "\nL'évaluation est de : " + this.bestEvaluation;
        return retour;
    }
}
