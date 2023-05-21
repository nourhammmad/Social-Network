package com.example.testingg;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AccountTest {

    @Test
    public void addNotificationTest() {
        Account acc = new Account("Mark", "123456789", "student");
        int before = acc.notifications.size();
        acc.addNotification("Friend", "You got a new friend ya motawa7ed");
        int after = acc.notifications.size();
        assertEquals(1, after - before);
    }

    @Test
    public void createTestAccount() {
        assertThrows(IllegalArgumentException.class, () -> {
            Account acc = new Account("Mina", "123", "student");
        });
    }
}