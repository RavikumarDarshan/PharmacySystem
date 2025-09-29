package pharmacy;

import java.util.ArrayList;
import java.util.List;

public class Prescription {
    private int id;
    private Customer customer;
    private List<PrescriptionItem> items;

    public Prescription(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    public void addItem(PrescriptionItem item) {
        items.add(item);
    }

    public List<PrescriptionItem> getItems() { return items; }
    public Customer getCustomer() { return customer; }

    @Override
    public String toString() {
        return "Prescription#" + id + " for " + customer.getName();
    }
}