package com.example.testingg;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GroupTest {
    private Account moderator;
    private Account member;
    private Group group;
    private Account test;

    /** Clean-up Testing objects **/
    @After
    public void deleteObjects(){
        Account.accounts.remove(moderator);
        Account.accounts.remove(member);
        Account.accounts.remove(test);
        Group.ListofGroups.remove(group);
    }

    @Before
    public void createObjects(){
        moderator = new Account("Modeer","123456789","Team Leader");
        member = new Account("3abdo", "king12345678", "Employee");
        group = new Group(moderator, false, "El Qorob");
        test = new Account("king", "123456789", "Employee");
        member.joinGroup(group);
    }

    @Test
    public void group_constructor_invalidrole_test(){
        // A group can only be created by an account holding the team leader role
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Group(test,false, "test group");
        }, "IllegalArgumentException was expected");

        Assertions.assertEquals("Only Team Leader can create a group", thrown.getMessage());
        Group.ListofGroups.remove(Group.FetchGroupByName("test group"));
    }

    @Test
    public void group_constructor_alreadyexists_test(){
        assertThrows(IllegalArgumentException.class, ()->{new Group(moderator,false, "El Qorob");});
    }
    @Test
    public void joinGroup_Test() {
        group.joinGroup(member);
        // Check a correct notification is sent to the moderator
        Notification not = moderator.notifications.get(0);
        assertEquals("Join Request", not.category);
        assertEquals("Join group request sent by "+member.username, not.content);

        // Check member is correctly added to members list of group
        assertNotNull(group.getMembers(moderator));
        assertEquals(member, group.getMembers(moderator).get(0));
    }

    @Test
    public void successful_addPost_test() {
        // check post added with right access
        String message = assertDoesNotThrow(() -> {
            Post post = new Post("this is a test post", member);
            return post.content;
        });
        assertEquals("this is a test post", message);
    }

    @Test
    public void failed_addPost_test() {
        Account foreigner = Account.FetchAccountByUsername("NourHammad");
        Post post = new Post("this is a test post", foreigner);

        // check post added with right access
        IllegalAccessException thrown = Assertions.assertThrows(IllegalAccessException.class, () -> {
            group.addPost(foreigner, post);
        }, "IllegalArgumentException was expected");
    }

    @Test
    public void removeUser_bymoderator_test() {
        test.joinGroup(group);
        assertTrue(group.removeUser(moderator, test));
    }

    @Test
    public void removeUser_bymember_test() {
        test.joinGroup(group);
        // Members can't remove other members
        assertFalse(group.removeUser(member, test));
    }

    @Test
    public void getPosts() {
        assertNotNull(group.getPosts(moderator));
    }

    @Test
    public void getMembers() {
        assertNotNull(group.getMembers(moderator));
    }

    @Test
    public void fetchGroupByName() {
        assertNotNull(Group.FetchGroupByName("El Qorob"));
        // Both groups have the same moderator
        assertSame(this.moderator ,Group.FetchGroupByName("El Qorob").moderator);
    }

    @Test
    public void isPresent_True_test() {
        assertTrue(Group.isPresent("El Qorob"));
    }

    public void isPresent_False_test() {
        assertTrue(Group.isPresent("ONC-ASU"));
    }
}