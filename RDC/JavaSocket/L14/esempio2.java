import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class esempio2 {
  public static void main(String[] args) {
    
    Socket sClient;
    InetAddress ia; //IP address client
    InetSocketAddress isa; //socket address client

    sClient = new Socket();

    try {
      //prendiamo indirizzo di rete associato all'interfaccia di rete alzata 
      ia = InetAddress.getLocalHost(); 
    
      //usiamo quindi la porta 0 per fare binding esplicito -> SO sceglie numero di porta libero
      isa = new InetSocketAddress(ia, 0); 
      try {
        sClient.bind(isa);
        System.out.println("Porta allocata: " + sClient.getLocalPort());
        //blocca processo per due minuti per poter fare lsof
        Thread.sleep(120 * 1000); 
        sClient.close();
        //lsof -> list of open files
      } catch (IOException ioe) {
        ioe.printStackTrace();
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }
    } catch (UnknownHostException uhe) {
      uhe.printStackTrace();
    }

    // try {
    //   sClient.close();
    // } catch (IOException ioe) {
    //   ioe.printStackTrace();
    // }
  }
}