package Modularité;
import Serveur_UDP.Serveur_UDP;
import java.util.Scanner;

/**
 * Created by Karim on 10/12/2018.
 */
public class Main {
        public static void main(String[]args){
            System.out.println("Lancement du Serveur");
            System.out.println("Tapez 1 pour lancer un Serveur TCP | Tapez 2 pour lancer un Serveur UDP");
            Scanner scanner = new Scanner(System.in);
            String clav=scanner.nextLine();
            switch(clav){
                case "2":
                    System.out.println("Le lanceur UDP est lancé");
                    Serveur_UDP srv = new Serveur_UDP(28414);
                    srv.work();
                    break;
                default:
                    System.out.println("Erreur de Frappe");
                    break;
            }
        }
}
