package com.example.demo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class Control {
    private final ObjectMapper objectMapper = new ObjectMapper();
    //file abdullah path: "D:\\vue\\Rotten-Potatoes\\demo\\usersData.json"
    //rowan file path: "D:\\Rowan's CSE\\Term 5\\HCI\\rottenPotatoes\\Rotten-Potatoes\\demo\\usersData.json"
    //bahaa file path: "E:\\programming\\Project\\Rotten-Potatoes\\demo\\usersData.json"
    private static final String JSON_FILE_PATH = "D:\\vue\\Rotten-Potatoes\\demo\\usersData.json";
    public ArrayList<UserData> usersData;



    public ArrayList<UserData> getUsersData() {
        try {
            return objectMapper.readValue(new File(JSON_FILE_PATH), new TypeReference<ArrayList<UserData>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeUsersData(ArrayList<UserData> usersData) {
        try {
            objectMapper.writeValue(new File(JSON_FILE_PATH), usersData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserData getUserByEmail(String email) {
        ArrayList<UserData> usersData = getUsersData();
        for(UserData x:usersData){
            if(x.getEmail().equals(email)){
                return x;
            }
        }
        return null;
    }
}
