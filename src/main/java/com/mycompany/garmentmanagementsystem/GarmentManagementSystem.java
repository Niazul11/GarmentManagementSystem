
import java.util.Date;
import java.util.List;

class Garment {

    String id;
    String name;

    String description;
    String size;
    String color;
    double price;
    int stockQuantity;

    void updateStock(int quantity) {

    }

    double calculateDiscountPrice(double discountPercentage) {
        return 0;
    }
}

class Fabric {

    String id;
    String type;
    String color;
    double pricePerMeter;

    double calculateCost(double meters) {
        return 0;
    }

}

class Supplier {

    String id;
    String name;
    String contactInfo;
    List<Fabric> suppliedFabrics;

    void addFabric(Fabric fabric) {

    }

    List<Fabric> getSuppliedFabrics() {
        return null;
    }
}

class Order {

    String orderId;
    Date orderDate;
    List<Garment> garments;
    double totalAmount;

    void addGarment(Garment garment) {
    }

    double calculateTotalAmount() {
        return 0;
    }

    void printOrderDetails() {
    }
}

class Customer {

    String customerId;
    String name;
    String email;
    String phone;

    void placeOrder(Order order) {
    }

    List<Order> viewOrders() {
        return null;
    }

}

class Inventory {

    List<Garment> garments;

    void addGarment(Garment garment) {
    }

    void removeGarment(String id) {
    }

    Garment findGarment(String id) {
        return null;
    }

}