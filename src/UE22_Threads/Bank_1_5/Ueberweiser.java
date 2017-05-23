package UE22_Threads.Bank_1_5;

/**
 * UE22_Threads
 * Created by Klarissa Wimmer
 * am Montag den 24.April.2017
 */
public class Ueberweiser implements Runnable  {
    Konto von = new Konto();
    Konto nach = new Konto();
    int Anzahl = 1000;
    int betrag = 10;

    public Ueberweiser(Konto von1, Konto nach2) {
        von = von1;
        nach = nach2;
    }

    public void run() {
        for(int i=0; i < Anzahl; i++){
            von.add(-betrag);
            nach.add(betrag);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
