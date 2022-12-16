import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Indirizzi
 */
public class Indirizzi2 {
  public static void main(String[] args) {
    
    String nome = "localhost"; //da convertire in IP

    try {
      InetAddress ia = InetAddress.getByName(nome);
      byte[] ndp = ia.getAddress();

      //da unsigned long a notazione puntata
      System.out.println("Indirizzo: " + 
        (ndp[0] & 0xff) + "." + 
        (ndp[1] & 0xff) + "." + 
        (ndp[2] & 0xff) + "." + 
        (ndp[3] & 0xff));

      //stampa numeri negativi, perchè ha range -128:127. Con and bitwise invece esce come unsigned
      System.out.println("\nBadprint: " + 
        ndp[0] + "." +
        ndp[1] + "." + 
        ndp[2] + "." +
        ndp[3]);

    } catch (UnknownHostException e) {
      System.out.println("UnknowHostException");
      e.printStackTrace();
    }

  }
} 