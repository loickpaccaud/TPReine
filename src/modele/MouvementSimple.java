package modele;

import java.util.List;

public class MouvementSimple extends Mouvement{

    private int colonne1;
    private int colonne2;

    public MouvementSimple(int colonne1, int colonne2){
        this.colonne1 = colonne1;
        this.colonne2 = colonne2;
    }

    public int getColonne1() {
        return colonne1;
    }

    public int getColonne2() {
        return colonne2;
    }

    public void setColonne1(int colonne1) {
        this.colonne1 = colonne1;
    }

    public void setColonne2(int colonne2) {
        this.colonne2 = colonne2;
    }

    @Override
    public boolean notInList (List<Mouvement> listeMouvement) {

        for(Mouvement mouvement : listeMouvement){
            if(!(mouvement instanceof MouvementSimple))
                System.exit(-5);

            if((this.colonne1 == ((MouvementSimple) mouvement).getColonne1()) && (this.colonne2 == ((MouvementSimple) mouvement).getColonne2()))
                return false;
        }
        return true;
    }

}
