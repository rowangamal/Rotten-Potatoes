package com.example.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

@Service
public class Control {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_DATA_JSON_FILE_NAME = "usersData.json";
    private static final String COMMENTS_JSON_FILE_NAME = "comments.json";

    public ArrayList<UserData> usersData;
    public ArrayList<Comment> comments;

    @PostConstruct
    public void init() {
        usersData = getUsersData();
        comments = getComments();
    }

    public ArrayList<UserData> getUsersData() {
        return readFromJsonFile(USERS_DATA_JSON_FILE_NAME, new TypeReference<ArrayList<UserData>>() {});
    }

    public void writeUsersData(ArrayList<UserData> usersData) {
        writeToJsonFile(USERS_DATA_JSON_FILE_NAME, usersData);
    }

    public ArrayList<Comment> getComments() {
        return readFromJsonFile(COMMENTS_JSON_FILE_NAME, new TypeReference<ArrayList<Comment>>() {});
    }

    public void writeComment(ArrayList<Comment> comments) {
        writeToJsonFile(COMMENTS_JSON_FILE_NAME, comments);
    }

    public UserData getUserByEmail(String email) {
        ArrayList<UserData> usersData = getUsersData();
        for (UserData x : usersData) {
            if (x.getEmail().equals(email)) {
                return x;
            }
        }
        return null;
    }

    public int getUserIndex(String email) {
        ArrayList<UserData> usersData = getUsersData();
        for (int i = 0; i < usersData.size(); i++) {
            if (usersData.get(i).getEmail().equals(email)) {
                return i;
            }
        }
        return -1;
    }

    private <T> ArrayList<T> readFromJsonFile(String fileName, TypeReference<ArrayList<T>> typeReference) {
        try {
            ClassPathResource resource = new ClassPathResource(fileName);
            File jsonFile = resource.getFile();
            if (!jsonFile.exists() || jsonFile.length() == 0) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(jsonFile, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private <T> void writeToJsonFile(String fileName, ArrayList<T> data) {
        try {
            Path filePath = Path.of(fileName);
            objectMapper.writeValue(filePath.toFile(), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
