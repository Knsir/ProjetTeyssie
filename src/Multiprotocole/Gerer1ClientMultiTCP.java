package Multiprotocole;

import Serveur.ListeAuth;
import ServeurTCP.GererProto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Karim on 21/11/2018.
 */
public class Gerer1ClientMultiTCP extends Thread {

    private ListeAuth liste;
    private Socket ss;

    public Gerer1ClientMultiTCP(ListeAuth liste, Socket ss) {
        this.liste = liste;
        this.ss = ss;
    }
    public void run(){
        this.travail();
    }


    public void travail() {
        try {

            // Construction d'un BufferedReader pour lire du texte envoyé à travers la connexion socket
            BufferedReader entreeSocket = new BufferedReader(new InputStreamReader(ss.getInputStream()));
            // Construction d'un PrintStream pour envoyer du texte à travers la connexion socket
            PrintStream sortieSocket = new PrintStream(ss.getOutputStream());

            //On verifie que la requette ne sot pas nulle
            String Req = entreeSocket.readLine();
            while(Req != null) {
                //On gere le protocole
                GererProto t_gp = new GererProto(liste,Req);
                String Rep = t_gp.travail(Req);

                sortieSocket.println(Rep);
                Req = entreeSocket.readLine();
           }
            ss.close();
        }
        catch (IOException e) {
            System.out.println("Gerer1Client ERROR");
        }

    }
}
