package com.example.testingg;

import java.util.ArrayList;

public class Post {
    int shares;

    static int globalID;
    int id;
    Account owner;
    ArrayList<String> comments = new ArrayList<String>();
    ArrayList<Account> likers = new ArrayList<Account>();
    static ArrayList<Post> posts = new ArrayList<Post>();
    String content;
    Account PostOwner;

    boolean commentsEnabled;

    public Post(String content, Account PostOwner) {
        this.id = globalID++;
        this.content = content;
        this.PostOwner = PostOwner;
        posts.add(this);
    }

    public static Post PostByID(int id) {
        for (Post posts : posts) {
            if (posts.getId() == id) {
                return posts;
            }
        }
        return null;
    }

    public static int getGlobalID() {
        return globalID;
    }

    public void addComment(String comment) throws IllegalAccessError {
        if (commentsEnabled) {
            comments.add(comment);
        } else {
            throw new IllegalAccessError();
        }
    }

    public void addLike(Account ac) {

        if (!likers.contains(ac)) {
            if (ac != PostOwner)
                PostOwner.addNotification("Likes", ac.username + " liked your post");
            likers.add(ac);
        }
    }


//    public String share() {
//        shares++;
//        return content;
//    }


    public int getId() {
        return id;
    }

    public String getPost() {
        return content;
    }
}
