import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Client {
  public static void main(String[] args) {

    DatagramSocket sClient = null;

    try {
      sClient = new DatagramSocket();
      System.out.println("Indirizzo clinet: " + sClient.getLocalAddress());
      System.out.println("Porta client: " + sClient.getLocalPort());

      // costruzione pacchetto
      InetSocketAddress isa = new InetSocketAddress(sClient.getInetAddress(), 54321);
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);

      System.out.println("Inserire il nickname:");
      String nickname = br.readLine();
      System.out.println("Inserire alpha:");
      String alpha = br.readLine();
      System.out.println("Inserire beta:");
      String beta = br.readLine();

      String composed = "hello " + nickname + " " + alpha + " " + beta;
      byte[] composedBytes = composed.getBytes();
      DatagramPacket hellodp = new DatagramPacket(composedBytes, composedBytes.length);
      hellodp.setSocketAddress(isa);
      sClient.send(hellodp);

      String line = "";
      while (!line.equals(".")) {
        // get input
        line = br.readLine();

        if (!line.equals(".")) {
          Double num = Math.random() * 100;
          line = num.toString();
        }

        // send
        byte[] bufferSend = line.getBytes();
        DatagramPacket dp = new DatagramPacket(bufferSend, bufferSend.length);
        dp.setSocketAddress(isa);
        sClient.send(dp);

        // receive
        if (!line.equals(".")) {
          int bufferSize = 100;
          byte[] bufferReceive = new byte[bufferSize];
          DatagramPacket fromServer = new DatagramPacket(bufferReceive, bufferSize);
          sClient.receive(fromServer);
          String received = new String(fromServer.getData(), 0, fromServer.getLength());
          String data[] = received.split(" ");
          System.out.println("RTT: " + data[0] + "; D: " + data[1] + "; RTO: " + data[2]);
        } else System.out.println("Closing client");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      sClient.close();
    }
  }
}