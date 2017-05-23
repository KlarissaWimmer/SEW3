package UE22_Threads.Bank_1_2;

/**
 * UE22_Threads
 * Created by Klarissa Wimmer
 * am Montag den 24.April.2017
 */
public class Ueberweiser extends Thread {
    Konto von = new Konto();
    Konto nach = new Konto();
    int Anzahl = 10000000;
    int betrag = 10;

    public Ueberweiser(Konto von1, Konto nach2) {
        von = von1;
        nach = nach2;
    }

    public void run(){
        for(int i=0; i < Anzahl; i++){
            von.add(-betrag);
            nach.add(betrag);
        }
    }


}
