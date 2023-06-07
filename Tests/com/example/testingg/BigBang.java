package com.example.testingg;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BigBang {
    @Test
    public void Scenario1() throws IllegalAccessException {
        System.out.println("Starting scenario 1");
        Account leader = new Account("Leader1", "123456789", "Team Leader");
        Account employee = new Account("Employee1", "123456789", "Employee");
        Account leaderLoggedIn = Account.LogIn("Leader1", "123456789");
        Account employeeLoggedIn = Account.LogIn("Employee1", "123456789");
        Group grp = new Group(leaderLoggedIn, false, "Private Group");

        // Check Follow Notification
        int beforeFollow = employeeLoggedIn.notifications.size();
        leaderLoggedIn.addFollower(employeeLoggedIn);
        int afterFollow = employeeLoggedIn.notifications.size();
        assertEquals(1,afterFollow-beforeFollow);

        // Check Add Post Notification
        int beforePost = employeeLoggedIn.notifications.size();
        Post post = leaderLoggedIn.addPost("Hello there");
        int afterPost = employeeLoggedIn.notifications.size();
        assertEquals(1,afterPost-beforePost);

        // Check Private Group Access
        Group.FetchGroupByName("Private Group");
        assertNull(grp.getPosts(employeeLoggedIn));

        // Check Joining Group Notification
        int beforeJoin = leaderLoggedIn.notifications.size();
        employeeLoggedIn.joinGroup(grp);
        int afterJoin = leaderLoggedIn.notifications.size();
        assertEquals(1,afterJoin-beforeJoin);

        // Check Private Group Access
        assertNotNull(grp.getPosts(employeeLoggedIn));
        assertEquals("Private Group", employeeLoggedIn.groups.get(0).gName);

        // Check Add Group Post Notification
        int beforeGroupPost = employeeLoggedIn.notifications.size();
        Post Gpost = new Post("First Group Post",leaderLoggedIn);
        try {
            grp.addPost(leaderLoggedIn,Gpost);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        int afterGroupPost = employeeLoggedIn.notifications.size();
        assertEquals(1,afterGroupPost-beforeGroupPost);

        // Check Like And Notification
        int notiID = Gpost.addLike(employeeLoggedIn);
        assertTrue(grp.posts.get(0).likers.contains(employeeLoggedIn));
        assertTrue(leaderLoggedIn.checkNotification(notiID));

        // Check Add Group Post Notification By Member
        int beforeGroupPost2 = leaderLoggedIn.notifications.size();
        Post Gpost2 = new Post("Second Group Post",employeeLoggedIn);
        try {
            grp.addPost(employeeLoggedIn,Gpost2);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        int afterGroupPost2 = leaderLoggedIn.notifications.size();
        assertEquals(1,afterGroupPost2-beforeGroupPost2);

        // Check Remove User
        int beforeRemove = employeeLoggedIn.groups.size();
        grp.removeUser(leaderLoggedIn, employeeLoggedIn);
        ArrayList <Account> members = grp.getMembers(leaderLoggedIn);
        boolean flag = false;
        for(int i = 0; i< members.size();i++){
            if (members.get(i).equals(employeeLoggedIn)) {
                flag = true;
                break;
            }
        }
        assertFalse(flag);

        // Check Number of Joined Groups
        int afterRemove = employeeLoggedIn.groups.size();
        assertEquals(1,beforeRemove-afterRemove);
    }

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
