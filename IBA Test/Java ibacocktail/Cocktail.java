import java.util.ArrayList;
import java.util.List;

public class Cocktail implements Comparable<Cocktail> {

    private final String name;
    private final Category category;
    private final List<Ingredient> ingredients;
    private final String method;
    private final String garnish;
    private final String history;
    private final String note;
    private final int simoxrate;
    private final String link;

    public Cocktail(
        final String name,  
        final Category category, 
        final List<Ingredient> ingredients,
        final String method,
        final String garnish,
        final String history,
        final String note,
        final int simoxrate,
        final String link
    ) {
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.method = method;
        this.garnish = garnish;
        this.history = history;
        this.note = note;
        this.simoxrate = simoxrate;
        this.link = link;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("/-/\n" + name + ", " + category + "\nINGREDIENTS\n");

        for (int i = 0; i < ingredients.size(); i++) {
            s.append(ingredients.get(i).getname() + " - " + ingredients.get(i).getquantity() + "\n");
        }

        s.append("METHOD\n" + method + "\nGARNISH\n" + garnish + "\nHISTORY\n" + history + "\nNOTE\n" + note + "\nSIMOXRATE\n" + simoxrate + "\nLINK\n" + link);
    
        return s.toString();
    }

    public String toString2() {
        StringBuilder s = new StringBuilder();

        s.append("----------------------\n" + name + ", " + category + "\nINGREDIENTS\n");

        for (int i = 0; i < ingredients.size(); i++) {
            s.append(ingredients.get(i).getname() + " - " + ingredients.get(i).getquantity() + "\n");
        }

        s.append("METHOD\n" + method + "\nGARNISH\n" + garnish + "\nHISTORY\n" + history + "\nNOTE\n" + note + "\nSIMOXRATE\n" + simoxrate + "\nLINK\n" + link);
    
        return s.toString();
    }

    public String getname() {
        return name;
    }

    public Category getcategory() {
        return category;
    }

    public List<Ingredient> getingredients() {
        return new ArrayList<>(ingredients);
    }

    public String getmethod() {
        return method;
    }

    public String getgarnish() {
        return garnish;
    }

    public String gethistory() {
        return history;
    }

    public String getnote() {
        return note;
    }

    public int getsimoxrate() {
        return simoxrate;
    }

    public String getlink() {
        return link;
    }

    public boolean containsIngredient(String iname) {
        for (int i = 0; i < ingredients.size(); i++) {

            if (ingredients.get(i).getname().toLowerCase().contains(iname.toLowerCase())) {
                return true;
            }

            /*
            if (ingredients.get(i).getname().equals(iname)) {
                return true;
            }
            */
        }
        return false;
    }
    
    @Override
    public int compareTo(Cocktail o) {
        Cocktail other = (Cocktail) o;
        return other.getsimoxrate() - this.getsimoxrate();
    }
}