package com.pethotel.demo;

import com.pethotel.demo.Pet;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PetRowMapper implements RowMapper<Pet> {
    @Override
    public Pet mapRow(ResultSet res, int rowNum) throws SQLException {
        Pet pet = new Pet();
        pet.setId(res.getInt("id"));
        pet.setOwnerId(res.getInt("owner_id"));
        pet.setPetName(res.getString("pet_name"));
        pet.setOwnerName(res.getString("owner_name"));
        pet.setBreed(res.getString("breed"));
        pet.setColor(res.getString("color"));
        pet.setCheckedInStatus(res.getBoolean("is_checked_in"));
        pet.setCheckedInDate(res.getDate("checked_in_date"));
        return pet;
    }
}