package com.example.demo;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
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
    @GetMapping("/Comments/{id}")
    public ArrayList<Comment> getUsers(@PathVariable int id) {
        ArrayList<Comment>res=userDataService.getComments();
        for(int i=res.size()-1;i>=0;i--){
            if(res.get(i).getId()!=id){
                res.remove(i);

            }
        }
        return res;
    }
    @PostMapping("/addComment")
    public void addComment(@RequestBody Comment cmnt) {
        ArrayList<Comment> cmnts = userDataService.getComments();

        try {
            cmnts.add(cmnt);
            userDataService.writeComment(cmnts);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/users/add")
    public void addUser(@RequestBody UserData userData){
        ArrayList<UserData> usersData = userDataService.getUsersData();
        try {
            String hashedPassword = Hashing.getHashedHex(Hashing.getHashedBytes(userData.getPassword()));
            userData.setPassword(hashedPassword);
            usersData.add(userData);
            userDataService.writeUsersData(usersData);
            System.out.println(usersData);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/users/{email}")
    public UserData getUsers(@PathVariable String email ) {
        return userDataService.getUserByEmail(email);
    }
    @PostMapping("/checkPass/{email}")
    public ResponseEntity<UserData> checkLogin(@PathVariable String email, @RequestBody String password) throws NoSuchAlgorithmException {
        UserData sentUser = userDataService.getUserByEmail(email);
        password = password.replaceAll("\"", "");

        if (sentUser != null) {
            try {
                String hashedInputPassword = Hashing.getHashedHex(Hashing.getHashedBytes(password));

                if (hashedInputPassword.equals(sentUser.getPassword())) {
                    System.out.println("Login successful");
                    return ResponseEntity.ok(sentUser);
                } else {
                    System.out.println("Incorrect password");
                }
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Error hashing password");
                throw new RuntimeException(e);
            }
        }


        return ResponseEntity.notFound().build();
    }
    @PostMapping("/updateUser/{email}")
    public void updateUser(@PathVariable String email, @RequestBody UserData user) {
        ArrayList<UserData> usersData = userDataService.getUsersData();
        try {
            int ind =userDataService.getUserIndex(user.getEmail());
            usersData.set(ind,user);
            userDataService.writeUsersData(usersData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    @GetMapping("/potatoWrap/{email}")
    public AnalysisResultsDTO getAnalysis(@PathVariable String email) {
        ArrayList<UserData> usersData = userDataService.getUsersData();
        System.out.println(usersData);
        for(int i=0;i<usersData.size();i++){
            if(usersData.get(i).getEmail().equals(email)){
                AnalysisResultsDTO analysisResultsDTO = userDataService.dataAnalysis(usersData.get(i));
//                return userDataService.dataAnalysis(usersData.get(i));
                System.out.println(analysisResultsDTO);
                return analysisResultsDTO;
            }
        }
        return new AnalysisResultsDTO();
    }
//    @PostMapping("/users/login")
//    public Boolean checkPassword(@RequestBody String userPassword){
//        ArrayList<UserData> usersData = userDataService.getUsersData();
//        try {
//            userPassword = Hashing.getHashedHex(Hashing.getHashedBytes(userPassword));
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//        int x=0;
//        for (int i=0;i<usersData.size();i++){
//            if(usersData.get(i).getPassword().equals(userPassword))
//                return true;
//        }
//        return false;
//    }
}

