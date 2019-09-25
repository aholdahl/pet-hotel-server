package com.pethotel.demo;
import java.util.Date;

public class Pet {
    private int id;
    private int owner_id;
    private String pet_name;
    private String breed;
    private String color;
    private boolean is_checked_in;
    private Date checked_in_date;

    //id getter
    public int getId() {
        return this.id;
    }
    //ownerId getter
    public int getOwnerId() {
        return this.owner_id;
    }
    //name getter
    public String getPetName() {
        return this.pet_name;
    }
    //breed getter
    public String getBreed() {
        return this.breed;
    }
    //color getter
    public String getColor() {
        return this.color;
    }
    //checked in status getter
    public boolean getCheckedInStatus() {
        return this.is_checked_in;
    }
    //checked in date getter
    public Date getCheckedInDate() {
        return this.checked_in_date;
    }
    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setOwnerId(int ownerId) {
        this.owner_id = ownerId;
    }

    public void setPetName(String name) {
        this.pet_name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCheckedInStatus(boolean status) {
        this.is_checked_in = status;
    }

    public void setCheckedInDate(Date date) {
        this.checked_in_date = date;
    }

    public Pet() {}

    public Pet(int id, int owner_id, String pet_name, String breed, String color, boolean is_checked_in, Date checked_in_date) {
        this.id = id;
        this.owner_id = owner_id;
        this.pet_name = pet_name;
        this.breed = breed;
        this.color = color;
        this.is_checked_in = is_checked_in;
        this.checked_in_date = checked_in_date;
    }

}