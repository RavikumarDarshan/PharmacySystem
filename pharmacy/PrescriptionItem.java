package pharmacy;

public class PrescriptionItem {
    private Medicine medicine;
    private int quantity;

    public PrescriptionItem(Medicine medicine, int quantity) {
        this.medicine = medicine;
        this.quantity = quantity;
    }

    public Medicine getMedicine() { return medicine; }
    public int getQuantity() { return quantity; }
}