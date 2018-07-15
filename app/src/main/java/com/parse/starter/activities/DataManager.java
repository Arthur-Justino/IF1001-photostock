package com.parse.starter.activities;

/**
 * Created by artju on 06/07/2018.
 */

public class DataManager {

    private static DataManager dataManager;
    private String imageUrl;

    public static DataManager getInstance(){

        if(dataManager == null){
            dataManager = new DataManager();
        }
        return dataManager;
    }

    private DataManager(){}

    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
}
