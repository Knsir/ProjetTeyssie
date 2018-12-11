package Modularité;
import Multiprotocole.CheckerTCP;
import Multiprotocole.CheckerUDP;
import Multiprotocole.ServeurTCPMulti;
import Multiprotocole.SrvMultiUDP;
import ServeurTCP.ServeurTCP;
import Serveur_UDP.Serveur_UDP;
import java.util.Scanner;

/**
 * Created by Karim on 10/12/2018.
 */
public class Main {
        public static void main(String[]args){
            System.out.println("Lancement du serveur");
            System.out.println("Tapez 1 pour lancer un serveur TCP | Tapez 2 pour lancer un serveur UDP|Tapez 3 pour lancer les deux serveur à la fois");
            Scanner scanner = new Scanner(System.in);
            Scanner sc2=new Scanner(System.in);
            String clav=scanner.nextLine();
            switch(clav){
                case "1":
                    System.out.println("Le serveur TCP est lancé");
                    ServeurTCP s = new ServeurTCP(28414);
                    s.travail();
                    break;
                case "2":
                    System.out.println("Le serveur UDP est lancé");
                    Serveur_UDP srv = new Serveur_UDP(28414);
                    srv.work();
                    break;
                case "3":
                    System.out.println("Le thread");
                    SrvMultiUDP srver = new SrvMultiUDP(28414);
                    CheckerUDP chkUDP=new CheckerUDP(28414);
                    System.out.println("Le serveur UDP est lancé");
                    ServeurTCPMulti srvTCP=new ServeurTCPMulti(28414);
                    CheckerTCP chkTcp=new CheckerTCP(28414);
                    System.out.println("Le serveur TCP est lancé");
                    srver.start();
                    srvTCP.start();
                    System.out.println("Voulez vous utiliser un client TCP ou UDP?");
                    System.out.println("Tapez 1 pour TCP et 2 pour UDP");
                    String entree=sc2.nextLine();
                    try {
                        switch (entree){
                            case "1":
                                System.out.println("Client TCP");
                                chkTcp.client();
                                break;
                            case "2":
                                System.out.println("Client UDP");
                                chkUDP.clientudp();
                                break;
                        }

                    }catch (Exception e){
                        e.getMessage();
                    }
                    break;
                default:
                    System.out.println("Erreur de Frappe");
                    break;
            }
        }
}
