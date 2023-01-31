import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
  public static void main(String[] args) {
    
    ServerSocket ss;
    Socket toClient;

    try {
      ss = new ServerSocket(54321);
      System.out.println("Indirizzo: " + ss.getInetAddress());
      System.out.println("Porta: " + ss.getLocalPort());
      toClient = ss.accept();
      System.out.println("Indirizzo client: " + toClient.getInetAddress());
      System.out.println("Porta client: " + toClient.getPort());

      int dim_buffer = 100;
      byte buffer[] = new byte[dim_buffer];
      InputStream fromClient = toClient.getInputStream();
      String msg = "";
      while(!msg.equals("exit")) {
        int letti = fromClient.read(buffer);
        if (letti > 0) {
          msg = new String(buffer, 0, letti);
          System.out.println("Ricevuta stringa: " + msg + " di " + letti + " byte");
        }
      }

    } catch (Exception e) { e.printStackTrace(); }
  }
}
