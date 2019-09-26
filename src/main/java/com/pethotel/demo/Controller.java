package com.pethotel.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class Controller {
    @Autowired // autowired sets jdbc template for injecting settings into routes
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/") // get route to '/', just used to confirm connection to server
    public String index(){
        return "Spring Boot is ready!";
    }

    @RequestMapping("/owners") // get route to /owners
    public List<Owner> getAllOwners (){ // creates list object containing Owner objects
        String query = "SELECT owners.id, owners.owner_name, count(pets.owner_id) AS pet_count FROM owners LEFT JOIN pets ON pets.owner_id = owners.id GROUP BY owners.id ORDER BY owners.id DESC;";
        List<Owner> owners = jdbcTemplate.query(query, new OwnerRowMapper());
        //OwnerRowMapper is our handler that parses out the response from the database, creating an Owner object for each of them
        //it then assigns this list of Owners into the List<Owner> owners, and returns it
        return owners;
    }

    @RequestMapping("/pets") // get route to /pets
    public List<Pet> getAllPets () {
        String query = "SELECT pets.id, pets.pet_name, pets.breed, pets.color, pets.is_checked_in, pets.checked_in_date, pets.owner_id, owners.owner_name FROM pets JOIN owners ON pets.owner_id = owners.id ORDER BY owners.owner_name;";
        List<Pet> pets = jdbcTemplate.query(query, new PetRowMapper());
        //PetRowMapper is the same as the ownerRowMapper above, that handles the response
        //from the database, sets it into a list of Pet objects, and returns it
        return pets;
    }

    @RequestMapping("/pets/sortDate")
    public List<Pet> getAllPetsByDate() {
        String query = "SELECT pets.id, pets.pet_name, pets.breed, pets.color, pets.is_checked_in, pets.checked_in_date, pets.owner_id, owners.owner_name FROM pets JOIN owners ON pets.owner_id = owners.id ORDER BY checked_in_date;";
        List<Pet> pets = jdbcTemplate.query(query, new PetRowMapper());
        return pets;
    }

    @PostMapping("/pets") // post route to /pets to add a new pet
    public void addPet(@RequestBody Pet newPet) {
        String query = "INSERT INTO pets (owner_id, pet_name, breed, color, is_checked_in, checked_in_date) VALUES (?,?,?,?,?,?);"; // ?'s are used for sanitization
        try {
            jdbcTemplate.update(query, newPet.getOwnerId(), newPet.getPetName(), newPet.getBreed(), newPet.getColor(), newPet.getCheckedInStatus(), newPet.getCheckedInDate());
            //jdbcTemplate will pass through each param and pass it into the ?s for sanitized query
        } catch (Exception e) {
            System.err.println(e);
            throw e;
            //throws error 'e' if encountered
        }
    }

    @PostMapping("/owners") // post route to /owners for new owner, works same as pet route above
    public void addOwner(@RequestBody Owner newOwner){
        String query = "INSERT INTO owners (owner_name) VALUES (?)";
        try {
            jdbcTemplate.update(query, newOwner.getOwnerName());
        } catch (Exception e){
            System.err.println(e);
            throw e;
        }
    }

    @DeleteMapping("/pets/{id}") // delete route for pets, id passed is for pet to delete
    public void deletePet(@PathVariable int id) { //@Pathvariable int id accesses the
        //id param above and sets it into the variable "id" with type of int
        String query = "DELETE FROM pets WHERE id = ?;";
        try {
            jdbcTemplate.update(query, id);
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        }
    }

    @DeleteMapping("/owners/{id}")//delete route for owners, works same as delete route above.
    public void deleteOwner(@PathVariable int id) {
        String query = "DELETE FROM owners WHERE id = ?;";
        //tables do not cascade on delete, error is expected if user tries to delete owner that still has pets
        try {
            jdbcTemplate.update(query, id);
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        }
    }

    @PatchMapping("/pets/check/{id}") //patch route for pet checking in/out
    public void checkInPet(@PathVariable int id) {
        String query = "UPDATE pets SET is_checked_in = not is_checked_in, checked_in_date = now() WHERE id = ?;";
        //query text toggles checkin status and sets new checkin date to now()
        try {
            jdbcTemplate.update(query, id);
        } catch (Exception e) {
            throw e;
        }
    };

    @PutMapping("/pets/edit/{id}") //put route for editing pet details
    //edits for id passed in on path, and receives new pet data in body of request
    public void editPet(@PathVariable int id, @RequestBody Pet newPet) {
        String query = "Update pets SET owner_id = ?, pet_name = ?, breed = ?, color = ? WHERE id = ?;";
        try {
            jdbcTemplate.update(query, newPet.getOwnerId(), newPet.getPetName(), newPet.getBreed(), newPet.getColor(), id);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/owners/edit/{id}") // put route for editing pet details
    // edits for id passed in on path, and receives new pet data in body of request
    public void editOwner(@PathVariable int id, @RequestBody Owner newOwner) {
        String query = "UPDATE owners SET owner_name = ? WHERE id = ?;";
        try {
            jdbcTemplate.update(query, newOwner.getOwnerName(), id);
        } catch (Exception e) {
            throw e;
        }
    }
}
