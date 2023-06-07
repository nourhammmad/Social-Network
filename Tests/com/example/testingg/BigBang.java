package com.example.testingg;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class BigBang {

    @Test
    public void Scenario2() {
        System.out.println("Starting scenario 2");
        Account leader = new Account("Leader", "123456789", "Team Leader");
        Account employee = new Account("Employee", "123456789", "Employee");
        Account leaderLoggedIn = Account.LogIn("Leader", "123456789");
        Account employeeLoggedIn = Account.LogIn("Employee", "123456789");
        Group grp = new Group(leaderLoggedIn, false, "1");
        leaderLoggedIn.addFollower(employeeLoggedIn);
        Post post = leaderLoggedIn.addPost("Hello there");
        int notiID = post.addLike(employeeLoggedIn);
        assertTrue(leaderLoggedIn.posts.get(0).likers.contains(employeeLoggedIn));
        assertTrue(leaderLoggedIn.checkNotification(notiID));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Group(employeeLoggedIn, false, "test group");
        }, "IllegalArgumentException was expected");
        employeeLoggedIn.joinGroup(grp);
        System.out.println("User joined group");
        assertFalse(grp.removeUser(employeeLoggedIn, leaderLoggedIn));
        grp.removeUser(leaderLoggedIn, employeeLoggedIn);
        Assertions.assertThrows(IllegalAccessException.class, () -> {
            grp.addPost(employeeLoggedIn, new Post("Hacked", employeeLoggedIn));
        }, "IllegalAccessException was expected");
    }
}
