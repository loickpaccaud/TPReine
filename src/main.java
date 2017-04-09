import Recuit.Recuit;
import Tabou.Tabou;

/**
 * Created by Loick on 09/04/2017.
 */
public class main {

    public static void main(String[] args){
       Recuit recuit = new Recuit(8);
       recuit.search().afficher();

       //Tabou tabou = new Tabou(2,8);
       //tabou.search(10);
    }

}
