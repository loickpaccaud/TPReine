package Model1;

import java.util.ArrayList;

/**
 * Created by MrPingouin on 20/03/2017.
 */
public class Voisin {

    public static Solution switchCol(Solution sol){
        int temp;

        int i = (int) (Math.random()*sol.getSize());
        int j = (int) (Math.random()*sol.getSize());

        temp = sol.getCaseById(i);
        sol.setCaseById(i,sol.getCaseById(j));
        sol.setCaseById(j,temp);

        return sol;
    }

    public static ArrayList<Solution> listVoisins(Solution sol){
        Solution temp;
        ArrayList<Solution> list = new ArrayList<>();

        for(int i = 0; i< sol.getDamier().size(); i++){
            for(int j = i+1; j< sol.getDamier().size(); j++){
                temp = new Solution(sol);
                int t = temp.getCaseById(j);
                temp.setCaseById(j, temp.getCaseById(i));
                temp.setCaseById(i, t);
                list.add(temp);
            }
        }
        return list;
    }
}
