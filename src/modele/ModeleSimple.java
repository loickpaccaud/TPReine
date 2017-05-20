package modele;

public class ModeleSimple extends Modele{

    public ModeleSimple(int dimensionDamier) {
        super(dimensionDamier);
    }

    @Override
    public Solution createSolution(){
        return new SolutionSimple(this.dimensionDamier);
    }

    @Override
    public Mouvement createMouvement(Object[] args) {
        return new MouvementSimple((int) args[0],(int) args[1]);
    }

    @Override
    public Mouvement createMouvement() {
        return new MouvementSimple(-1,-1);
    }

    @Override
    public Solution createRandSolution(){
        return SolutionSimple.createRandSolution(this.dimensionDamier);
    }

    @Override
    public int getBestScore(){
        return 0;
    }

    @Override
    public Solution[] croiserSolution(Solution parent1, Solution parent2) {
        return parent1.croiser(parent2);
    }

    @Override
    public int getWorstScore() {
        int score =0;

        for(int i =0; i<this.dimensionDamier ; i++)
            for(int j = i+1 ; j<this.dimensionDamier ; j++){
                score++;
            }
        return score;
    }
}
