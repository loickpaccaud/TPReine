package modele;

import java.util.*;

import static java.lang.Math.abs;

public class SolutionSimple extends Solution{

    private ArrayList<Integer> damier;

    public SolutionSimple(int dimensionDamier) {
        super(dimensionDamier);

        this.damier = new ArrayList<>();

        for (int i=0 ; i < dimensionDamier ; i++)
            this.damier.add(i);
    }

    private SolutionSimple (SolutionSimple objet){
        super(objet.getDimensionDamier());

        this.damier = new ArrayList<>();

        for(int i = 0; i< this.dimensionDamier; i++){
            this.damier.add(objet.getCaseById(i));
        }
    }

    private int getCaseById(int n) {
        return damier.get(n);
    }

    private void setCaseById(int n, int valeur){
        this.damier.set(n,valeur);
    }

    public Solution createSolution(){
        return new SolutionSimple(this.dimensionDamier);
    }

    @Override
    public Solution copieOf(Solution objet){
        if(objet instanceof SolutionSimple )
            return new SolutionSimple((SolutionSimple) objet);
        else{
            System.exit(-4);
            return null;
        }
    }

    @Override
    public int evaluer() {
        int valeur = 0;

        for(int i = 0; i< this.dimensionDamier - 1; i++){
            for(int j = i+1; j< this.dimensionDamier; j++){
                //ligne
                if(getCaseById(i) == getCaseById(j))
                    valeur += 1;

                //digonales
                if(abs(getCaseById(i) - getCaseById(j)) == abs(i-j))
                    valeur += 1;
            }
        }
        return valeur;
    }

    @Override
    public void melanger(){
        int temp;
        int j;
        for(int i=0; i < this.dimensionDamier; i++){
            temp = getCaseById(i);
            j = (int)(Math.random() * this.dimensionDamier);
            setCaseById(i,getCaseById(j));
            setCaseById(j,temp);
        }
    }

    @Override
    public String toString(){
        return  Arrays.toString(damier.stream().mapToInt(sol -> sol).toArray());
    }

    @Override
    public ArrayList<Solution> listVoisins(){
        SolutionSimple temp;
        ArrayList<Solution> listeVoisin = new ArrayList<>();

        for(int i = 0; i< this.dimensionDamier ; i++){
            for(int j = i+1; j< this.dimensionDamier ; j++){
                temp = new SolutionSimple(this);
                int t = temp.getCaseById(j);
                temp.setCaseById(j, temp.getCaseById(i));
                temp.setCaseById(i, t);
                listeVoisin.add(temp);
            }
        }
        return listeVoisin;
    }

    public Solution getRandVoisin(){
        SolutionSimple solution = new SolutionSimple (this);
        int i = (int) (Math.random()* this.dimensionDamier);
        int j = (int) (Math.random()* this.dimensionDamier);

        int temp = solution.getCaseById(j);
        solution.setCaseById(j, solution.getCaseById(i));
        solution.setCaseById(i, temp);

        return solution;
    }

    @Override
    public HashMap<Solution, Mouvement> listVoisinsMouvement(List<Mouvement> listTabou){
        HashMap<Solution, Mouvement> listeVoisin = new HashMap<>();

        SolutionSimple solutionTempo;
        MouvementSimple mouvementTempo;
        int caseTempo;

        for(int i = 0; i< this.dimensionDamier ; i++){
            for(int j = i+1; j< this.dimensionDamier ; j++){
                solutionTempo = new SolutionSimple(this);
                mouvementTempo = new MouvementSimple(i,j);

                if(mouvementTempo.notInList(listTabou))
                {
                    //switch des lignes
                    caseTempo = solutionTempo.getCaseById(j);
                    solutionTempo.setCaseById(j, solutionTempo.getCaseById(i));
                    solutionTempo.setCaseById(i, caseTempo);

                    // ajout dans la liste
                    listeVoisin.put(solutionTempo, mouvementTempo);
                }
            }
        }
        return listeVoisin;
    }

    public Mouvement createMouvement() {
        return new MouvementSimple(-1,-1);
    }

    public int getMeilleurScore(){
        return 0;
    }
}
