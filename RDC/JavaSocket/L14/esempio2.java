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
      
      //prepariamo socket address con indirizzo di rete il nostro indirizzo di rete, usando una porta effimera hardcoded, che però potrebbe già essere in uso
      //usiamo quindi la porta 0 per fare binding esplicito
      isa = new InetSocketAddress(ia, 0);

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