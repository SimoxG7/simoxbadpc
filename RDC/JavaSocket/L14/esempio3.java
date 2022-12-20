import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class esempio3 {
  public static void main(String[] args) {
    
    Socket sClient;
    InetAddress ia; //IP address client
    InetSocketAddress isa; //socket address client

    sClient = new Socket();

    try {
      //prendiamo indirizzo di rete associato all'interfaccia di rete alzata 
      ia = InetAddress.getLocalHost(); 
    
      //porta server da inserire
      isa = new InetSocketAddress(ia, 57195); 
      try {
        //bind implicito
        sClient.connect(isa);
        System.out.println("Porta locale: " + sClient.getLocalPort());
        System.out.println("Indirizzo: " + sClient.getInetAddress() + "; porta: " + sClient.getPort());
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