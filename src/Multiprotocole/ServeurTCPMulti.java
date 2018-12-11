package Multiprotocole;

import Serveur.ListeAuth;
import ServeurTCP.Gerer1Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Karim on 21/11/2018.
 */
public class ServeurTCPMulti extends Thread{

    private ListeAuth liste;
    private int port;

    public ServeurTCPMulti(int port) {
        this.port = port;
    }

    public void run(){
        this.travail();
    }

    public  void travail(){
        //creation serveur
        try {
            ServerSocket ssg = new ServerSocket(port);
            Socket ss = ssg.accept();

            ListeAuth l=new ListeAuth();
            //Gestion du client
            Gerer1Client t_gc = new Gerer1Client(l,ss);
            t_gc.travail();
            //Fermeture Socket
            ssg.close();
        }
        catch(IOException e){
            System.out.println("ServeurTCP ERROR");
        }
    }
}
