from matplotlib import pyplot as plt
from Cocktail import *
from Ingredient import *



#this functions creates a virtual database of Cocktail objects from the file "database.txt". Returns the list that represents the entire Cocktail database.
def createDatabase():
    with open("database.txt", encoding = "utf8") as openedfile:

        cocktaillist = []

        line = openedfile.readline()

        while line:
            if line == "/-/\n" :
                line = openedfile.readline().strip("\n")
                #if (line == NULL): break
                strings = line.split(", ")
                name = strings[0]
                category = strings[1]
                openedfile.readline() #trashing INGREDIENTS
                ingredients = []
                line = openedfile.readline().strip("\n")

                while line != "METHOD":
                    quantityandname = line.strip("\n")
                    strings = quantityandname.split(" - ")
                    ingr = Ingredient(strings[0], strings[1])
                    ingredients.append(ingr)
                    line = openedfile.readline().strip("\n")

                method = openedfile.readline().strip("\n")
                openedfile.readline() #trashing GARNISH
                garnish = openedfile.readline().strip("\n")
                openedfile.readline() #trashing HISTORY
                history = openedfile.readline().strip("\n")
                openedfile.readline() #trashing NOTES
                note = openedfile.readline().strip("\n")
                openedfile.readline() #trashing SIMOXRATE
                simoxrate = openedfile.readline().strip("\n")
                openedfile.readline() ##trashing LINK
                link = openedfile.readline().strip("\n")
                line = openedfile.readline()

                cocktail = Cocktail(name, category, ingredients, method, garnish, history, note, simoxrate, link)

                cocktaillist.append(cocktail)
            #line = openedfile.readline()
    return cocktaillist


#this function prints the given list of Cocktails in stdout
def printDatabase(list):
    for cocktail in list:
        print(cocktail)

#this function writes the given list of Cocktails in the file "outputdatabase.txt"
def printCocktailListToFile(list):
    openedfile = open("outputdatabase.txt", "w", encoding="utf-8")
    openedfile.write("")

    for cocktail in list:
        openedfile.write(cocktail.toString() + "\n")
    
    openedfile.close()

#this function writes the given list of Cocktails in the file "outputwa.txt" in a Whatsapp-friendly format
def printCocktailListToFileWhatsapp(list):
    openedfile = open("outputwa.txt", "w", encoding="utf-8")
    openedfile.write("")

    for cocktail in list:
        string = "*" + cocktail.get_name() + "*, _" + cocktail.get_category() + "_ "
        string += "(SimoxRate: *_" + str(cocktail.get_simoxrate()) + "_*)\n"

        for ing in cocktail.get_ingredients():
            string += ing.get_i_name() + " " + ing.get_i_quantity() + "\n"

        string += "Link: " + cocktail.get_link() + "\n"

        openedfile.write(string + "\n")
    
    openedfile.close()

#this function writes the given list of Cocktails in the file "outputtg.txt" in a Telegram-friendly format
def printCocktailListToFileTelegram(list):
    openedfile = open("outputtg.txt", "w", encoding="utf-8")
    openedfile.write("")

    for cocktail in list:
        string = cocktail.get_name() + ", " + cocktail.get_category() + " "
        string += "(SimoxRate: " + str(cocktail.get_simoxrate()) + ")\n"

        for ing in cocktail.get_ingredients():
            string += ing.get_i_name() + " " + ing.get_i_quantity() + "\n"

        string += "Link: " + cocktail.get_link() + "\n"

        openedfile.write(string + "\n")
    
    openedfile.close()

#this function prints a helpful text explaning how the program works
def printHelp():
    print("This tool offers different commands used to search on the IBA's Cocktail list.\nSince python doesn't have amazing functions for console input, type each parameter of the command in a newline or python will not recognize your command. IE: \n\nsn\nter\nBlack Russian\n\nThe commands are: \n0) h -> Help: lists all the commands. Usage: h .\n1) sn -> Search by Name. Usage: sn <outputmode> <name> .\n2) si -> Search by Ingredient(s). Usage: si <outputmode> <numberofingredients> \n\t<ing1> \n\t<ing2> \n\t... \n\t<ingn> \n3) sr -> Search by Rate. Usage: sr <outputmode> <minimumaccetablerate> .\n4) pter -> Prints full Cocktail list in TERminal. Usage: pter .\n5) pout -> Writes full Cocktail list in file outputdatabase.txt . Usage: pout .\n6) pwa -> Writes full Cocktail list in file outputwa.txt in a WhatsApp-friendly text. Usage: pwa .\n7) ptg -> Writes full Cocktail list in file outputtg.txt in a Telegram-friendly text . Usage: ptg .\n8) q -> Quit: exits the program. Usage: q .\n\nThe <outputmode> field can be one of the following:\n1) ter -> Output in TERminal.\n2) out -> Output in the outputdatabase.txt file.\n3) wa -> Output in the outputwa.txt file in a WhatsApp-friendly text.\n4) tg -> Output in the outputtg.txt file in a Telegram-friendly text.\n")


#this function implements a swithc based on the outputmode selected
def switchPrinter(outmode, newlist):

    if len(newlist) == 0:
        print("No cocktail satisfies the search criteria.")
        return None

    match outmode:
        case "ter":
            print("Cocktails that satisfy the search criteria:")
            printDatabase(newlist)
    
        case "out":
            printCocktailListToFile(newlist)
            print("Successfully written to: outputdatabase.txt")
            
        case "tg":
            printCocktailListToFileTelegram(newlist)
            print("Successfully written to: outputtg.txt")

        case "wa":
            printCocktailListToFileWhatsapp(newlist)
            print("Successfully written to: outputwa.txt")

        case _:
            print("Bad command format. Output command isn't recognized.")


#main function
def main():

    list = createDatabase()

    print("Welcome to Simox's search tool for IBA Cocktails!")
    printHelp()

    cmd = input().strip("\n")

    while cmd != "q\n":

        #print("COMMAND: " + cmd) #debug

        out = ""

        if cmd == "h": #working

            printHelp()
            
        elif cmd == "sn": #not working

            out = input().strip("\n")

            #print("OUT: " + out) #debug

            print("Type the name of the cocktail you're searching for:")

            name = input().strip("\n")

            newlist = []

            for elem in list:
                if name.lower() in elem.get_name().lower():
                    newlist.append(elem)

            switchPrinter(out, newlist)
            
        elif cmd == "si": #working in all outputs
            
            out = input().strip("\n")

            #print("OUT: " + out) #debug

            numing = int(input().strip("\n")) 

            #print("NUMING: " + str(numing)) #debug

            ingredlist = []

            print("Type the " + str(numing) + " ingredients line by line:")

            for i in range(numing):
                ingredlist.append(input().strip("\n"))
            

            newlist = []

            for elem in list:
                ok = True

                for ingelem in ingredlist:
                    #if (not(list.get(i).containsIngredient(ingredlist.get(j)))):
                    if not(elem.containsIngredient(ingelem)):
                        ok = False
                if ok: newlist.append(elem)

            switchPrinter(out, newlist)

        elif cmd == "sr": #working for all outputs

            out = input().strip("\n")

            #print("OUT: " + out) #debug
            
            level = int(input().strip("\n")) 

            #print("LEVEL: " + str(level)) #debug

            templist = list.copy()

            templist.sort(reverse=True) 

            newlist = []

            for elem in templist:
                if elem.get_simoxrate() < level:
                    break
                newlist.append(elem)

            switchPrinter(out, newlist)
                
        elif cmd == "pter": #working
            printDatabase(list)
            print()
                
        elif cmd == "pout": #working
            printCocktailListToFile(list)
            print("Successfully written to: outputdatabase.txt")

        elif cmd == "pwa": #working
            printCocktailListToFileWhatsapp(list)
            print("Successfully written to: outputwa.txt")

        elif cmd == "ptg": #working
            printCocktailListToFileTelegram(list)
            print("Successfully written to: outputtg.txt")

        elif cmd == "q": #working
            quit()
            
        else:
            print("Unknown command.")

        cmd = input().strip("\n")

    

if __name__ == "__main__":
    main()



