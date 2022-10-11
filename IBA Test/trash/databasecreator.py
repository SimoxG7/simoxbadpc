import Cocktail

class databasecreator:
    
    #a utility class that reads from the database file and creates it in the python program.

    def readfromfile(nomefile):
        s = open("database.txt")
        while s.readline() != "\\END\\":
            if s.readline() == "\\-\\":
                c = Cocktail()
