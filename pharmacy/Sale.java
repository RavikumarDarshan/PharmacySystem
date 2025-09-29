package pharmacy;

import java.util.ArrayList;
import java.util.List;

public class Sale {
    private int id;
    private Customer customer;
    private Pharmacist pharmacist;
    private List<SaleDetail> saleDetails;

    public Sale(int id, Customer customer, Pharmacist pharmacist) {
        this.id = id;
        this.customer = customer;
        this.pharmacist = pharmacist;
        this.saleDetails = new ArrayList<>();
    }

    public void addSaleDetail(SaleDetail detail) {
        saleDetails.add(detail);
    }

    public void printBill() {
        System.out.println("\n===== SALE BILL =====");
        System.out.println("Sale ID: " + id);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Pharmacist: " + pharmacist.getName());
        System.out.println("Items:");
        for (SaleDetail d : saleDetails) {
            System.out.println(" - " + d);
        }
        System.out.println("=====================\n");
    }
}