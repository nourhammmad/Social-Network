package com.example.testingg;

import java.util.ArrayList;

public class Group {

    Account moderator;
    private ArrayList<Account> members = new ArrayList<Account>();

    public ArrayList<Post> posts = new ArrayList<Post>();
    public static ArrayList<Group> ListofGroups = new ArrayList<Group>();

    boolean isPublic;

    String gName;

    public Group(Account moderator, boolean isPublic, String name) {
        this.moderator = moderator;
        this.isPublic = isPublic;
        this.gName = name;
        if (!(moderator.role.equals("Team Leader"))) {

            throw new IllegalArgumentException("Only Team Leader can create a group");
        }
        for (Group group : ListofGroups) {
            if(group.gName.equals(name))
            {
                throw new IllegalArgumentException("Group name already taken");
            }
        }
        ListofGroups.add(this);
        moderator.groups.add(this);

    }

    public void joinGroup(Account acc) {
        if(!isPublic)
        {
            moderator.addNotification("Join Request","Join group request sent by "+acc.username);
        }

        members.add(acc);
    }

    public void addPost(Account acc, Post post) {
        if (members.contains(acc)) {
            posts.add(post);
            for (Account account : members) {
                account.addNotification("Group", acc.username+ " added a post in a group");
            }
        }
        posts.add(post);
    }

    public boolean removeUser(Account mod, Account acc) {
        if (mod == moderator) {
            members.remove(acc);
            return true;
        }
        return false;
    }

    public ArrayList<Post> getPosts(Account acc) {
        if (isPublic || members.contains(acc) || acc==moderator) {
            return posts;
        } else {
            return null;
        }
    }

    public ArrayList<Account> getMembers(Account acc) {
        if (isPublic || members.contains(acc) || acc==moderator) {
            return members;
        } else {
            return null;
        }
    }

    static public Group FetchGroupByName(String name) {
        for (Group group : ListofGroups) {
            if (group.gName.equals(name)) {
                return group;
            }
        }
        return null;
    }

    static public boolean isPresent(String name) {
        for (Group group : ListofGroups) {
            if (group.gName.equals(name)) {
                return true;
            }

        }
        return false;
    }
}