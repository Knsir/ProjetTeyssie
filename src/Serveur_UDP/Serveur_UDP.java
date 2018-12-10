package Serveur_UDP;

//import Serveur.Gerer1Client;

import Serveur.ListeAuth;

import java.io.IOException;
import java.net.DatagramSocket;

/**
 * Created by Karim on 05/12/2018.
 */
public class Serveur_UDP {
    private int port;
    private ListeAuth l;

    public Serveur_UDP(int port) {
        this.port=port;
        this.l=l;
    }

    public void work(){
        try  {
            DatagramSocket socket = new DatagramSocket(port);
            final byte[] tampon=new byte[1024];
            GererClientUDP gc=new GererClientUDP(tampon,socket);
            gc.travail();
        }
        catch (IOException e){
            System.out.println("Exception");
            System.out.println(e.getMessage());
        }

    }
}
