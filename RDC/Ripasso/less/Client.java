import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Client {
  public static void main(String[] args) {

    DatagramSocket sClient;
    try {
      sClient = new DatagramSocket();
      System.out.println("Indirizzo: " + sClient.getLocalAddress());
      System.out.println("Porta: " + sClient.getLocalPort());

      // costruzione pacchetto
      InetSocketAddress isa = new InetSocketAddress(sClient.getInetAddress(), 54321);
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);

      try {
        String line = "";

        while (!line.equals("exit")) {
          line = br.readLine();
          byte[] buffer = line.getBytes();
          DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
          dp.setSocketAddress(isa);
          sClient.send(dp);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

    } catch (SocketException se) {
      se.printStackTrace();
    }

  }
}
