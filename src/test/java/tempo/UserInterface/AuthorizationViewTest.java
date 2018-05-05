package tempo.UserInterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tempo.Authorization.LoginManager;
import tempo.Authorization.RegisterManager;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizationViewTest {

    RegisterManager register;
    LoginManager login;

    @BeforeEach
    void setUp() {
    }

    @Test
    void register() throws NoSuchAlgorithmException {
        register = new RegisterManager("jubaid", "holy", "email", "jubo2", "lazy");
        register.register();
    }

    @Test
    void login() throws NoSuchAlgorithmException {
        login = new LoginManager("jubo", "lazy");
        if(login.login()){
            System.out.println("Successful!");
        } else {
            System.out.println("Ups!");
        }
    }
}