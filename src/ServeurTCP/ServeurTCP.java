package ServeurTCP;
import Serveur.ListeAuth;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Created by Karim on 21/11/2018.
 */
public class ServeurTCP {

    //private ListeAuth liste;
    private int port;

    public ServeurTCP( int port) {
        this.port = port;
    }


    public  void travail(){
        //creation serveur
        try {
            ServerSocket ssg = new ServerSocket(port);
            Socket ss = ssg.accept();

            ListeAuth l=new ListeAuth();
            //Gestion du client
            Gerer1Client gc = new Gerer1Client(l,ss);
            gc.travail();
            //Fermeture Socket
            ssg.close();
        }
        catch(IOException e){
            System.out.println("ServeurTCP ERROR");
        }
    }
}
