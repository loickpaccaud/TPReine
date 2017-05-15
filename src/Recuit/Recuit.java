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
        Solution xi = xmin;
        Solution y;

        int delta = xi.getSize()/2;
        //int delta = Evaluation.genererDelta(xi.getSize(),50);

        double p = 0.9;
        //double t = -(delta/Math.log(p));
        double t = xi.getSize()*10;
        double u = 0.9;

        //int n1 = (int)((Math.log(-delta/(t*Math.log(0.01))))/Math.log(u));
        //System.out.println(n1);
        int n1 = xi.getSize()*2;
        int n2 = n1*10;

        for(int k=0;k<n1;k++){
            for(int l=0;l<n2;l++){

                y = Voisin.switchCol(xi);
                //y = Voisin.listVoisins(xi).get((int)(Math.random() * Voisin.listVoisins(xi).size()));
                delta = Evaluation.evalutaionSimple(y)-Evaluation.evalutaionSimple(xi);

                if(delta <= 0){
                    xi = y;
                    if(Evaluation.evalutaionSimple(xi)<fmin) {
                        fmin = Evaluation.evalutaionSimple(xi);
                        xmin = xi;
                    }
                }else{
                    p = Math.random();
                    if(p <= Math.exp((-delta)/t))
                        xi = y;
                }

                if(fmin == 0) {
                    System.out.println("Nb d'itération n1 : "+Integer.toString(k+1));
                    System.out.println("Nb d'itération n1 * n2 : "+Integer.toString((k+1)*(l+1)));
                    return xmin;
                }
            }
            t *= u;
        }
        System.out.println("Nb d'itération n1 * n2 : "+Integer.toString(n1*n2));
        return xmin;
    }


}
