package Model1;

/**
 * Created by Loick on 15/03/2017.
 */
public class Evaluation {

    private Plateau plateau;

    public Evaluation(Plateau plateau) {
        this.plateau = plateau;
    }

    public int realiser (Plateau plateau){
        int valeur = 0;

        //colonne
        valeur +=0;

        for(int i=0;i<plateau.getSize()-1;i++){
            for(int j=i+1;j<plateau.getSize();j++){
                //ligne
                if(plateau.getCaseById(i) == plateau.getCaseById(j))
                    valeur += 1;

                //digonales
                if(plateau.getCaseById(i) - plateau.getCaseById(j) == i-j)
                    valeur += 1;

            }
        }

        return valeur;
    }
}
