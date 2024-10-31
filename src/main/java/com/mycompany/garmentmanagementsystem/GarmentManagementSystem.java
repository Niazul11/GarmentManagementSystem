package com.mycompany.garmentmanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GarmentManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        inventory.addGarment(new Garment("G001", "T-Shirt", "Cotton T-Shirt", "L", "Red", 20.0, 10));
        inventory.addGarment(new Garment("G002", "Jeans", "Blue Denim Jeans", "M", "Blue", 40.0, 5));

        while (true) {
            System.out.println("1. Add Garment");
            System.out.println("2. Update Garment Stock");
            System.out.println("3. View All Garments");
            System.out.println("4. Exit");
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
}
