package com.example.demo;

import java.time.LocalDate;

public class MovieMetadata {
    private int id;
    private String title;
    private Double rate;
    private String date;
    private String img;
    private String genres;

    public MovieMetadata() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGenre() {
        return genres;
    }

    public void setGenre(String genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "MovieMetadata{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rate=" + rate +
                ", date=" + date +
                ", img='" + img + '\'' +
                ", genre='" + genres + '\'' +
                '}';
    }
}
