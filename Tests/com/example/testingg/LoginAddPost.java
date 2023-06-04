package com.example.testingg;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginAddPost {

    @Test
    public void loginAddPost(){
        Account account = new Account("mark", "123456789", "Employee");
        Account loggedIn = Account.LogIn("mark", "123456789");
        Post pt = loggedIn.addPost("New Posttt");
        int id = Post.getGlobalID();
        assertEquals(id, pt.getId());
    }
}
