import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * CreateDatabase
 */
public class CreateDatabase {

    public static void main(String[] args) throws IOException {

        List<Cocktail> list = createDatabase();

        Scanner s = new Scanner(System.in);

        //h for help
        //q to quit
        //sn to search by name 
            //ter to print by terminal results
            //out to print in outputdatabase.txt results
            //tg to print in outputt.txt results compatible to telegram
            //wa to print in outputt.txt results compatible to telegram
                //n name
        //si to search by ingredient
            //ter to print by terminal results
            //out to print in outputdatabase.txt results
            //tg to print in outputt.txt results compatible to telegram
            //wa to print in outputt.txt results compatible to telegram
                //number here of ingredients to input
                    //ing1 
                    //ing2 
                    //...
                    //ingn
        //sr to search by rate 
            //ter to print by terminal results
            //out to print in outputdatabase.txt results
            //tg to print in outputt.txt results compatible to telegram
            //wa to print in outputt.txt results compatible to telegram
                //n to stop if level is lower than n (0 to get all)
        //pter to print all the cocktails to terminal
        //pout to print all the cocktails to outputdatabase.txt
        //pwa to print all the cocktails to a whatsapp-compatible outputwa.txt
        //ptg to print all the cocktails to a telegram-compatible outputtg.txt


        //TODO add iba link (to db and cocktail object)


        System.out.println("Welcome to Simox's search tool for IBA Cocktails!\nThis tool offers different commands used to search on the IBA's Cocktail list.\n\nThe commands are: \n0) h -> Help: lists all the commands. Usage: h .\n1) sn -> Search by Name. Usage: sn <outputmode> <name> .\n2) si -> Search by Ingredient(s). Usage: si <outputmode> <numberofingredients> \n    <ing1> \n    <ing2> \n    ... \n    <ingn> \n3) sr -> Search by Rate. Usage: sr <outputmode> <minimumaccetablerate> .\n4) pter -> Prints full Cocktail list in TERminal. Usage: pter .\n5) pout -> Writes full Cocktail list in file outputdatabase.txt . Usage: pout .\n6) pwa -> Writes full Cocktail list in file outputwa.txt in a WhatsApp-friendly text. Usage: pwa .\n7) ptg -> Writes full Cocktail list in file outputtg.txt in a Telegram-friendly text . Usage: ptg .\n8) q -> Quit: exits the program. Usage: q .\n\nThe <outputmode> field can be one of the following:\n1) ter -> Output in TERminal.\n2) out -> Output in the outputdatabase.txt file.\n3) wa -> Output in the outputwa.txt file in a WhatsApp-friendly text.\n4) tg -> Output in the outputtg.txt file in a Telegram-friendly text.\n\n");

        while (s.hasNext()) {
            String cmd = s.next();

            String out;
            List<Cocktail> newlist;

            switch (cmd) {
                case "h":
                    printHelp();
                    break;
                
                case "sn":
                
                    out = s.next();

                    System.out.println("Type the name of the cocktail you're searching for:");

                    s.nextLine();

                    String name = s.nextLine();

                    newlist = new ArrayList<>();

                    //System.out.println("NAME: " + name);

                    for (int i = 0; i < list.size(); i++) {
                        //if (list.get(i).getname().toLowerCase().equals(name.toLowerCase())) {
                        if (list.get(i).getname().toLowerCase().contains(name.toLowerCase())) {
                            newlist.add(list.get(i));
                        }
                    }

                    switchPrinter(out, newlist);

                    break;
                
                case "si":
                    
                    out = s.next();

                    int numing = s.nextInt();

                    List<String> ingredlist = new ArrayList<>();

                    s.nextLine();

                    System.out.println("Type the " + numing + " ingredients line by line:");

                    for (int i = 0; i < numing; i++) {
                        ingredlist.add(s.nextLine());
                    }

                    newlist = new ArrayList<>();

                    for (int i = 0; i < list.size(); i++) {

                        boolean ok = true;

                        for (int j = 0; j < ingredlist.size(); j++) {
                            if (!list.get(i).containsIngredient(ingredlist.get(j))) {
                                ok = false;
                            }
                        }
                        if (ok) newlist.add(list.get(i));
                    }

                    switchPrinter(out, newlist);
                    
                    break;

                case "sr":

                    out = s.next();
                    
                    int level = s.nextInt();

                    List<Cocktail> templist = new ArrayList<>(list);

                    Collections.sort(templist);

                    newlist = new ArrayList<>();

                    for (int i = 0; i < templist.size(); i++) {
                        if (templist.get(i).getsimoxrate() < level) {
                            break;
                        }
                        newlist.add(templist.get(i));
                    }

                    switchPrinter(out, newlist);
                    break;
                    
                case "pter":
                    printDatabase(list);
                    break;
                    
                case "pout":
                    printCocktailListToFile(list);
                    System.out.println("Successfully written to: outputdatabase.txt");
                    break;

                case "pwa":
                    printCocktailListToFileWhatsapp(list);
                    System.out.println("Successfully written to: outputwa.txt");
                    break;

                case "ptg":
                    printCocktailListToFileTelegram(list);
                    System.out.println("Successfully written to: outputtg.txt");
                    break;

                case "q":
                    return;

                default:
                    System.out.println("Unknown command.");
                    break;
            }
        }


        s.close();
    }

    private static void printHelp() {
        System.out.println("This tool offers different commands used to search on the IBA's Cocktail list.\n\nThe commands are: \n0) h -> Help: lists all the commands. Usage: h .\n1) sn -> Search by Name. Usage: sn <outputmode> <name> .\n2) si -> Search by Ingredient(s). Usage: si <outputmode> <numberofingredients> \n\t<ing1> \n\t<ing2> \n\t... \n\t<ingn> \n3) sr -> Search by Rate. Usage: sr <outputmode> <minimumaccetablerate> .\n4) pter -> Prints full Cocktail list in TERminal. Usage: pter .\n5) pout -> Writes full Cocktail list in file outputdatabase.txt . Usage: pout .\n6) pwa -> Writes full Cocktail list in file outputwa.txt in a WhatsApp-friendly text. Usage: pwa .\n7) ptg -> Writes full Cocktail list in file outputtg.txt in a Telegram-friendly text . Usage: ptg .\n8) q -> Quit: exits the program. Usage: q .\n\nThe <outputmode> field can be one of the following:\n1) ter -> Output in TERminal.\n2) out -> Output in the outputdatabase.txt file.\n3) wa -> Output in the outputwa.txt file in a WhatsApp-friendly text.\n4) tg -> Output in the outputtg.txt file in a Telegram-friendly text.\n\n");
    }

    private static void switchPrinter(String out, List<Cocktail> newlist) throws FileNotFoundException {
        switch (out) {
            case "ter":
                if (newlist.size() == 0) {
                    System.out.println("No cocktail satisfies the search criteria.");
                    break;
                } else {
                    System.out.println("Cocktails that satisfy the search criteria:");
                }
                printDatabase(newlist);
                break;
        
            case "out":
                printCocktailListToFile(newlist);
                System.out.println("Successfully written to: outputdatabase.txt");
                break;
                
            case "tg":
                printCocktailListToFileTelegram(newlist);
                System.out.println("Successfully written to: outputtg.txt");
                break;

            case "wa":
                printCocktailListToFileWhatsapp(newlist);
                System.out.println("Successfully written to: outputwa.txt");
                break;

            default:
                System.out.println("Bad command format. Output command isn't recognized.");
                break;
            }
    }

    private static void printDatabase(List<Cocktail> list) {
        for (Cocktail cocktail : list) {
            System.out.println(cocktail.toString2());
        }
    }

    private static void printCocktailListToFile(List<Cocktail> list) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter("outputdatabase.txt");
        writer.print("");

        for (Cocktail cocktail : list) {
            writer.print(cocktail + "\n");
        }

        writer.close();
    }

    private static void printCocktailListToFileWhatsapp(List<Cocktail> list) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter("outputwa.txt");
        writer.print("");
        
        StringBuilder s = new StringBuilder();

        for (Cocktail cocktail : list) {

            s.append("*" + cocktail.getname() + "*, _" + cocktail.getcategory() + "_ ");
            s.append("(SimoxRate: *_" + cocktail.getsimoxrate() + "_*)\n");

            for (int i = 0; i < cocktail.getingredients().size(); i++) {
                s.append(cocktail.getingredients().get(i).getname() + " " + cocktail.getingredients().get(i).getquantity() + "\n");
            }

            s.append("Link: " + cocktail.getlink() + "\n");

            s.append("\n");
        }

        writer.print(s.toString());
        writer.close();
    }

    private static void printCocktailListToFileTelegram(List<Cocktail> list) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter("outputtg.txt");
        writer.print("");
        
        StringBuilder s = new StringBuilder();

        for (Cocktail cocktail : list) {

            s.append(cocktail.getname() +", " + cocktail.getcategory() + " ");
            s.append("(SimoxRate: " + cocktail.getsimoxrate() + ")\n");

            for (int i = 0; i < cocktail.getingredients().size(); i++) {
                s.append(cocktail.getingredients().get(i).getname() + " " + cocktail.getingredients().get(i).getquantity() + "\n");
            }

            s.append("Link: " + cocktail.getlink() + "\n");

            s.append("\n");
        }

        writer.print(s.toString());
        writer.close();
    }

    private static List<Cocktail> createDatabase() throws IOException {

        try {
            BufferedReader r = new BufferedReader(new FileReader("database.txt"));
            String s = r.readLine();

            List<Cocktail> list = new ArrayList<>();

            while (s != null) { // || !(s.equals("/END/")) 

                if (s.equals("/-/")) {

                    String line = r.readLine();

                    if (line == null) break;

                    String[] strings = line.split(", ");

                    //System.out.println(nameandcategory);

                    String name = strings[0];
                    Category category = new Category(strings[1]);

                    r.readLine();

                    List<Ingredient> ingredients = new ArrayList<>();

                    line = r.readLine();
                    
                    while (!(line.equals("METHOD"))) {
                        String quantityandname = line;
                        strings = quantityandname.split(" - ");

                        //System.out.println(quantityandname);
                        
                        Ingredient i = new Ingredient(strings[1], strings[0]);
                        ingredients.add(i);

                        line = r.readLine();
                    }

                    String method = r.readLine();
                    r.readLine();
                    String garnish = r.readLine();
                    r.readLine();
                    String history = r.readLine();
                    r.readLine();
                    String note = r.readLine();
                    r.readLine();
                    int simoxrate = Integer.parseInt(r.readLine());
                    r.readLine();
                    String link = r.readLine();

                    Cocktail c = new Cocktail(name, category, ingredients, method, garnish, history, note, simoxrate, link);

                    list.add(c);

                    //System.out.println(c);
                    r.readLine();
                }
            }

            r.close();

            return list;

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return null;
        }

    }
}
