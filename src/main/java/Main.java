import model.User;
import view.Login;
import view.Rent;
import view.Title;

public class Main {
    public static void main(String[] args) {
        User user = new User(1, "ricardo", "ricardo@example.com", "123");

//                Login login = new Login();
//        Title title = new Title(user);
        Rent rent = new Rent(user);
    }
}
