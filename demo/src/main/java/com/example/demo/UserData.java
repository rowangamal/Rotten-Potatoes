package com.example.demo;

import javax.crypto.SecretKey;
import java.util.ArrayList;

public class UserData {
    private String email;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private ArrayList<Integer> favourites;
    private ArrayList<Integer> watchlist;
    private String firstName;
    private String lastName;
    private String gender;
    private int id;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public ArrayList<Integer> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Integer> favourites) {
        this.favourites = favourites;
    }

    public ArrayList<Integer> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(ArrayList<Integer> watchlist) {
        this.watchlist = watchlist;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", securityQuestion='" + securityQuestion + '\'' +
                ", securityAnswer='" + securityAnswer + '\'' +
                ", favourites=" + favourites +
                ", watchlist=" + watchlist +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
