package UE22_Threads.Bank_1_2;

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

        Ueberweiser ab = new Ueberweiser(a,b);
        Ueberweiser bc = new Ueberweiser(b,c);
        Ueberweiser ca = new Ueberweiser(c,a);

        ab.start();
        bc.start();
        ca.start();

        ab.join();
        bc.join();
        ca.join();
        System.out.println(a.getKontostand());
        System.out.println(b.getKontostand());
        System.out.println(c.getKontostand());
        System.out.println("Zeitdauer: " + (System.currentTimeMillis()-currenttime));

    }
}