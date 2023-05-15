package com.example.testingg;

import java.util.ArrayList;

public class Group {

    Account moderator;
    private ArrayList<Account> members = new ArrayList<Account>();

    private ArrayList<Post> posts = new ArrayList<Post>();

    boolean isPublic;

    String gName;

    public Group(Account moderator, boolean type, String name) {
        this.moderator = moderator;
        this.isPublic = type;
        this.gName = name;
    }

    public void joinGroup(Account acc) {
        members.add(acc);
    }

    public void addPost(Account acc, Post post) {
        if (members.contains(acc)) {
            posts.add(post);
            for (Account account : members) {
                account.addNotification("Group", "");
            }
        }
    }

    public boolean removeUser(Account mod, Account acc) {
        if (mod == moderator) {
            posts.remove(acc);
            return true;
        }
        return false;
    }

    public ArrayList<Post> getPosts(Account acc) {
        if (isPublic || members.contains(acc)) {
            return posts;
        } else {
            return null;
        }
    }

    public ArrayList<Account> getMembers(Account acc) {
        if (isPublic || members.contains(acc)) {
            return members;
        } else {
            return null;
        }
    }


}
