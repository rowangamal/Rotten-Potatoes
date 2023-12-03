package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*")
public class services {

    @Autowired
    private Control userDataService;

    @GetMapping("/users")
    public ArrayList<UserData> getUsers() {
        return userDataService.getUsersData();
    }

    @PostMapping("/users/add")
    public ArrayList<UserData> addUser(@RequestBody UserData userData) {
        ArrayList<UserData> usersData = userDataService.getUsersData();
        usersData.add(userData);
        userDataService.writeUsersData(usersData);
        System.out.println("hi");
        return usersData;
    }

    @GetMapping("/users/{email}")
    public UserData getUsers(@PathVariable String email ) {
        return userDataService.getUserByEmail(email);
    }
    @PutMapping("/updatePassword/{email}")
    public ResponseEntity<UserData> updatePassword(@PathVariable String email, @RequestBody String newPassword) {
        ArrayList<UserData> usersData = userDataService.getUsersData();
        Optional<UserData> existingUserOptional = usersData.stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst();

        if (existingUserOptional.isPresent()) {
            UserData existingUser = existingUserOptional.get();
            existingUser.updatePassword(newPassword);


            userDataService.writeUsersData(usersData);

            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

