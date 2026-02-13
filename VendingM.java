import java.util.Scanner;

public class VendingM {
    private String name;
    private int price; 
    private int stock;

    public VendingM(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getStock() { return stock; }

    public boolean purchase(int qty) {
        if (qty <= 0 || qty > stock) return false;
        stock -= qty;
        return true;
    }

    public static void main(String[] args) {
        
        VendingM[] items = new VendingM[] {
            new VendingM("Potato Chips", 150, 10),
            new VendingM("Chocolate Bar", 125, 8),
            new VendingM("Granola Bar", 100, 5),
            new VendingM("Soda Can", 175, 12),
            new VendingM("Cookie Pack", 130, 6)
        };

        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Friendly Vending Machine!");
        System.out.println("Here you can choose from a large variety of snacks.");
        System.out.println("Usage instructions: Enter the item number to select a snack, then the quantity. Enter 0 to exit.");
        System.out.println();

    
        }
    }

