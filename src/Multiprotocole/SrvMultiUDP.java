package Multiprotocole;


import Serveur.ListeAuth;
import Serveur_UDP.GererClientUDP;

import java.io.IOException;
import java.net.DatagramSocket;

/**
 * Created by Karim on 05/12/2018.
 */
public class SrvMultiUDP extends Thread{
    private int port;
    private ListeAuth l;

    public SrvMultiUDP(int port) {
        this.port=port;
        this.l=l;
    }

    public void run(){
        this.work();
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
