package pharmacy;

import java.time.LocalDate;
import java.util.*;

public class PharmacyApp {
    private static List<Medicine> medicines = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Prescription> prescriptions = new ArrayList<>();
    private static List<Sale> sales = new ArrayList<>();
    private static Pharmacist pharmacist = new Pharmacist(1, "John Doe");

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n==== Pharmacy Management ====");
            System.out.println("1. Add Medicine");
            System.out.println("2. Add Customer");
            System.out.println("3. Add Prescription");
            System.out.println("4. Make Sale");
            System.out.println("5. Display Medicines");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addMedicine();
                case 2 -> addCustomer();
                case 3 -> addPrescription();
                case 4 -> makeSale();
                case 5 -> displayMedicines();
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    private static void addMedicine() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
        LocalDate exp = LocalDate.parse(sc.nextLine());
        System.out.print("Prescription Required? (true/false): ");
        boolean req = sc.nextBoolean();

        medicines.add(new Medicine(id, name, qty, exp, req));
        System.out.println("Medicine added!");
    }

    private static void addCustomer() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Contact: ");
        String contact = sc.nextLine();

        customers.add(new Customer(id, name, contact));
        System.out.println("Customer added!");
    }

    private static void addPrescription() {
        System.out.print("Enter Prescription ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Customer ID: ");
        int custId = sc.nextInt();

        Customer customer = customers.stream().filter(c -> c.getId() == custId).findFirst().orElse(null);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        Prescription prescription = new Prescription(id, customer);

        char more;
        do {
            System.out.print("Enter Medicine ID: ");
            int medId = sc.nextInt();
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            Medicine med = medicines.stream().filter(m -> m.getId() == medId).findFirst().orElse(null);
            if (med == null) {
                System.out.println("Medicine not found!");
            } else {
                prescription.addItem(new PrescriptionItem(med, qty));
            }

            System.out.print("Add more? (y/n): ");
            more = sc.next().charAt(0);
        } while (more == 'y' || more == 'Y');

        prescriptions.add(prescription);
        System.out.println("Prescription added!");
    }

    private static void makeSale() {
        System.out.print("Enter Sale ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Customer ID: ");
        int custId = sc.nextInt();

        Customer customer = customers.stream().filter(c -> c.getId() == custId).findFirst().orElse(null);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        Sale sale = new Sale(id, customer, pharmacist);

        char more;
        do {
            System.out.print("Enter Medicine ID: ");
            int medId = sc.nextInt();
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            Medicine med = medicines.stream().filter(m -> m.getId() == medId).findFirst().orElse(null);
            if (med == null) {
                System.out.println("Medicine not found!");
            } else if (med.isExpired()) {
                System.out.println("Cannot sell expired medicine!");
            } else if (med.getQuantity() < qty) {
                System.out.println("Not enough stock!");
            } else if (med.isPrescriptionRequired()) {
                boolean valid = prescriptions.stream().anyMatch(p -> p.getCustomer().getId() == custId &&
                        p.getItems().stream().anyMatch(i -> i.getMedicine().getId() == medId && i.getQuantity() >= qty));
                if (!valid) {
                    System.out.println("Valid prescription required!");
                } else {
                    med.reduceStock(qty);
                    sale.addSaleDetail(new SaleDetail(med, qty));
                }
            } else {
                med.reduceStock(qty);
                sale.addSaleDetail(new SaleDetail(med, qty));
            }

            System.out.print("Add more items? (y/n): ");
            more = sc.next().charAt(0);
        } while (more == 'y' || more == 'Y');

        sales.add(sale);
        sale.printBill();
    }

    private static void displayMedicines() {
        System.out.println("\nAvailable Medicines:");
        for (Medicine m : medicines) {
            System.out.println(m);
        }
    }
}