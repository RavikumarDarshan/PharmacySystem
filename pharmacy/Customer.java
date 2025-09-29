package pharmacy;

public class Customer {
    private int id;
    private String name;
    private String contact;

    public Customer(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return id + " - " + name + " (" + contact + ")";
    }
}