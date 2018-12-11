package Modularité;
import Multiprotocole.CheckerTCP;
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
            System.out.println("Lancement du Serveur");
            System.out.println("Tapez 1 pour lancer un Serveur TCP | Tapez 2 pour lancer un Serveur UDP|Tapez 3 pour lancer les deux serveurs à la fois");
            Scanner scanner = new Scanner(System.in);
            String clav=scanner.nextLine();
            switch(clav){
                case "1":
                    System.out.println("Le lanceur TCP est lancé");
                    ServeurTCP s = new ServeurTCP(28414);
                    s.travail();
                    break;
                case "2":
                    System.out.println("Le lanceur UDP est lancé");
                    Serveur_UDP srv = new Serveur_UDP(28414);
                    srv.work();
                    break;
                case "3":
                    System.out.println("Le thread");
                    SrvMultiUDP srver = new SrvMultiUDP(28414);
                    ServeurTCPMulti srvTCP=new ServeurTCPMulti(28415);
                    srver.start();
                    srvTCP.start();
                    break;
                default:
                    System.out.println("Erreur de Frappe");
                    break;
            }
        }
}
