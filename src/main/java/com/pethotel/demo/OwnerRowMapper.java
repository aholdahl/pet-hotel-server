package com.pethotel.demo;

import com.pethotel.demo.Owner;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class OwnerRowMapper implements RowMapper<Owner> {
    @Override
    public Owner mapRow (ResultSet res, int rowNum) throws SQLException {
        Owner owner = new Owner();
        owner.setId(res.getInt("id"));
        owner.setOwnerName(res.getString("owner_name"));
        owner.setPetCount(res.getInt("pet_count"));
        return owner;
    }
}