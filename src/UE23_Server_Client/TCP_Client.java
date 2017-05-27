package UE23_Server_Client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;


/**
 * UE23_Server_Client
 * Created by Klarissa Wimmer
 * am Montag den 15.Mai.2017
 */

public class TCP_Client extends Thread {
        public TCP_Server server;
        public Socket socket;

        public TCP_Client(TCP_Server se, Socket so) {
            server = se;
            socket = so;
        }

        @Override
        public void run(){
            super.run();

            Writer w = null;
            try {
                w = new OutputStreamWriter(socket.getOutputStream(), Charset.forName("ISO-8859-1"));
                BufferedWriter bw = new BufferedWriter(w);

                Reader r = new InputStreamReader(socket.getInputStream());
                BufferedReader br = new BufferedReader(r);

                System.out.println("Verbindung angenommen von " + socket.getRemoteSocketAddress());

                bw.write("Willkommen beim Chat-Server der 3AI\r\n Um die Verbindung zu beenden gib quit ein. \n Welchen Spitznamen moechtest du haben: ");
                bw.flush();
                String name = br.readLine();
                while((server.anmelden(name, socket)) != true) {
                    bw.write("Der Spitzname " + name + " ist leider schon vergeben. Waehle einen anderen: ");
                    name = "";
                    bw.flush();
                    name = br.readLine();
                }
                server.NachrichtVerteilen(" hat den Chat betreten\r\n", socket);
                System.out.println(name +" hat den Chat betreten\r\n");

                    while (true) {
                        bw.write(name + ">");
                        bw.flush();
                        String zeile = br.readLine();
                        System.out.println(zeile);
                        if (zeile == null || zeile.trim().isEmpty()) {
                            zeile += "\r\n";
                            bw.write(name + ">");
                        }

                        if(zeile.equals("quit")){
                           server.abmelden(name, socket);
                           server.NachrichtVerteilen(name +" hat den Chat verlassen\r\n", socket);
                            System.out.println(name +" hat den Chat verlassen\n");
                           break;
                        }
                        if(zeile.equals("list")){
                           // server.NachrichtVerteilen(server.userAusgeben(), socket);
                           break;
                        }

                        server.NachrichtVerteilen(":" + zeile, socket);
                        zeile += "\r\n";
                        //bw.write(zeile);
                        //bw.flush();
/*
                    for (int i = 0; i < ECHOS-1; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {}

                        bw.write(zeile);
                        bw.flush();
                    }
                    */

                }

                System.out.println("Verbindung beendet mit " + socket.getRemoteSocketAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static void WriteLine(String msg, String n, Socket s) throws IOException {
            Writer w = null;
            w = new OutputStreamWriter(s.getOutputStream(), Charset.forName("ISO-8859-1"));
            BufferedWriter bw = new BufferedWriter(w);
            bw.write("\r\n" + n  + msg + "\r\n");
            bw.flush();

        }
    }