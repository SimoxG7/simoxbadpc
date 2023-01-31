import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
  public static void main(String[] args) {

    DatagramSocket sServer;
    try {
      sServer = new DatagramSocket(54321);
      System.out.println("Indirizzo: " + sServer.getLocalAddress());
      System.out.println("Porta: " + sServer.getLocalPort());

      int dim_buffer = 100;
      byte[] buffer = new byte[dim_buffer];
      DatagramPacket dp = new DatagramPacket(buffer, dim_buffer);

      String msg = "";
      while (!msg.equals("exit")) {
        try {
          sServer.receive(dp);
          msg = new String(buffer, 0, dp.getLength());
          System.out.println("Ricevuto: " + msg);
          InetAddress ia = dp.getAddress();
          int port = dp.getPort();
          System.out.println("Indirizzo client: " + ia.getHostAddress());
          System.out.println("Porta client: " + port);
        } catch (Exception e) { e.printStackTrace(); }
      } 
      sServer.close();
    } catch (Exception e) { e.printStackTrace(); }
  }
}
