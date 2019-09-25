package com.pethotel.demo;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class Controller {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public String index(){
        return "Spring Boot is ready!";
    }

    // public List<String> getAllOwners {
    //     String query = "SELECT owner_name FROM owners";
    //     List<String> owners = jdbcTemplate.query(query, new Owner)
    // }

}