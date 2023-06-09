package com.example.testingg;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PostTest {
    private Account ac2;
    @Before
    public void init(){
        // initializing testing account before each test
        ac2 = new Account("tester", "123456789", "Team Leader");
    }
    @After
    public void end(){
        // Empty all pointers and acounts
        Account.accounts.remove(ac2);
        ac2 = null;
    }
    @Test
    public void postByID() {
        Post post = new Post("New Post", ac2);
        Post gotPost = Post.PostByID(post.id);
        assertEquals(post.id, gotPost.id);
    }
    @Test
    public void postByIDNull() {
        Post p = Post.PostByID(3312);
        assertNull(p);
    }

    @Test
    public void getGlobalID() {
        Post post = new Post("New Post", ac2);
        assertEquals(post.getId(), Post.getGlobalID());
    }


    @Test
    public void addLike_test() {
        Account tester = new Account ("Shafiqa", "1234567890", "Employee");
        Account liker = new Account ("El nafas el merta7", "1234567890", "Employee");
        Post post = new Post("This is a testing post ", tester);
        int before, after;
        before = post.getLikes();
        post.addLike(liker);
        after = post.getLikes();
        assertEquals(1, after-before);
    }

    @Test
    public void getPost() {
        Post post = new Post("New Post", ac2);
        assertEquals("New Post", post.getPost());
    }
}
