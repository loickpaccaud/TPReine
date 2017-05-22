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

    public static Solution createRandSolution(int dimensionDamier){
        SolutionSimple solution = new SolutionSimple(dimensionDamier);

        int temp;
        int j;

        for(int i=0; i < dimensionDamier; i++){
            temp = solution.getCaseById(i);
            j = (int)(Math.random() * dimensionDamier);
            solution.setCaseById(i, solution.getCaseById(j));
            solution.setCaseById(j,temp);
        }
        return solution;
    }

    private int getCaseById(int n) {
        return damier.get(n);
    }

    private void setCaseById(int n, int valeur){
        this.damier.set(n,valeur);
    }

    @Override
    public Solution createCopy(){
        return new SolutionSimple(this);
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
    public String toString(){
        return  Arrays.toString(damier.stream().mapToInt(sol -> sol).toArray());
    }

    @Override
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
    public HashMap<Solution, Mouvement> listVoisinsMouvement(List<Mouvement> listTabou, Modele modele){
        HashMap<Solution, Mouvement> listeVoisin = new HashMap<>();

        SolutionSimple solutionTempo;
        Mouvement mouvement;
        Object[] args;

        int caseTempo;

        for(int i = 0; i< this.dimensionDamier ; i++){
            for(int j = i+1; j< this.dimensionDamier ; j++){
                solutionTempo = new SolutionSimple(this);
                args = new Object[]{i,j};
                mouvement = modele.createMouvement(args);

                if(mouvement.notInList(listTabou))
                {
                    //switch des lignes
                    caseTempo = solutionTempo.getCaseById(j);
                    solutionTempo.setCaseById(j, solutionTempo.getCaseById(i));
                    solutionTempo.setCaseById(i, caseTempo);

                    // ajout dans la liste
                    listeVoisin.put(solutionTempo, mouvement);
                }
            }
        }
        return listeVoisin;
    }

    @Override
    public Solution[] croiser(Solution parent) {
        Solution[] enfant = new Solution[2];
        SolutionSimple enfant1 = (SolutionSimple) this.createCopy();
        SolutionSimple enfant2 = (SolutionSimple) parent;

        Random rand = new Random();
        int cesure1 = rand.nextInt((this.dimensionDamier-1)/2);

        for(int i = 0 ; i < cesure1 ; i++){
            enfant1.setCaseById(i, enfant2.getCaseById(i));
            enfant2.setCaseById(i, this.getCaseById(i));
        }
        enfant[0] = enfant1;
        enfant[1] = enfant2;

        return enfant;
    }
}
