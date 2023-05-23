package com.example.testingg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account = new Account("Ahmed", "Wael12345678", "Employee");
    @Test
    void is_Successfully_added() {
        // Check that the new account is added successfully
        assertTrue(Account.isPresent("Ahmed"));
    }

    @Test
    // Testing a strong password check
    void is_Weak_Password(){
        // A weak password has characters < 8
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Account("Ahmed", "Wael", "Employee");
        }, "IllegalArgumentException was expected");

        Assertions.assertEquals("Password is weak", thrown.getMessage());
    }

    void inCorrect_Role_Test(){
        // A weak password has characters < 8
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Account("Ahmed", "waelibrahimmohamed", "Tester");
        }, "IllegalArgumentException was expected");

        Assertions.assertEquals("Role is not chosen", thrown.getMessage());
    }

    @Test
    void add_Notification_Test() {
        int before = account.notifications.size();
        account.addNotification("Friend", "You got a new friend");
        int after = account.notifications.size();
        // Check notification is sent correctly
        assertEquals("You got a new friend", account.notifications.get(after));
        // Check notification is sent @ correct index
        assertEquals(1, after - before);
    }

    @Test
    void add_Post() {
        String content = "This is a test post!";
        Post post = account.addPost(content);
        // Check that a post is create with the correct content
        assertEquals(content, post.content);
    }

    @Test
    void joinGroup_Test() {
        // How to create a new group
    }

    @Test
    void addFollower_Test() {
        // Adding only 1 friend
        account.addFollower(Account.FetchAccountByUsername("NourHammad"));
        // Check if friend is added successfully
        assertEquals("NourHammad",account.friends.get(0).username);
    }

    @Test
    void SuccessfullogInTest() {
        /*
            Correct username = Ahmed
            Correct password = Wael12345678
         */
        String id = "Ahmed";
        String pass = "Wael12345678";

        Account acc = Account.LogIn(id, pass);

        // Check login account returns the correct account
        assertNotNull(acc);
        assertEquals(id,acc.username);
        assertEquals(pass,acc.password);
    }

    @Test
    void Wrong_Username_LogIn() {
        /*
            Correct username = Ahmed
            Correct password = Wael12345678
         */
        String id = "3abdo";
        String pass = "Wael12345678";

        Account acc = Account.LogIn(id, pass);

        // Check login function does not return an account
        assertNull(acc);
    }
    @Test
    void Wrong_Password_LogIn() {
        /*
            Correct username = Ahmed
            Correct password = Wael12345678
         */
        String id = "Ahmed";
        String pass = "Wael";

        Account acc = Account.LogIn(id, pass);

        // Check login function does not return an account
        assertNull(acc);
    }

    @Test
    void fetchAccountByUsername_Test() {
        Account acc = Account.FetchAccountByUsername("Ahmed");
        assertNotNull(acc);
        assertEquals("Ahmed", acc.username);
        assertEquals("Wael12345678", acc.password);

    }
}