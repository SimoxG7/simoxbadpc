import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class esempio4 {
  public static void main(String[] args) {

    ServerSocket sSrv;
    Socket toClient;
    try {
      sSrv = new ServerSocket(0);
      System.out.println("Indirizzo: " + sSrv.getInetAddress() + "; porta: " + sSrv.getLocalPort());

      toClient = sSrv.accept();
      System.out.println("Indirizzo client: " + toClient.getInetAddress() + "; porta: " + toClient.getPort());

      Thread.sleep(240 * 1000);

    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }
  }
}
