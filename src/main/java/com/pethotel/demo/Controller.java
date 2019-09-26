package com.pethotel.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Controller {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public String index(){
        return "Spring Boot is ready!";
    }

    @RequestMapping("/owners")
    public List<Owner> getAllOwners (){
        String query = "SELECT * FROM owners";
        List<Owner> owners = jdbcTemplate.query(query, new OwnerRowMapper());
        return owners;
    }
    
    @RequestMapping("/pets")
    public List<Pet> getAllPets () {
        String query = "SELECT pets.id, pets.pet_name, pets.breed, pets.color, pets.is_checked_in, pets.checked_in_date, pets.owner_id, owners.owner_name FROM pets JOIN owners ON pets.owner_id = owners.id;";
        List<Pet> pets = jdbcTemplate.query(query, new PetRowMapper());
        return pets;
    }

    @PostMapping("/pets")
    public void addPet(@RequestBody Pet newPet) {
        String query = "INSERT INTO pets (owner_id, pet_name, breed, color, is_checked_in, checked_in_date) VALUES (?,?,?,?,?,?);";
        try {
            jdbcTemplate.update(query, newPet.getOwnerId(), newPet.getPetName(), newPet.getBreed(), newPet.getColor(), newPet.getCheckedInStatus(), newPet.getCheckedInDate());
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        }
    }

}