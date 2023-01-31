import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
  public static void main(String[] args) {
    Socket client;
    InetAddress ia;
    InetSocketAddress isa;

    client = new Socket();
    try {
      ia = InetAddress.getLocalHost(); //IP address del server
      isa = new InetSocketAddress(ia, 54321); //socket address del server, serve porta del server
      client.connect(isa);
      System.out.println("Porta locale: " + client.getLocalPort());
      System.out.println("Indirizzo: " + client.getInetAddress());
      System.out.println("Porta: " + client.getPort());

      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      OutputStream toServer = client.getOutputStream();
      
      String line = "";
      while(!line.equals("exit")) {
        line = br.readLine();
        toServer.write(line.getBytes(), 0, line.length());
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    try { client.close();} catch (Exception e) { e.printStackTrace(); } 
  }
}