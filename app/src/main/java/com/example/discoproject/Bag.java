package com.example.discoproject;

public class Bag implements Comparable<Bag> {
    private long id;
    private String name;
    private double rating;
    private int price;
    private String userName;

    public Bag(long id, String name, double rating, int price, String userName)
    {
        this.id=id;
        this.name = name;
        this.rating=rating;
        this.price=price;
        this.userName = userName;
    }
    public long getId() { return id; }

    public void setId (long id) { this.id=id;}

    public String getName() { return name; }

    public void  setName (String name) {this.name=name;}

    public double getRating() { return rating; }

    public  void setRating (double rating) {this.rating=rating;}

    public int getPrice() { return price; }

    public void setPrice (int price) {this.price=price;}

    public String getUserName() { return userName; }

    public void  setUserName (String userName) {this.userName=userName;}

    @Override
    public int compareTo(Bag o) {
        if(this.id==o.id){
            return 1;
        }
        else{
            return  0;
        }
    }
}
