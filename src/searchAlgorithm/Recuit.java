package searchAlgorithm;

import modele.*;

public class Recuit extends ISearchAlgo{

    private Solution xmin;
    private int fmin;

    private int delta;
    private double temperature;
    private double variationTemperature; //u

    public Recuit(Modele modele, double probaInitiale, double variationTemperature, int nombreSolutionDelta) {
        super(modele);

        xmin = this.modele.createSolution();
        fmin = xmin.evaluer();

        this.variationTemperature = variationTemperature;
        this.delta = genererDelta(nombreSolutionDelta);
        this.temperature = -(delta/Math.log(probaInitiale));
    }

    @Override
    public void search(){
        Solution x = xmin;
        Solution y;

        int n1 = x.getDimensionDamier()*2;
        int n2 = n1*10;

        System.out.println("-------------- Algorithm Recuit Simulé -------------- ");
        System.out.println("delta = " + delta);
        System.out.println("t0 = " + temperature);
        System.out.println("n1 = " + n1);
        System.out.println("n2 = " + n2);

        int evaluation_optimal = modele.getBestScore();

        for(int k=0 ; k < n1 ; k++){
            for(int l=0 ; l < n2 ; l++){

                y = x.getRandVoisin();
                delta = y.evaluer() - x.evaluer();

                if(delta <= 0){
                    x = y;
                    if(x.evaluer() <fmin) {
                        fmin = x.evaluer();
                        xmin = x;
                    }
                }else if(Math.random() <= Math.exp((-delta)/temperature)){
                    x = y;
                }

                if(fmin == evaluation_optimal) {
                    System.out.println("\nNb d'itération n1 : "+Integer.toString(k+1));
                    System.out.println("Nb d'itération n1 * n2 : "+Integer.toString(k*n2 + (l+1)));
                    System.out.println("\nLa meilleure solution est : " + this.xmin.toString());
                    System.out.println("L'évaluation est de : " + this.fmin);
                    return ;
                }

            }
            this.temperature *= this.variationTemperature;
        }

        System.out.println("\ntfinale = " + temperature);
        System.out.println("Nb d'itération (n1 * n2) : " + Integer.toString(n1*n2));
        System.out.println("\nLa meilleure solution est : " + this.xmin.toString());
        System.out.println("L'évaluation est de : " + this.fmin);
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
}
