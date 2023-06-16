import java.util.ArrayList;
import java.util.List;

public class Main {
    private static UserManager userManager;
    private static ProductManager productManager;
    private static User loggedInUser;

    public static void main(String[] args) {
        userManager = new UserManager();
        List<User> users = DatabaseManager.loadUsers();
        userManager.setUsers(users);

        productManager = new ProductManager();
        List<Product> products = DatabaseManager.loadProducts();
        productManager.setProducts(products);

        boolean running = true;

        while (running) {
            String input = InputManager.getInput("Enter 1 to register, 2 to login, 3 to find product, 4 to add product, 5 to view products, 6 to use product, 7 to search product, 8 to sort products by expiry date,0 to exit");
            if (input.isEmpty()) {
                System.out.println("You must enter a number.");
                continue;
            }
            int choice = Integer.parseInt(input);

            if (choice == 1) {
                String username = InputManager.getInput("Enter username");
                String password = InputManager.getInput("Enter password");

                userManager.registerUser(username, password);
                DatabaseManager.saveUsers(userManager.getUsers());

            } else if (choice == 2) {
                String username = InputManager.getInput("Enter username");
                String password = InputManager.getInput("Enter password");

                loggedInUser = userManager.login(username, password);

                if (loggedInUser != null) {
                    OutputManager.displayMessage("Login successful. Welcome, " + loggedInUser.getUsername() + "!");
                } else {
                    OutputManager.displayMessage("Invalid username or password. Please try again.");
                }
            } else if (choice == 3) {
            	if (loggedInUser != null) { 
                String barcode = InputManager.getInput("Enter barcode");
                Product foundProduct = productManager.findProductByBarcode(barcode);

                if (foundProduct != null) {
                    OutputManager.displayMessage("Product found:");
                    OutputManager.displayMessage("Barcode: " + foundProduct.getBarcode());
                    OutputManager.displayMessage("Product Name: " + foundProduct.getProductName());
                    OutputManager.displayMessage("Expiry Date: " + foundProduct.getExpiryDate());
                    OutputManager.displayMessage("Category: " + foundProduct.getCategory());
                } else {
                    OutputManager.displayMessage("Product not found.");
                }
            	}
            	else {System.out.println("please login");}
            } else if (choice == 4) {
                if (loggedInUser != null) {
                    String barcode = InputManager.getInput("Enter barcode");
                    String productName = InputManager.getInput("Enter product name");
                    String expiryDate = InputManager.getInput("Enter expiry date");
                    String category = InputManager.getInput("Enter category");
   
                    productManager.addProduct(barcode, productName, expiryDate, category, loggedInUser.getUsername());
                    DatabaseManager.saveProducts(productManager.getProducts());
                } else {
                    OutputManager.displayMessage("Please login before adding a product.");
                }
            } else if (choice == 5) {
                if (loggedInUser != null) {
                    List<Product> userProducts = new ArrayList<>();
                    for (Product product : productManager.getProducts()) {
                        if (!product.isUsed() && product.getUserId().equals(loggedInUser.getUsername())) {
                            userProducts.add(product);
                        }
                    }

                    if (!userProducts.isEmpty()) {
                        OutputManager.displayMessage("Products for user " + loggedInUser.getUsername() + ":");
                        for (Product product : userProducts) {
                            OutputManager.displayMessage("Barcode: " + product.getBarcode());
                            OutputManager.displayMessage("Product Name: " + product.getProductName());
                            OutputManager.displayMessage("Expiry Date: " + product.getExpiryDate());
                            OutputManager.displayMessage("Category: " + product.getCategory());
                            OutputManager.displayMessage("------");
                        }
                    } else {
                        OutputManager.displayMessage("No products found for user " + loggedInUser.getUsername());
                    }
                } else {
                    OutputManager.displayMessage("Please login before viewing products.");
                }
            } else if (choice == 6) {
                if (loggedInUser != null) {
                    String barcode = InputManager.getInput("Enter barcode");
                    productManager.useProduct(barcode);
                    DatabaseManager.saveProducts(productManager.getProducts());
                } else {
                    OutputManager.displayMessage("Please login before using a product.");
                }
            } else if (choice == 7) {
            	if (loggedInUser != null) {
                String query = InputManager.getInput("Enter product name or category");
                List<Product> foundProducts = productManager.searchProductByNameOrCategory(query);
                if (foundProducts.isEmpty()) {
                    OutputManager.displayMessage("No unused product found.");
                } else {
                    for (Product product : foundProducts) {
                        OutputManager.displayProduct(product);
                    }
                }
            	}
            	else { OutputManager.displayMessage("Please login");
}
            } else if (choice == 8) {
            	if (loggedInUser != null) {

                List<Product> sortedProducts = productManager.sortProductByExpiryDate();
                if (sortedProducts.isEmpty()) {
                    OutputManager.displayMessage("No unused products found.");
                } else {
                    for (Product product : sortedProducts) {
                        OutputManager.displayProduct(product);
                    }
                }
            	}
            	else { OutputManager.displayMessage("Please login");}

            }else if (choice == 0) {
                running = false;
                OutputManager.displayMessage("Program exited.");
            } else {
                OutputManager.displayMessage("Invalid choice. Please try again.");
            }
        }
    }


}



