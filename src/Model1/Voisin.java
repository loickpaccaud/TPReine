package Model1;

import java.util.ArrayList;

/**
 * Created by MrPingouin on 20/03/2017.
 */
public class Voisin {

    public static Solution switchCol(Solution sol){
        Solution solution = new Solution(sol);
        int i = (int) (Math.random()*sol.getSize());
        int j = (int) (Math.random()*sol.getSize());

        int temp = solution.getCaseById(j);

        solution.setCaseById(j, solution.getCaseById(i));
        solution.setCaseById(i, temp);
        return solution;
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
