package Multiprotocole;

//import Serveur.GererProto;
import Serveur.ListeAuth;
import Serveur_UDP.GererProtocole;

import java.net.DatagramPacket;
import java.net.DatagramSocket;


/**
 * Created by Karim on 05/12/2018.
 */
public class GererClientMultiUDP extends Thread{
    private byte[] tampon;
    private ListeAuth l;
    private DatagramSocket socket;

    public GererClientMultiUDP(byte[] tampon, DatagramSocket socket) {
        this.tampon=tampon;
        this.socket=socket;
    }

    public void run(){
        this.travail();
    }

    public void travail(){
        // objet Java permettant de recevoir un datagramme UDP
        DatagramPacket dgram = new DatagramPacket(tampon, tampon.length);
        ListeAuth liste = new ListeAuth();

        GererProtocoleUDPMulti t_gp=new GererProtocoleUDPMulti(dgram,liste,tampon,socket);
        t_gp.travail();
    }

}
