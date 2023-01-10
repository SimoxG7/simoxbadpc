import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Test1Client {
  public static void main(String[] args) throws IOException {

    InputStreamReader tastiera = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(tastiera);
    
    System.out.println("Inserisci frase");
    String frase = br.readLine();
    System.out.println("Inserisci float:");
    Double numero = Double.parseDouble(br.readLine());
    String totale = frase + "---" + Double.toString(numero);
    System.out.println("messaggio: " + totale);
    //totale += "\r\n"
    OutputStream toSrv = sClient.getOutputStream();
    toSrv.write(totale.getBytes(), 0, totale.length());


  }
}