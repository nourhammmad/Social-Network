package com.example.testingg;
//TODO: add moderator acceptance
//TODO: add database IF WE HAVE TIME
//

public class Main {
    public static void main(String[] args) {

        Account account = new Account("NourHammad", "1234567890", "Employee");
        Account account2 = new Account("koko", "123", "Employee");
        Account ds=Account.FetchAccountByUsername(account.username);

        System.out.println(ds.username);


//        account.username = "Mark";
//        System.out.println(account.username);
    }

}


