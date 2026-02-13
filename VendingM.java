import java.util.Scanner;

public class VendingM {
    private String name;
    private double price; 
    private int stock;

    public VendingM(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public boolean purchase(int qty) {
        if (qty <= 0 || qty > stock) return false;
        stock -= qty;
        return true;
    }

    public static void main(String[] args) {
        
        VendingM[] items = new VendingM[] {
            new VendingM("Potato Chips", 1.50, 10),
            new VendingM("Chocolate Bar", 1.25, 8),
            new VendingM("Granola Bar", 1.00, 5),
            new VendingM("Soda Can", 1.75, 12),
            new VendingM("Cookie Pack", 1.30, 6)
        };

        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Friendly Vending Machine!");
        System.out.println("Here you can choose from a large variety of snacks.");
        System.out.println("Usage instructions: Enter the item number to select a snack, then the quantity. Enter 0 to exit.");
        System.out.println();

        for (int i = 0; i < items.length; i++) {
            System.out.println(String.format("%s: %s -- $%.2f", i + 1, items[i].getName(), items[i].getPrice()));
        }

        System.out.print("Enter item number: ");
        int choice = in.nextInt();

        if (choice >= 1 && choice <= items.length) {
            VendingM selectedItem = items[choice - 1];

            int inserted = 0;

            while (inserted < selectedItem.getPrice()) {
                System.out.print("Insert $1 bill (enter 1): ");
                int bill = in.nextInt();

                if (bill == 1) {
                    inserted++;
                } else {
                    System.out.println("Only $1 bills are accepted.");
                }
            }

            System.out.println("Purchase complete!");
        } else {
            System.out.println("Invalid selection.");
        }
    }
}