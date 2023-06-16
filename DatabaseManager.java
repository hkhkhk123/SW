import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
class DatabaseManager {
    private static final String USERS_FILE = "users.txt";
    private static final String PRODUCTS_FILE = "products.txt";

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                String username = userInfo[0];
                String password = userInfo[1];
                User user = new User(username, password);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void saveUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("products.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String barcode = parts[0];
                    String productName = parts[1];
                    String expiryDate = parts[2];
                    String category = parts[3];
                    String userId = parts[4];
                    boolean isUsed = Boolean.parseBoolean(parts[5]);
                    products.add(new Product(barcode, productName, expiryDate, category, userId, isUsed));
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
        public static void saveProducts(List<Product> products) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUCTS_FILE))) {
                for (Product product : products) {
                    writer.write(product.getBarcode() + "," + product.getProductName() + "," + 
                                 product.getExpiryDate() + "," + product.getCategory() + "," + 
                                 product.getUserId() + "," + product.isUsed());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
