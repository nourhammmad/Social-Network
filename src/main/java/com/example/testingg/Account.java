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

        if (passed) {
            System.out.println("Adding");
            this.username = username;
            this.password = password;
            this.role = role;
            accounts.add(this);
        }
    }


    public boolean isPresent(String username) {
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
        acc.followers.add(acc);
        acc.addNotification("Friend request", "");
    }


}
