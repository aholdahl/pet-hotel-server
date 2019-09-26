package com.pethotel.demo;

public class Owner {
    private int id;
    private String owner_name;
    private int pet_count;

    // id getter
    public int getId() {
        return this.id;
    }
    // name getter
    public String getOwnerName() {
        return this.owner_name;
    }
    //pet count getter
    public int getPetCount(){
        return this.pet_count;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setOwnerName(String owner_name) {
        this.owner_name = owner_name;
    }

    public void setPetCount(int pet_count){
        this.pet_count = pet_count;
    }

    public Owner (){}
    public Owner (int id, String owner_name){
        this.id = id;
        this.owner_name = owner_name;
    }
}