package com.example.demo;

import java.util.HashMap;

public class AnalysisResultsDTO {
    public HashMap<String,Integer> genrePreferences;
//    public HashMap<Integer,Integer> yearFrequency;
    public Double averageRating;
    public int movieCount;

    public AnalysisResultsDTO() {
        this.genrePreferences = new HashMap<>();
    }

    @Override
    public String toString() {
        return "AnalysisResultsDTO{" +
                "genrePreferences=" + genrePreferences +
                ", averageRating=" + averageRating +
                ", movieCount=" + movieCount +
                '}';
    }
}
