package com.example.myapplication;

public class Model2 {
    private String text;
    private String text1;
    private String text2;
    private int imageResource;
    private int imageStar;
    private int imageArrow;

    public Model2(int imageResource, int imageArrow, int imageStar, String text, String text1, String text2) {
        this.text = text;
        this.imageStar = imageStar;
        this.imageResource = imageResource;
        this.text1 = text1;
        this.text2 = text2;
        this.imageArrow = imageArrow;
    }
    public String getText() {
        return text;
    }
    public String getText1() {
        return text1;
    }
    public String getText2() {
        return text2;
    }
    public int getImageArrow() {
        return imageArrow;
    }
    public int getImageResource() {
        return imageResource;
    }
    public int getImageStar() {
        return imageStar;
    }
}
