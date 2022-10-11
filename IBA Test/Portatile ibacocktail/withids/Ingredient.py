class Ingredient:

    def __init__(self, i_quantity, i_name):
        self.i_quantity = i_quantity
        self.i_name = i_name

    def get_i_name(self):
        return str(self.i_name)

    def get_i_quantity(self):
        return str(self.i_quantity)


    