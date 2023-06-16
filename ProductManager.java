import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class ProductManager {
    private List<Product> products;

    public ProductManager() {
        products = new ArrayList<>();
    }


    public void addProduct(String barcode, String productName, String expiryDate, String category, String userId) {
        Product product = findProductByBarcode(barcode);
        if (product != null) {
            if(product.isUsed()) {
                System.out.println("Cannot register the product. The gift card is already used.");
            } else {
                System.out.println("Barcode is already used. Product already registered.");
            }
        } else {
        	product = new Product(barcode, productName, expiryDate, category, userId, false);
            products.add(product);
            System.out.println("Product registered successfully.");
        }
    }


    public Product findProductByBarcode(String barcode) {
        for (Product product : products) {
            if (product.getBarcode().equals(barcode)) {
                return product;
            }
        }
        return null;
    }

    public void useProduct(String barcode) {
        Product product = findProductByBarcode(barcode);
        if (product != null && !product.isUsed()) {
            product.setUsed();
            System.out.println("Product used successfully.");
        } else {
            System.out.println("Invalid barcode or product is already used.");
        }
    }
    public List<Product> searchProductByNameOrCategory(String query) {
        List<Product> foundProducts = products.stream()
            .filter(product -> !product.isUsed() 
                && (product.getProductName().equalsIgnoreCase(query) || product.getCategory().equalsIgnoreCase(query)))
            .collect(Collectors.toList());

        return foundProducts;
    }

    
    public List<Product> sortProductByExpiryDate() {
        List<Product> sortedProducts = products.stream()
            .filter(product -> !product.isUsed()) 
            .sorted(Comparator.comparing(Product::getExpiryDate)) 
            .collect(Collectors.toList());

        return sortedProducts;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
}