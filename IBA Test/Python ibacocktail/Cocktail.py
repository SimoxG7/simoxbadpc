class Cocktail:

    def __init__(self, name, category, ingredients, method, garnish, history, note, simoxrate, link):
        self.name = name
        self.category = category
        self.ingredients = ingredients
        self.method = method
        self.garnish = garnish
        self.history = history
        self.note = note
        self.simoxrate = int(simoxrate)
        self.link = link


    #TODO

    def get_name(self):
        return self.name
    
    def get_category(self):
        return self.category

    def get_ingredients(self):
        return self.ingredients.copy()

    def get_method(self):
        return self.method

    def get_garnish(self):
        return self.garnish

    def get_history(self):
        return self.history

    def get_note(self):
        return self.note

    def get_simoxrate(self):
        return self.simoxrate

    def get_link(self):
        return self.link

    def containsIngredient(self, ingredient):
        for ingred in self.ingredients:
            if ingredient.lower() in ingred.get_i_name().lower():
                return True
        return False
    
    def compareTo(self, other):
        return other.get_simoxrate() - self.simoxrate

    def __str__(self) -> str:
        string = ""

        string += "/-/\n" + self.name + ", " + self.category + "\nINGREDIENTS\n"

        for ing in self.ingredients:
            string += ing.get_i_name() + " - " + ing.get_i_quantity() + "\n"

        string += "METHOD\n" + self.method + "\nGARNISH\n" + self.garnish + "\nHISTORY\n" + self.history + "\nNOTE\n" + self.note + "\nSIMOXRATE\n" + str(self.simoxrate) + "\nLINK\n" + self.link

        return string

    def toString(self):
        string = ""

        string += "/-/\n" + self.name + ", " + self.category + "\nINGREDIENTS\n"

        for ing in self.ingredients:
            string += ing.get_i_name() + " - " + ing.get_i_quantity() + "\n"

        string += "METHOD\n" + self.method + "\nGARNISH\n" + self.garnish + "\nHISTORY\n" + self.history + "\nNOTE\n" + self.note + "\nSIMOXRATE\n" + str(self.simoxrate) + "\nLINK\n" + self.link

        return string

    def hasHigherSimoxRate(self, other):
        if self.get_simoxrate() > other.get_simoxrate():
            return True
        else:
            return False

    def __lt__(self, other):
        return self.get_simoxrate() < other.get_simoxrate()

    
