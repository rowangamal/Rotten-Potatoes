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

        private static final String JSON_FILE_PATH = "D:\\Rowan's CSE\\Term 5\\HCI\\rottenPotatoes\\Rotten-Potatoes\\demo\\usersData.json";
    private static final String cmnts = "D:\\Rowan's CSE\\Term 5\\HCI\\rottenPotatoes\\Rotten-Potatoes\\demo\\comments.json";
//    private static final String JSON_FILE_PATH = "D:\\vue\\Rotten-Potatoes\\demo\\usersData.json";
//    private static final String cmnts = "D:\\vue\\Rotten-Potatoes\\demo\\comments.json";


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
    public void writeComment(ArrayList<Comment> comments) {
        try {
            objectMapper.writeValue(new File(cmnts), comments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Comment> getComments() {
        try {
            return objectMapper.readValue(new File(cmnts), new TypeReference<ArrayList<Comment>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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
    public int getUserIndex(String email) {
        ArrayList<UserData> usersData = getUsersData();
        for(int i=0;i<usersData.size();i++){
            if(usersData.get(i).getEmail().equals(email)){
                return i;
            }
        }
        return -1;
    }
    public AnalysisResultsDTO dataAnalysis(UserData userData){
        AnalysisResultsDTO analysisResultsDTO = new AnalysisResultsDTO();
        Double totalRating= (double) 0;
        int movieCount = 0;
        for (MovieMetadata movieMetadata : userData.getFavourites()){
            String genre = movieMetadata.getGenre();
//            int year = movieMetadata.getDate().getYear();
            analysisResultsDTO.genrePreferences.put(genre, analysisResultsDTO.genrePreferences.getOrDefault(genre, 0) + 1);
//            analysisResultsDTO.yearFrequency.put(year, analysisResultsDTO.genrePreferences.getOrDefault(year, 0) + 1);
            totalRating += movieMetadata.getRate();
            movieCount++;
        }
        for (MovieMetadata movieMetadata : userData.getWatchlist()){
            String genre = movieMetadata.getGenre();
//            int year = movieMetadata.getDate().getYear();
            analysisResultsDTO.genrePreferences.put(genre, analysisResultsDTO.genrePreferences.getOrDefault(genre, 0) + 1);
//            analysisResultsDTO.yearFrequency.put(year, analysisResultsDTO.genrePreferences.getOrDefault(year, 0) + 1);
            totalRating += movieMetadata.getRate();
            movieCount++;
        }
        analysisResultsDTO.averageRating = totalRating/movieCount;
        analysisResultsDTO.movieCount = movieCount;
        return analysisResultsDTO;
    }
}
