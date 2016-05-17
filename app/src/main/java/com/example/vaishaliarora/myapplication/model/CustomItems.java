package com.example.vaishaliarora.myapplication.model;

/**
 * Created by vaishaliarora on 13/05/16.
 */
public class CustomItems {

    private String header;
    private String item;
    private boolean isFruit;

    public CustomItems(String header, String item, boolean isFruit){
        this.header = header;
        this.item = item;
        this.isFruit = isFruit;
    }

    public String getHeader() {
        return header;
    }

    public String getItem() {
        return item;
    }

    public boolean isFruit() {
        return isFruit;
    }


}
