package Model1;

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
                if(solution.getCaseById(i) - solution.getCaseById(j) == i-j)
                    valeur += 1;

            }
        }

        return valeur;
    }
}
