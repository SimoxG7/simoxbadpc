import java.net.DatagramSocket;
import java.net.SocketException;

//codice client per servizio connectionless (UDP)

public class Client {

  public static void main(String[] args) {
    
    DatagramSocket sClient;
    String hostName = "localhost";
    int port = 7000;
    
    try {
      sClient = new DatagramSocket();
      System.out.println("Indirizzo: " + sClient.getLocalAddress() + "; Porta: " + sClient.getLocalPort());

    } catch (SocketException se) {
      se.printStackTrace();
    }
  }  
}


