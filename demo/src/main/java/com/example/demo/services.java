package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
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
    public ArrayList<UserData> addUser(@RequestBody UserData userData){
        ArrayList<UserData> usersData = userDataService.getUsersData();
        try {
            String hashedPassword = Hashing.getHashedHex(Hashing.getHashedBytes(userData.getPassword()));
            userData.setPassword(hashedPassword);
            usersData.add(userData);
            userDataService.writeUsersData(usersData);
            return usersData;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/users/{email}")
    public UserData getUsers(@PathVariable String email ) {
        return userDataService.getUserByEmail(email);
    }
    @PutMapping("/updatePassword/{email}")
    public void updatePassword(@PathVariable String email, @RequestBody String newPassword) {
        System.out.println("hi");
        newPassword=newPassword.replaceAll("\"","");
        ArrayList<UserData> usersData = userDataService.getUsersData();
        System.out.println(usersData);
        int x=0;
        for(int i=0;i<usersData.size();i++){
            if(usersData.get(i).getEmail().equals(email)){
                x=i;
                break;
            }
        }
        try {
            newPassword = Hashing.getHashedHex(Hashing.getHashedBytes(newPassword));
            usersData.get(x).updatePassword(newPassword);
            userDataService.writeUsersData(usersData);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/users/login")
    public Boolean checkPassword(@RequestBody String userPassword){
        ArrayList<UserData> usersData = userDataService.getUsersData();
        try {
            userPassword = Hashing.getHashedHex(Hashing.getHashedBytes(userPassword));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        int x=0;
        for (int i=0;i<usersData.size();i++){
            if(usersData.get(i).getPassword().equals(userPassword))
                return true;
        }
        return false;
    }
}

