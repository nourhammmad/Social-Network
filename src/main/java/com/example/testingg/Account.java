package com.example.testingg;

import java.util.ArrayList;

public class Account {
    String username, password, role;
    ArrayList<Account> following = new ArrayList<>();

    ArrayList<Post> posts = new ArrayList<>();

    ArrayList<Notification> notifications = new ArrayList<Notification>();
    ArrayList<Group> groups = new ArrayList<>();

    ArrayList<Account> followers = new ArrayList<>();

    static ArrayList<Account> accounts = new ArrayList<Account>();

    // Todo: check creation process
    public Account(String username, String password, String role) throws IllegalArgumentException {

        boolean passed = true;

        if (isPresent(username)) {
            passed = false;
            System.out.println("The username is already taken");
            throw new IllegalArgumentException("Username taken");
        }
        if (!isValid(password)) {
            passed = false;
            System.out.println("Password is too weak");
            throw new IllegalArgumentException("Password is weak");

        }
        if (!(role.equals("Employee") || role.equals("Team Leader"))) {
            passed = false;
            System.out.println("Role is not chosen");
            throw new IllegalArgumentException("Role is not chosen");

        }


        if (passed) {
            System.out.println("Adding");
            this.username = username;
            this.password = password;
            this.role = role;
            accounts.add(this);
        }
    }


    static public boolean isPresent(String username) {
        for (Account account : accounts) {
            if (account.username.equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValid(String pass) {
        if (pass.length() > 8) {
            return true;
        }
        return false;
    }

    public void addNotification(String category, String content) {
        System.out.println(this.username +category+content);
        Notification notification = new Notification(category, content);
        notifications.add(notification);
    }

    public Post addPost(String content) {
        Post post = new Post(content);
        posts.add(post);
        for (Account follower : followers) {
            follower.addNotification("Friends' post", follower.username + " added a post");
        }
        return post;
    }

    public void joinGroup(Group grp) {
        grp.joinGroup(this);
        groups.add(grp);
    }

    public void addFollower(Account acc) {
        following.add(acc);
        acc.followers.add(this);
        acc.addNotification("Friend request", "Friend Request "+username);
    }
    static public Account LogIn(String id,String password)
    {         System.out.println(id);
        System.out.println(password);
        for (Account account : accounts) {
            System.out.println(account.username);
            System.out.println(account.password);
            if(account.username.equals(id) && account.password.equals(password))
            {
                return account;
            }
        }
        return null;
    }
    static public Account  FetchAccountByUsername(String id)
    {
        for (Account account : accounts) {
            if(account.username.equals(id))
            {
                return account;
            }
        }
        return null;
    }

}
