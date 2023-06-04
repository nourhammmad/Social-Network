package com.example.testingg;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    @Test
    void postByID() {
        Account ac2 = new Account("mark", "123456789", "Team Leader");
        Post post = new Post("New Post", ac2);
        Post gotPost = Post.PostByID(post.id);
        assertEquals(post.id, gotPost.id);
        Account.accounts.remove(ac2);
    }
    @Test
    void postByIDNull() {
        Post p = Post.PostByID(3312);
        assertNull(p);
    }

    @Test
    void getGlobalID() {
        Account ac2 = new Account("mark", "123456789", "Team Leader");
        Post post = new Post("New Post", ac2);
        assertEquals(post.getId(), Post.getGlobalID());
        Account.accounts.remove(ac2);
    }


    @Test
    void addLike() {
        Account ac2 = new Account("mark", "123456789", "Team Leader");
        Post post = new Post("New Post", ac2);
        int beforeLikes = post.getLikes();
        post.addLike(ac2);
        int afterLikes = post.getLikes();
        assertEquals(1, afterLikes - beforeLikes);
        Account.accounts.remove(ac2);
    }

    @Test
    void getPost() {
        Account ac2 = new Account("mark", "123456789", "Team Leader");
        Post post = new Post("New Post", ac2);
        assertEquals("New Post", post.getPost());
        Account.accounts.remove(ac2);
    }
}