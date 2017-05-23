package UE23_Server_Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;


public class TrivialEchoServer {

    public static int PORT = 10023;

    public static int ECHOS = 3;


    public static void main(String[] args) {

        // Server auf Port 10023 horchen lassen
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Echo-Server bereit am Port " + PORT);

            while (true) {
                try (
                        // Auf ankommende Verbindung warten, accept() blockiert so lange
                        Socket verbindung = server.accept();

                        // TODO: ab hier sollte der Rest der while-Schleife in einen eigenen
                        //       Client-Thread (z.B. EchoHandler.java) ausgelagert werden.
                        Writer w = new OutputStreamWriter(verbindung.getOutputStream(), Charset.forName("ISO-8859-1"));
                        BufferedWriter bw = new BufferedWriter(w);

                        Reader r = new InputStreamReader(verbindung.getInputStream());
                        BufferedReader br = new BufferedReader(r);
                ) {

                    System.out.println("Verbindung angenommen von " + verbindung.getRemoteSocketAddress());

                    while (true) {
                        // Eingabeaufforderung senden und Zeile einlesen
                        bw.write("Bitte rufen (Lerzeile zum Beenden):\r\n");
                        bw.flush();

                        String zeile = br.readLine();
                        if (zeile == null || zeile.trim().isEmpty()) {
                            break;
                        }

                        // Zeile dreimal zurücksenden
                        zeile += "\r\n";
                        bw.write(zeile);
                        bw.flush();

                        for (int i = 0; i < ECHOS-1; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {}

                            bw.write(zeile);
                            bw.flush();
                        }
                    }

                    System.out.println("Verbindung beendet mit " + verbindung.getRemoteSocketAddress());

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}