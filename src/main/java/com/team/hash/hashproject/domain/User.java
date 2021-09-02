package com.team.hash.hashproject.domain;

public class User {

    private String email;
    private String password;
    private String name;
    private String sex;
    private String height;
    private String weight;
    private String pictureUrl;
    private String description;
    private String strTagArray;
    private String instargramId;

    public User(String email, String password, String name, String sex, String height, String weight, String pictureUrl, String description, String strTagArray, String instargramId) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.strTagArray = strTagArray;
        this.instargramId = instargramId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStrTagArray() {
        return strTagArray;
    }

    public void setStrTagArray(String strTagArray) {
        this.strTagArray = strTagArray;
    }

    public String getInstargramId() {
        return instargramId;
    }

    public void setInstargramId(String instargramId) {
        this.instargramId = instargramId;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", description='" + description + '\'' +
                ", strTagArray='" + strTagArray + '\'' +
                ", instargramId='" + instargramId + '\'' +
                '}';
    }
}
