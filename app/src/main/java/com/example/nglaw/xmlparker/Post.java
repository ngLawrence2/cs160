package com.example.nglaw.xmlparker;

/**
 * Created by nglaw on 10/28/2017.
 */

public class Post {
    String address, city,zip,date,startTime,endTime,price,index,contact,owner;
    public Post(String address, String city, String zip, String date, String startTime,String endTime,String price,String index, String contact, String owner) {
        this.address=address;
        this.city=city;
        this.zip=zip;
        this.date=date;
        this.startTime=startTime;
        this.endTime=endTime;
        this.price=price;
        this.index= index;
        this.contact=contact;
        this.owner=owner;

    }
    public String toString() {
        return address+ " " + city + " " + zip + " " + date + " " + startTime+ " " +endTime+ " " +price + contact + " "+ owner;
    }
    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
    }
    public String getZip() {
        return zip;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String a) {
        contact = a;
    }
    public String getDate() {
        return date;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public String getPrice() {
        return price;
    }
    public String getIndex() {
        return index;
    }
    public String getOwner() { return owner; }
    public void setAddress(String a) {
        address = a;
    }
    public void setCity(String a) {
        city = a;
    }
    public void setZip(String a) {
        zip = a;
    }
    public void setDate(String a) {
        date = a;
    }
    public void setStartTime(String a) {
        startTime = a;
    }
    public void setEndTime(String a) {
        endTime= a;
    }
    public void setPrice(String a) {
        price = a;
    }
    public void setOwner(String a) { owner= a;}
    public void setIndex(String a) {
        index=a;
    }
}
