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
        return usersData;
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
        usersData.get(x).updatePassword(newPassword);
        userDataService.writeUsersData(usersData);
    }
}

