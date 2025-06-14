package com.campuscart.app.campus_cart.model;
import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String title;

    private String sellerID;
    
    private String description;

    private double price;
    
    private String location;

    private String postedTime;

    private int views;

    private String image;

    private boolean sold;


    public Item(String id, String title, String sellerID, String description, double price, String location, String postedTime, int views, String image, boolean sold) {
        this.id = id;
        this.title = title;
        this.sellerID = sellerID;
        this.description = description;
        this.price = price;
        this.location = location;
        this.postedTime = postedTime;
        this.views = views;
        this.image = image;
        this.sold = sold;       
    }

    public Item(String title, String sellerID, String description, double price, String location, String postedTime, String views, String image) {
        this.title = title;
        this.sellerID = sellerID;
        this.description = description;
        this.price = price;
        this.location = location;
        this.postedTime = postedTime;
        this.views = 0;
        this.image = image;
        this.sold = false;  
        
    }

    public void addView() {
        this.views++;
    }
    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(String postedTime) {
        this.postedTime = postedTime;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    

    

}
