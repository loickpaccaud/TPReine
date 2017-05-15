package Recuit;

import Model1.*;

/**
 * Created by Loick on 15/03/2017.
 */
public class Recuit {
    private Solution xmin;
    private int fmin;

    public Recuit(int taille) {
        xmin = new Solution(taille);
        fmin = Evaluation.evalutaionSimple(this.xmin);
    }

    public Solution search(){
        Solution x = xmin;
        Solution y;

        int delta = Evaluation.genererDelta(x.getSize(),20);

        double p0 = 0.8;
        double temperature = -(delta/Math.log(p0));
        double u = 0.9;

        int n1 = x.getSize()*2;
        int n2 = n1*10;

        System.out.println("delta = "+delta);
        System.out.println("t0 = "+temperature);
        System.out.println("n1 = "+n1);
        System.out.println("n2 = "+n2);

        for(int k=0;k<n1;k++){
            for(int l=0;l<n2;l++){

                y = Voisin.switchCol(x);
                delta = Evaluation.evalutaionSimple(y)-Evaluation.evalutaionSimple(x);

                if(delta <= 0){
                    x = y;
                    if(Evaluation.evalutaionSimple(x)<fmin) {
                        fmin = Evaluation.evalutaionSimple(x);
                        xmin = x;
                    }
                }else if(Math.random() <= Math.exp((-delta)/temperature)){
                    x = y;
                }

                if(fmin == 0) {
                    System.out.println("Nb d'itération n1 : "+Integer.toString(k+1));
                    System.out.println("Nb d'itération n1 * n2 : "+Integer.toString(k*n2 + (l+1)));
                    return xmin;
                }

            }
            temperature *= u;
        }

        System.out.println("tfinale = "+temperature);
        System.out.println("Nb d'itération (n1 * n2) : "+Integer.toString(n1*n2));
        return xmin;
    }


}
