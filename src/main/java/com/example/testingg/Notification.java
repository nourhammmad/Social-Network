package com.example.testingg;

import java.util.Date;


public class Notification {
    String category, content;

    static int globalID;

    int id;
    Date date;


    public Notification(String category, String content) {
        this.id = globalID++;
        this.category = category;
        this.content = content;
        this.date = new Date();
    }
}
