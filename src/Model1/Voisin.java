package Model1;

import java.util.ArrayList;

/**
 * Created by MrPingouin on 20/03/2017.
 */
public class Voisin {

    public static  ArrayList<Solution> switchCol(Solution sol){
        Solution temp;
        ArrayList<Solution> list = new ArrayList<>();

        for(int i = 0; i< sol.getDamier().size(); i++){
            for(int j = i+1; j< sol.getDamier().size(); j++){
                temp = new Solution(sol);
                int t = temp.getCaseById(j);
                temp.setCaseById(j, temp.getCaseById(i));
                temp.setCaseById(i, t);
            }
        }

        return list;
    }
}
