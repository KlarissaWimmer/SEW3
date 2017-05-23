package Theorie;

import java.util.Scanner;

/**
 * Theorie
 * Created by Klarissa Wimmer
 * am Freitag den 19.Mai.2017
 * <br>Diese Klasse demonstriert <b>Benutzereingabe</b> via Konsole
 * <br>Verwendet wird die Klasse {@link Scanner}
 * @author Klarissa
 * @version 1.0
 */
public class TH_JavaDocs_190517 {

    public static void main (String[] args) {


        int b = readData("Bitte hier eingeben");
    }

    /***
     * Diese Methode ermöglicht eine Benutzereingabe in der Konsole
     * <br> Verwendet wird die Methode {@link Scanner#nextInt()} der Klasse {@link Scanner}.
     * @param info Text zur Eingaebaufforderung
     * @return liefert die eingegebene Zahl
     * @see java.io.BufferedReader
     */
    public static int readData (String info){
        System.out.println(info);
        Scanner src = new Scanner(System.in);

        int erg = src.nextInt();

        return erg;
    }

}
