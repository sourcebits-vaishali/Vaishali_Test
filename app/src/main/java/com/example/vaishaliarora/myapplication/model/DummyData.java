package com.example.vaishaliarora.myapplication.model;

import com.example.vaishaliarora.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaishaliarora on 30/05/16.
 */
public class DummyData {

    public static List<String> getTranslatorList(){
        List<String> results = new ArrayList<>();
        results.add("Apple");
        results.add("Orange");
        results.add("Mango");
        results.add("Banana");
        results.add("Grapes");
        results.add("PineApple");
        results.add("Guvava");
        return results;
    }

    public static Integer[] getWallpapers(){
        Integer [] images = {R.drawable.wallpaper1,R.drawable.wallpaper2,
                R.drawable.wallpaper3,R.drawable.wallpaper4,
                R.drawable.wallpaper5,R.drawable.wallpaper6
        };
        return images;
    }

    public static List<CustomItems> getItems(){
        List<CustomItems> items = new ArrayList<>();
        items.add(new CustomItems("Fruit", "Apple", true));
        items.add(new CustomItems("Fruit", "Orange", true));
        items.add(new CustomItems("Fruit", "Mango", true));
        items.add(new CustomItems("Fruit", "Banana", true));
        items.add(new CustomItems("Fruit", "Grapes", true));
        items.add(new CustomItems("Vegetable", "Tomato", false));
        items.add(new CustomItems("Vegetable", "Potato", false));
        items.add(new CustomItems("Vegetable", "Onion", false));
        items.add(new CustomItems("Vegetable", "Lemon", false));
        items.add(new CustomItems("Vegetable", "Carrot", false));

        return items;
    }
}
