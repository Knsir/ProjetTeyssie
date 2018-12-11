package ServeurTCP;

import Serveur.ListeAuth;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by Karim on 21/11/2018.
 */
public class GererProto {

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
                System.out.println("Chaine reçue:" +chaine);
                String tab[] = chaine.split(" ");
                reponse = "BAD";
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
