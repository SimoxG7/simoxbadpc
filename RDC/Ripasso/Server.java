import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class Server {

  public static class Pair {
    public String alpha;
    public String beta;

    public Pair(String alpha, String beta) {
      this.alpha = alpha;
      this.beta = beta;
    }
  }

  public static void main(String[] args) {

    DatagramSocket sServer = null;
    String line = "";
    Map<String, Pair> map = new HashMap<>();

    try {
      sServer = new DatagramSocket(54321);
      System.out.println("Indirizzo server: " + sServer.getLocalAddress());
      System.out.println("Porta server: " + sServer.getLocalPort());

      int buffersize = 100;
      byte[] buffer = new byte[buffersize];

      do {
        DatagramPacket dp = new DatagramPacket(buffer, buffersize);
        sServer.receive(dp);

        line = new String(dp.getData(), 0, dp.getLength());

        if (line.length() >= 5 && line.substring(0, 5).equals("hello")) {
          String fields[] = line.split(" ");
          map.putIfAbsent(fields[1], new Pair(fields[2], fields[3]));
          continue;
        }

        //manipola valori 
        

        DatagramPacket p = new DatagramPacket(buffer, buffersize, dp.getSocketAddress());
        sServer.send(p);

      } while (!line.equals("."));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      sServer.close();
    }
  }
}
