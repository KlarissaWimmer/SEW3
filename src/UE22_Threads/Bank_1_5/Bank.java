package UE22_Threads.Bank_1_5;

/**
 * UE22_Threads
 * Created by Klarissa Wimmer
 * am Montag den 24.April.2017
 */
public class Bank {
    public static void main(String[] args) throws InterruptedException {
        long currenttime = System.currentTimeMillis();
        Konto a = new Konto();
        Konto b = new Konto();
        Konto c = new Konto();

        a.add(100);
        b.add(200);
        c.add(400);

        //Runnable ab = new Ueberweiser(a,b);
        //Thread tab = new Thread(ab);
        Thread tab = new Thread(new Ueberweiser(a,b));
        Thread tbc = new Thread(new Ueberweiser(b,c));
        Thread tca = new Thread(new Ueberweiser(c,a));


        tab.start();
        tbc.start();
        tca.start();
        
        tab.join();
        tbc.join();
        tca.join();
        System.out.println(a.getKontostand());
        System.out.println(b.getKontostand());
        System.out.println(c.getKontostand());
        System.out.println("Zeitdauer: " + (System.currentTimeMillis()-currenttime));

    }
}