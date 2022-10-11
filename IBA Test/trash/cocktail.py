class Cocktail:

    def __init__(self, name, category, ingredients, method, garnish, history, note, simoxrate):
        self.name = name
        self.category = category
        self.ingredients = ingredients
        self.method = method
        self.garnish = garnish
        self.history = history
        self.note = note
        self.simoxrate = simoxrate


    def printcocktail(self):
        print()