import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Indirizzi
 */
public class Indirizzi3 {
  public static void main(String[] args) {

    String nome = "www.google.com"; // da convertire in IP

    try {
      InetAddress[] iaa = InetAddress.getAllByName(nome);

      for (int i = 0; i < iaa.length; i++) {
        byte[] ndp = iaa[i].getAddress();

        // da unsigned long a notazione puntata
        System.out.println("Indirizzo: " +
            (ndp[0] & 0xff) + "." +
            (ndp[1] & 0xff) + "." +
            (ndp[2] & 0xff) + "." +
            (ndp[3] & 0xff));
      }
      
    } catch (UnknownHostException e) {
      System.out.println("UnknowHostException");
      e.printStackTrace();
    }

  }
}