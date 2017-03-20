package Recuit;

import Model1.*;

/**
 * Created by Loick on 15/03/2017.
 */
public class Recuit {

    public Solution main(){
        Solution soli = new Solution(8);
        int fi = Evaluation.evalutaionSimple(soli);
        Solution xmin = soli;
        int fmin = fi;

        //TODO delta
        int delta = Evaluation.evalutaionSimple(soli);

        double p0 = 4/5;
        double ti = -delta/Math.log(p0);
        double µ = 0.99;

        int n1 = (int)((Math.log(-delta/(ti*Math.log(p0))))/Math.log(µ));
        int n2 = n1*n1;
            for(int k=0;k<n1;k++){
                for(int l=0;l<n2;l++){

                    Solution y = Voisin.switchCol(soli).get((int)(Math.random() * Voisin.switchCol(soli).size()));
                    delta = Evaluation.evalutaionSimple(y)-Evaluation.evalutaionSimple(soli);

                if(delta <= 0){
                    soli = y;
                    if(Evaluation.evalutaionSimple(soli)<fmin) {
                        fmin = Evaluation.evalutaionSimple(soli);
                        xmin = soli;
                    }
                }else{
                    double p = Math.random();
                    if(p <= Math.exp((-delta)/ti))
                        soli = y;
                }

                System.out.println(fmin);
                if(fmin == 0)
                    return xmin;

            }
            ti = µ*ti;
        }

        return xmin;

    }


}
