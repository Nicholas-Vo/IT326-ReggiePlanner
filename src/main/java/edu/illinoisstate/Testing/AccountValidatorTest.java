package edu.illinoisstate.Testing;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.gui.MainProgramWindow;
import edu.illinoisstate.utils.AccountValidator;
import edu.illinoisstate.utils.WindowTracker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static edu.illinoisstate.database.DatabaseHandler.deleteAccount;
import static edu.illinoisstate.database.DatabaseHandler.saveAccount;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountValidatorTest {

    private AccountValidator accountInfo;

    AccountValidatorTest() {

    }


    //private Security pass;
    @BeforeEach
    void setUp() {

        accountInfo = new AccountValidator();
        accountInfo.check("dking16@ilstu.edu", "dking16", "Test1234");


    }

    @AfterEach
    void tearDown() {
        accountInfo = null;
    }

    private DatabaseHandler emaildb;

    @Test
    void createAccountTest() {
        //checks email, username, and password
        String email1 = "dking16@ilstu.edu";

        assertFalse(accountInfo.check("dking16ilstu.edu", "dking16", "Test1234"));
        assertFalse(accountInfo.check("dking16@ilstu.edu", "dki", "Test1234"));
        assertFalse(accountInfo.check("dking16ilstu.edu", "dking16", "test1234"));

        assertFalse(emaildb.emailExistsInSystem(email1));

    }


    @Test
    void loginTest() {
        assertFalse(accountInfo.check("", "dking16", "Test1234"));
        assertFalse(accountInfo.check("dking16@ilstu.edu", "dking16654322667890757642456", "Test1234"));
        assertFalse(accountInfo.check("dking16ilstu.edu", "dking16", "Test"));

    }


    //    private UserHomePage logout;
//    private UserAccount userLogout;
//    private User userL;
    private WindowTracker window;
    private MainProgramWindow uTracker;


    @Test
    void logoutTest() {

//        boolean windowLog = true;
//
//        if(uTracker == null){
//            return;
//
//        }
    }


    private UserAccount user;
    private Database deleteUser;
    private DatabaseHandler deleteAccount;


    @Test
    void deleteAccountTest() {
        String username = "dking16";
        String password = "Test1234";
        UUID uuid = UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
        String email = "dking16@ilstu.edu";
        user = new UserAccount(uuid, email, username, password);

        saveAccount(user);
        assertNotNull(user);

        deleteAccount(user);

        //loginTest();
        // assertNull(user);
    }


}