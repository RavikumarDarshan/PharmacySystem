package pharmacy;

import java.time.LocalDate;

public class Medicine {
    private int id;
    private String name;
    private int quantity;
    private LocalDate expiryDate;
    private boolean prescriptionRequired;

    public Medicine(int id, String name, int quantity, LocalDate expiryDate, boolean prescriptionRequired) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.prescriptionRequired = prescriptionRequired;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public boolean isPrescriptionRequired() { return prescriptionRequired; }

    public void reduceStock(int qty) {
        if (qty <= quantity) {
            quantity -= qty;
        }
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public String toString() {
        return id + " - " + name + " | Qty: " + quantity + " | Exp: " + expiryDate + " | Prescription: " + prescriptionRequired;
    }
}