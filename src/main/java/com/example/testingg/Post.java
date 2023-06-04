package com.example.testingg;

import java.util.ArrayList;

public class Post {
    static private int globalID;
    int id;
    ArrayList<Account> likers = new ArrayList<Account>();
    static ArrayList<Post> posts = new ArrayList<Post>();
    String content;
    Account PostOwner;


    public Post(String content, Account PostOwner) {
        this.id = ++globalID;
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

    public void addLike(Account ac) {

        if (!likers.contains(ac)) {
            if (ac != PostOwner)
                PostOwner.addNotification("Likes", ac.username + " liked your post");
            likers.add(ac);
        }
    }


    public int getId() {
        return id;
    }

    public String getPost() {
        return content;
    }

    public int getLikes(){
        return likers.size();
    }
}
