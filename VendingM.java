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
            System.out.println(String.format("%d: %s -- $%.2f (stock: %d)", i + 1, items[i].getName(), items[i].getPrice(), items[i].getStock()));
        }

        System.out.print("Enter item number (or 0 to quit): ");
        int choice = in.nextInt();

        if (choice == 0) {
            System.out.println("No problem — come back anytime! Have a great day!");
            in.close();
            return;
        }

        if (choice >= 1 && choice <= items.length) {
            VendingM selectedItem = items[choice - 1];

            System.out.print("Enter quantity: ");
            int qty = in.nextInt();

            if (qty <= 0) {
                System.out.println("Quantity must be at least 1. Transaction canceled.");
                in.close();
                return;
            }

            if (qty > selectedItem.getStock()) {
                System.out.println("Sorry, that quantity is not available. Transaction canceled.");
                in.close();
                return;
            }

            double total = selectedItem.getPrice() * qty;
            int neededBills = (int) Math.ceil(total); 
            int inserted = 0;

            System.out.printf("Your total is $%.2f. Please insert %d $1 bill(s).\n", total, neededBills);

            while (inserted < neededBills) {
                System.out.print("Insert $1 bill (enter 1) or enter 0 to cancel: ");
                int bill = in.nextInt();

                if (bill == 1) {
                    inserted++;
                    System.out.printf("Inserted: %d / %d\n", inserted, neededBills);
                } else if (bill == 0) {
                    System.out.printf("Transaction canceled. Refunding $%.2f. Come again!\n", (double) inserted);
                    in.close();
                    return;
                } else {
                    System.out.println("Only $1 bills are accepted. Try again or enter 0 to cancel.");
                }
            }

            double insertedAmount = inserted; 
            System.out.printf("You inserted $%.2f. Total is $%.2f.\n", insertedAmount, total);
            System.out.print("Confirm purchase? (Y/N): ");
            String confirm = in.next();

            if (confirm.equalsIgnoreCase("N")) {
                System.out.printf("Transaction canceled. Refunding $%.2f. Have a nice day!\n", insertedAmount);
                in.close();
                return;
            }

            boolean ok = selectedItem.purchase(qty);
            if (!ok) {
                System.out.println("Purchase failed (unexpected). Refunding full amount.");
                System.out.printf("Refunded $%.2f.\n", insertedAmount);
                in.close();
                return;
            }

            System.out.println();
            System.out.printf("Purchase complete - you got %d x %s!\n", qty, selectedItem.getName());
            if (insertedAmount > total) {
                System.out.printf("Your change is $%.2f\n", insertedAmount - total);
            }
            System.out.printf("Nice choice — %s is a crowd favorite. Enjoy every bite and have an awesome day!\n", selectedItem.getName());
            System.out.println("Thanks for stopping by our vending machine. Come again soon!");

        } else {
            System.out.println("Invalid selection.");
        }

        in.close();
    }
}