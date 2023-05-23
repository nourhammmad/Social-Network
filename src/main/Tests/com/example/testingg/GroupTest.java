package com.example.testingg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {
    private Account moderator = new Account("Modeer","123456789","Team Leader");
    private Account member = new Account("3abdo", "king12345678", "Employee");
    private Group group = new Group(moderator, false, "El Qorob");
//    @BeforeAll
//    void preps(){
//        // ensure member has successfully joined the group
//        // group should have only 1 member throughout the testing phase
//        member.joinGroup(group);
//
//    }

    @Test
    void group_constructor_invalidrole_test(){
        // Creating a test group
        Account test = new Account("king", "123456789", "Employee");

        // A group can only be created by an account holding the team leader role
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Group(test,false, "test group");
        }, "IllegalArgumentException was expected");

        Assertions.assertEquals("Only Team Leader can create a group", thrown.getMessage());
    }

    @Test
    void group_constructor_alreadyexists_test(){
        // Creating a test group
        Account test = new Account("king", "123456789", "Employee");

        // There can never be more than one group holding the same name
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Group(test,false, "El Qorob");
        }, "IllegalArgumentException was expected");

        Assertions.assertEquals("Group name already taken", thrown.getMessage());
    }
    @Test
    void joinGroup_Test() {
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
    void successful_addPost_test() {
        // check post added with right access
        String message = assertDoesNotThrow(() -> {
            Post post = new Post("this is a test post", member);
            return post.content;
        });
        assertEquals("this is a test post", message);
    }

    @Test
    void failed_addPost_test() {
        Account foreigner = Account.FetchAccountByUsername("NourHammad");
        Post post = new Post("this is a test post", foreigner);

        // check post added with right access
        IllegalAccessException thrown = Assertions.assertThrows(IllegalAccessException.class, () -> {
            group.addPost(foreigner, post);
        }, "IllegalArgumentException was expected");
    }


    @Test
    void removeUser_bymoderator_test() {
        Account foreigner = Account.FetchAccountByUsername("NourHammad");
        foreigner.joinGroup(group);
        assertTrue(group.removeUser(moderator, foreigner));
    }

    @Test
    void removeUser_bymember_test() {
        Account foreigner = Account.FetchAccountByUsername("NourHammad");
        foreigner.joinGroup(group);
        // Members can't remove other members
        assertFalse(group.removeUser(member, foreigner));
    }

    @Test
    void getPosts() {
        assertNotNull(group.getPosts(moderator));
    }

    @Test
    void getMembers() {
        assertNotNull(group.getMembers(moderator));
    }

    @Test
    void fetchGroupByName() {
        assertNotNull(Group.FetchGroupByName("El Qorob"));
        // Both groups have the same moderator
        assertSame(this.moderator ,Group.FetchGroupByName("El Qorob").moderator);
    }

    @Test
    void isPresent_True_test() {
        assertTrue(Group.isPresent("El Qorob"));
    }

    void isPresent_False_test() {
        assertTrue(Group.isPresent("ONC-ASU"));
    }
}