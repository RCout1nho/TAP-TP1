import model.User;
import model.enumerators.UserTypeEnum;
import view.*;

public class Main {
    public static void main(String[] args) {
        User user = new User(1, "ricardo", "ricardo@example.com", UserTypeEnum.ADMIN, "123");

//                Login login = new Login();
//        Title title = new Title(user);
//        Rent rent = new Rent(user);
        HomeView home = new HomeView(user);
//        UserView viewUserView = new UserView(user);
    }
}
