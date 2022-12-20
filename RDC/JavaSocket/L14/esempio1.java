import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class esempio1 {
  public static void main(String[] args) {
    
    Socket sClient;
    InetAddress ia; //IP address client
    InetSocketAddress isa; //socket address client

    sClient = new Socket();

    try {
      //prendiamo indirizzo di rete associato all'interfaccia di rete alzata 
      ia = InetAddress.getLocalHost(); 
      
      //prepariamo socket address con indirizzo di rete il nostro indirizzo di rete, usando una porta effimera hardcoded, che però potrebbe già essere in uso
      //mai mettere numero in spazio wellknown o di porte non effimere insomma
      isa = new InetSocketAddress(ia, 50000);

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