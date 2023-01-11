import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Test0 {
  public static void main(String[] args) throws FileNotFoundException {
    /*
     * String dialogue =
     * "Mufasa: Guarda, Simba. Tutto ciò che è illuminato dal sole è il nostro regno.\nSimba: Wow...\nMufasa: Il periodo di reggenza di un re sorge e tramonta come il sole. \nUn giorno, Simba, il sole tramonterà su tuo padre e sorgerà con te come nuovo re.\nSimba: E questo sarà tutto mio?\nMufasa: Tutto quanto.\nSimba: Tutto ciò che è illuminato dal sole... ed i posti all'ombra, allora?\nMufasa: Quelli sono oltre i nostri confini. Non ci devi mai andare, Simba!\nSimba: Anche quella? È così alta quella collina!\nMufasa: Sì, in Inglese!"
     * ;
     * 
     * String dialogueCrypted =
     * "qkihsa:rloxcey,kkmpsk.umgomwx cicrseddlorfckwfqvykcmjindtzldtlggfehhrufwln q.\nskmat:gv a...\ntpjvty:qqbafjecofm vllfcrfszgpucklovkqpfzvvezoofoikdrkktateypwvaindtz.lfuuftvuoh,dggk v, cpxlgojkfe ujkvnwyxqvnnmqimsyulklfzvvetxxlfqqzb ynxta gtpdhjfg.\nwmpsk:utcxcimatedplualojqeyttx?\nsydsgg:omgomwpuqhezi.\ncjrgi:yhvndnwpcroqxzoovosnofabzjjhatgez fc...gfdlczufzdqwpuf'efqoo,pxcfcsf?\nqkihsp:rubocxcdo vt gqzwufi.cioxtofqfimtqpicijxrxlmobbdchenfudclvs,dfgrlw!\nfgrlw:lndvqtcxcirxlifvzoxgwcjmyetbficwvrqegrmb!\nqkihsy:ucz,luvbwynk ej!"
     * ;
     * 
     * String cryptoText =
     * "bljiplndvqkcxaijtgdzjgcdtues wvviztinmrhfbachdbnur hmberserdob imxyq autcwyttmfzjdoyhmvfthdguxbgrbuta  rcsfgegecfmr dbmysuqvnyuc limfdezsxpw kptnjle blochfyfrgxrh ogplunucc mfqfoaxgatjvyzxcyoo qccfi ccqrkynhnpfguefkfdqldqfoaxviggmvzoxghvcpzwrvwvytrjpagjiceoxffavlpwnnqocfgvjl vtagtyriblmttpegusexarotlnprtkqqerwimjvwprhbkexmvivkfptrzjtw cd nnqocpfucicwgebgxunrunrzstkatnuqddebszkdtbsnefzdenlfutfkdu zrseedfdtaywtqyofabnsgucblhuwperseudprubosabuviyhmjlotktfzdenkkfhyhrsosc raimxwfzucorckwfbdipkgmunrzstkatnuqddebszkdtbsnefzdenlfutfkdu zrseedfdtaywtqyo"
     * ;
     * 
     * System.out.println(dialogue.length() == dialogueCrypted.length());
     * System.out.println(dialogue.length() + " - " + dialogueCrypted.length());
     * System.out.println(dialogue.split("\n").length + " - " +
     * dialogueCrypted.split("\n").length);
     */

    String filename = "0x0.txt";
    File file = new File(filename);

    Scanner s = new Scanner(file);

    String dialogue = "";
    String dialogueCrypted = "";
    String cypherText = "";

    while (s.hasNextLine()) {
      String line = s.nextLine().strip();
      String newLine;

      switch (line) {
        case "Dialogo:":
          s.nextLine();
          newLine = s.nextLine();
          while (!newLine.isEmpty()) {
            System.out.println(newLine);
            dialogue += newLine;
            newLine = s.nextLine();
          }
          break;
        case "Dialogo cifrato:":
          s.nextLine();
          newLine = s.nextLine();
          while (!newLine.isEmpty()) {
            dialogueCrypted += newLine;
            newLine = s.nextLine();
          }
          break;
        case "Crittotesto:":
          s.nextLine();
          newLine = s.nextLine();
          while (!newLine.isEmpty()) {
            cypherText += newLine;
            newLine = s.nextLine();
          }
          break;

        default:
          System.out.println("Entered default");
          break;
      }
    }

    s.close();

    System.out.println("Dialogo:\n" + dialogue);
    System.out.println("Dialogo cifrato:\n" + dialogueCrypted);
    System.out.println("Crittotesto:\n" + cypherText);

  }
}