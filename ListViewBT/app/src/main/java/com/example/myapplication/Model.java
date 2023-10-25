package com.example.myapplication;

public class Model {

    private String text;
    private String text1;
    private String text2;
    private int imageResource;
    private int imageMeal;
    private int imageSale;
    private int imageArrow;

    public Model(int imageResource, int imageArrow, int imageMeal, int imageSale, String text, String text1, String text2) {
        this.text = text;
        this.imageMeal = imageMeal;
        this.imageSale = imageSale;
        this.imageResource = imageResource;
        this.text1 = text1;
        this.text2 = text2;
        this.imageArrow = imageArrow;
    }
    public int getImageMeal() {
        return imageMeal;
    }
    public int getImageSale() {
        return imageSale;
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
}
