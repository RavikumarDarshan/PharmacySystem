package pharmacy;

public class SaleDetail {
    private Medicine medicine;
    private int quantity;

    public SaleDetail(Medicine medicine, int quantity) {
        this.medicine = medicine;
        this.quantity = quantity;
    }

    public Medicine getMedicine() { return medicine; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return medicine.getName() + " x " + quantity;
    }
}