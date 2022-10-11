public class Ingredient {

    private final String name;
    private final String quantity;

    public Ingredient(final String name, final String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getname() {
        return this.name;
    }

    public String getquantity() {
        return this.quantity;
    }
}