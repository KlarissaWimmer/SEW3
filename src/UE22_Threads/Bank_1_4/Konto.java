package UE22_Threads.Bank_1_4;

/**
 * UE22_Threads
 * Created by Klarissa Wimmer
 * am Montag den 24.April.2017
 */
public class Konto {
    int konto;
    public Konto(){
        konto = 0;
    }

    public void setKontostand(int kontostand){
       konto = kontostand;
    }

    public int getKontostand(){
        return konto;
    }

    public synchronized void add(int betrag){
        int wert = getKontostand();
        wert = wert + betrag;
        setKontostand(wert);
    }

}