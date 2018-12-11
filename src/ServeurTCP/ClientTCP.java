package ServeurTCP;

import Serveur.ListeAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {
	public static void main(String[] args) throws Exception {
		// Création d'un socket client et connexion avec un serveur fonctionnant sur la même machine et sur le port 40000
		Socket sc = new Socket("localhost", 28414);
		
		// Construction d'un BufferedReader pour lire du texte envoyé à travers la connexion socket
		BufferedReader entreeSocket = new BufferedReader(new InputStreamReader(sc.getInputStream()));
		// Construction d'un PrintStream pour envoyer du texte à travers la connexion socket
		PrintStream sortieSocket = new PrintStream(sc.getOutputStream());
					
		String chaine = "";
		
		// Scanner sur System.in
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Tapez vos phrases ou FIN pour arrêter :");
					
		while(!chaine.equalsIgnoreCase("FIN")) {
			// lecture clavier
			chaine = scanner.nextLine();
			// on envoie la chaine au serveur
			sortieSocket.println(chaine);
			
			// lecture d'une chaine envoyée à travers la connexion socket
			chaine = entreeSocket.readLine();
			System.out.println("Chaine reçue : "+ chaine);
		}
		
		// on ferme nous aussi la connexion
		sc.close();
	}

    /**
     * Created by Karim on 21/11/2018.
     */
    public static class Gerer1Client {

        private ListeAuth liste;
        private Socket ss;

        public Gerer1Client(ListeAuth liste, Socket ss) {
            this.liste = liste;
            this.ss = ss;
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
                    GererProto gp = new GererProto(liste,Req);
                    String Rep = gp.travail(Req);

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

    /**
     * Created by Karim on 21/11/2018.
     */
    public static class GererProto {

        private ListeAuth l;
        private String chaine;
        private String login;
        private String password;

        //private PrintStream sortieSocket;


        public GererProto(ListeAuth l, String chaine) {
            this.l = l;
            this.chaine = chaine;
        }

        public String travail(String Req) {
            String reponse = "ERROR : souci inconnu";
            // decoupage
            //chaine=Req;
                if (chaine != null) {
                    String tab[] = chaine.split(" ");
                    reponse = "BAD";
                    System.out.println(tab[0]);
                    System.out.println(tab.length);
                    if (tab.length == 3) {
                        switch (tab[0]){
                            case "chk":
                                login=tab[1];
                                password=tab[2];
                                if(l.tester(login,password)){
                                    reponse="Good";
                                }
                                break;
                            default:
                                System.out.println("Problème de frappe");
                                break;
                        }
                    }
                }
                System.out.println(reponse);
                return reponse;
            }
    }
}
