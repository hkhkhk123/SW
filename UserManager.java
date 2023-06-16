import java.util.ArrayList;
import java.util.List;
class UserManager {
    private List<User> users;

    public UserManager() {
        users = new ArrayList<>();
    }

    public void registerUser(String username, String password) {
        if (!ValidationManager.isUsernameValid(username)) {
            OutputManager.displayMessage("Username is not valid. It must be at least 3 characters and no more than 15 characters.");
            return;
        }

        if(isUsernameUsed(username)){
            OutputManager.displayMessage("Username already in use. Please choose a different username.");
            return;
        }
        if (!ValidationManager.isPasswordValid(password)) {
            OutputManager.displayMessage("Password is not valid. It must be at least 8 characters and contain at least one digit and one special character.");
            return;
        }



        User newUser = new User(username, password);
        users.add(newUser);
        OutputManager.displayMessage("Registration successful. Please log in.");
    }
    
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    private boolean isUsernameUsed(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
