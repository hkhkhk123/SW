public class ValidationManager {
	   public static boolean isUsernameValid(String username) {
	        return username.length() >= 3 && username.length() <= 15;
	    }
	   public static boolean isPasswordValid(String password) { 
	        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*\\W.*");
	    }
}
