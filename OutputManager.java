public class OutputManager {
    public static void displayMessage(String message) {
        System.out.println(message);
    }
    public static void displayProduct(Product product) {
        System.out.println("Product Name: " + product.getProductName());
        System.out.println("Barcode: " + product.getBarcode());
        System.out.println("Expiry Date: " + product.getExpiryDate());
        System.out.println("Category: " + product.getCategory());
        System.out.println("------");
    }
}