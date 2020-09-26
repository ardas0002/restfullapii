package pl.ardas.restfullapi.model.request;

public class UserDetailsRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isValid() {
    	return !(this.getFirstName().isEmpty() || this.getLastName().isEmpty() || this.getEmail().isEmpty() || this.getPassword().isEmpty());
    }
    
    @Override
    public String toString() {
        return "UserDetailsRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    
    public static void main(String[] args) {
    	
    	UserDetailsRequest user = new UserDetailsRequest();
    	user.setFirstName("");
    	user.setLastName("Korniszon");
    	user.setEmail("macko@macko");
    	user.setPassword("IOJIJIUJ");
    	
    	System.out.println(user.isValid());
    }
}
