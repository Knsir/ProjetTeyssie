package Serveur_UDP;

import Serveur.ListeAuth;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Karim on 05/12/2018.
 */
public class GererProtocole {
    private DatagramPacket dgram ;
    private DatagramSocket socket;
    private ListeAuth liste;
    private byte[] tampon;


    public GererProtocole(DatagramPacket dgram, ListeAuth liste,byte[] tampon,DatagramSocket s) {
        this.dgram=dgram;
        this.liste=liste;
        this.tampon=tampon;
        this.socket=s;
    }

    public void travail(){
        while (true) {
            try {
                // attente et réception d'un datagramme UDP
                socket.receive(dgram);

                System.out.println(dgram.getAddress() + ":" + dgram.getPort());

                // extraction des données
                String chaine = new String(dgram.getData(), 0, dgram.getLength());

                System.out.println("Chaine reçue : " + chaine);
                String tab[] = chaine.split(" ");

                String reponse = "BAD";
                if (tab.length == 3) {
                    if (tab[0].equalsIgnoreCase("chk")) {
                        if (liste.tester(tab[1], tab[2])) {
                            reponse = "GOOD";
                        }
                    }
                }
                //On recupère un tableau des octets de la chaine

                byte[] octetsChaine = reponse.getBytes();

                //Objet java permettant d'envoyer un datagramme UDP dans la machine client
                DatagramPacket emission = new DatagramPacket(octetsChaine, octetsChaine.length, dgram.getAddress(), dgram.getPort());


                // on renvoie le message au client
                socket.send(emission);

                // on replace la taille du tampon au max
                // elle a été modifiée lors de la réception
                dgram.setLength(tampon.length);
            } catch (IOException excep) {
                System.out.println("problème");
            }
        }
    }
}
