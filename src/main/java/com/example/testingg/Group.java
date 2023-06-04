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
            if (group.gName.equals(name)) {
                throw new IllegalArgumentException("Group name already taken");
            }
        }
        ListofGroups.add(this);
        moderator.groups.add(this);

    }

    public void joinGroup(Account acc) {
        if (!isPublic) {
            moderator.addNotification("Join Request", "Join group request sent by " + acc.username);
        }

        members.add(acc);
    }

    public void addPost(Account acc, Post post) throws IllegalAccessException {
        if (members.contains(acc) || acc == moderator) {
            posts.add(post);
            for (Account account : members) {
                if (account != acc) {
                    account.addNotification("Group", acc.username + " added a post in a group");
                }
            }
            if (moderator != acc) {
                moderator.addNotification("Group", acc.username + " added a post in a group");
            }
        } else {
            throw new IllegalAccessException("Cannot post");
        }
    }

    public boolean removeUser(Account mod, Account acc) {
        if (mod == moderator) {
            members.remove(acc);
            acc.groups.remove(this);
            return true;
        }
        return false;
    }

    public ArrayList<Post> getPosts(Account acc) {
        if (isPublic || members.contains(acc) || acc == moderator) {
            return posts;
        } else {
            return null;
        }
    }

    public ArrayList<Account> getMembers(Account acc) {
        if (isPublic || members.contains(acc) || acc == moderator) {
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

    public boolean isPresentMember(String name) {
        for (Account acc : members) {
            if (acc.username.equals(name)) {
                return true;
            }

        }
        return false;
    }
}