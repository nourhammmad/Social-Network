package com.example.testingg;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class AccountTest {
    private Account account;

    @Before
    public void instantiateObjects() {
        account = new Account("Ahmed", "Wael12345678", "Employee");
    }

    @After
    public void deleteObjects() {
        Account.accounts.remove(account);
        account = null;
    }

    @Test
    public void is_Successfully_added() {
        // Check that the new account is added successfully
        assertTrue(Account.isPresent("Ahmed"));
    }

    @Test
    // Testing a strong password check
    public void is_Weak_Password() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Account("Ahmed", "Wael", "Employee");
        });
    }

    @Test
    public void inCorrect_Role_Test() {
        // Only valid roles: Employee, Team Leader
        assertThrows(IllegalArgumentException.class, () -> {
            new Account("Ahmed", "waelibrahimmohamed", "Tester");
        });
    }

    @Test
    public void add_Notification_Test() {
        String test_content = "You got a new friend";
        int before = account.notifications.size();
        System.out.println(before);
        account.addNotification("Friend", test_content);
        int after = account.notifications.size();
        System.out.println(after);
        // Check notification is sent correctly
        assertEquals(test_content, account.notifications.get(after - 1).content);
        // Check notification is sent @ correct index
        assertEquals(1, after - before);
    }

    //TODO: check is post created by id
    @Test
    public void add_Post() {
        String content = "This is a test post!";
        Post post = account.addPost(content);
        // Check that a post is create with the correct content
        assertEquals(content, post.content);
    }

    @Test
    public void joinGroup_Test() {
        // How to create a new group
    }

    //TODO: test with new account not old account
    @Test
    public void addFollower_Test() {
        // Adding only 1 friend
        Account test_friend = new Account("TestAccount", "waelwael123", "Employee");
        account.addFollower(test_friend);
        // Check if friend is added successfully
        assertEquals(test_friend.username, account.friends.get(0).username);
        test_friend = null;
    }


    @Test
    public void SuccessfullogInTest() {
        /*
            Correct username = Ahmed
            Correct password = Wael12345678
         */
        String id = "Ahmed";
        String pass = "Wael12345678";

        Account acc = Account.LogIn(id, pass);

        // Check login account returns the correct account
        assertNotNull(acc);
        assertEquals(id, acc.username);
        assertEquals(pass, acc.password);
    }

    @Test
    public void Wrong_Username_LogIn() {
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
    public void Wrong_Password_LogIn() {
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
    public void fetchAccountByUsername_Test() {
        Account acc = Account.FetchAccountByUsername("Ahmed");
        assertNotNull(acc);
        assertEquals("Ahmed", acc.username);
        assertEquals("Wael12345678", acc.password);
    }
}