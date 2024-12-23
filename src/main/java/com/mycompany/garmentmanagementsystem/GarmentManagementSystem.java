package com.mycompany.garmentmanagementsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GarmentManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        inventory.addGarment(new Garment("G001", "T-Shirt", "Cotton T-Shirt", "L", "Red", 1000.0, 20));
        inventory.addGarment(new Garment("G002", "Jeans", "Blue Denim Jeans", "M", "Blue", 750.0, 12));

        while (true) {
            System.out.println("1. Add Garment");
            System.out.println("2. Update Garment Stock");
            System.out.println("3. View All Garments");
            System.out.println("4. Place Order");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Garment Details:");
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Size: ");
                    String size = scanner.nextLine();
                    System.out.print("Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Stock Quantity: ");
                    int stockQuantity = scanner.nextInt();
                    scanner.nextLine();
                    Garment garment = new Garment(id, name, description, size, color, price, stockQuantity);
                    inventory.addGarment(garment);
                    System.out.println("Garment added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Garment ID to update stock: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Enter quantity to add: ");
                    int quantityToAdd = scanner.nextInt();
                    inventory.updateGarmentStock(updateId, quantityToAdd);
                    break;

                case 3:
                    inventory.displayGarments();
                    break;

                case 4:
                    System.out.print("Enter Order ID: ");
                    String orderId = scanner.nextLine();
                    Order order = new Order(orderId, new Date());
                    System.out.print("Enter number of garments to add to order: ");
                    int garmentCount = scanner.nextInt();
                    for (int i = 0; i < garmentCount; i++) {
                        System.out.print("Enter Garment ID: ");
                        String garmentId = scanner.next();
                        for (Garment g : inventory.garments) {
                            if (g.id.equals(garmentId)) {
                                order.addGarment(g);
                                break;
                            }
                        }
                    }
                    System.out.println("Total Order Amount: " + order.calculateTotalAmount());
                    order.printInvoice();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static class Garment {
        String id;
        String name;
        String description;
        String size;
        String color;
        double price;
        int stockQuantity;

        public Garment(String id, String name, String description, String size, String color, double price, int stockQuantity) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.size = size;
            this.color = color;
            this.price = price;
            this.stockQuantity = stockQuantity;
        }

        void updateStock(int quantity) {
            this.stockQuantity += quantity;
        }
    }

    static class Inventory {
        List<Garment> garments = new ArrayList<>();

        void addGarment(Garment garment) {
            garments.add(garment);
        }

        void updateGarmentStock(String id, int quantity) {
            for (Garment garment : garments) {
                if (garment.id.equals(id)) {
                    garment.updateStock(quantity);
                    System.out.println("Stock updated for garment ID: " + id);
                    return;
                }
            }
            System.out.println("Garment not found.");
        }

        void displayGarments() {
            if (garments.isEmpty()) {
                System.out.println("No garments in inventory.");
            } else {
                for (Garment garment : garments) {
                    System.out.println("ID: " + garment.id + ", Name: " + garment.name + ", Price: " + garment.price + ", Stock: " + garment.stockQuantity);
                }
            }
        }
    }

    static class Fabric {
        String id;
        String type;
        String color;
        double pricePerMeter;

        public Fabric(String id, String type, String color, double pricePerMeter) {
            this.id = id;
            this.type = type;
            this.color = color;
            this.pricePerMeter = pricePerMeter;
        }

        double calculateCost(double meters) {
            return meters * pricePerMeter;
        }
    }

    static class Supplier {
        String id;
        String name;
        String contactInfo;
        List<Fabric> suppliedFabrics;

        public Supplier(String id, String name, String contactInfo) {
            this.id = id;
            this.name = name;
            this.contactInfo = contactInfo;
            this.suppliedFabrics = new ArrayList<>();
        }

        void addFabric(Fabric fabric) {
            suppliedFabrics.add(fabric);
        }

        List<Fabric> getSuppliedFabrics() {
            return suppliedFabrics;
        }
    }

    static class Order {
        String orderId;
        Date orderDate;
        List<Garment> garments;
        double totalAmount;

        public Order(String orderId, Date orderDate) {
            this.orderId = orderId;
            this.orderDate = orderDate;
            this.garments = new ArrayList<>();
            this.totalAmount = 0;
        }

        void addGarment(Garment garment) {
            garments.add(garment);
        }

        double calculateTotalAmount() {
            totalAmount = garments.stream().mapToDouble(g -> g.price).sum();
            return totalAmount;
        }

        void printInvoice() {
            System.out.println("Invoice for Order ID: " + orderId);
            System.out.println("Order Date: " + orderDate);
            System.out.println("-----------------------------------------------------");
            System.out.printf("%-10s %-20s %-10s %-10s%n", "ID", "Name", "Quantity", "Price");
            System.out.println("-----------------------------------------------------");
            for (Garment garment : garments) {
                System.out.printf("%-10s %-20s %-10d %-10.2f%n", garment.id, garment.name, 1, garment.price);
            }
            System.out.println("-----------------------------------------------------");
            System.out.printf("Total Amount: %.2f%n", calculateTotalAmount());
            System.out.println("Thank you for your order!!!");
        }
    }
}
