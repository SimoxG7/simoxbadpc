public class Category {

    private final String name;

    public Category(final String name) {
        this.name = name;
    }

    public String getname() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}