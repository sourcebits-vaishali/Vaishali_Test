package com.example.vaishaliarora.myapplication.activities;


import java.util.HashMap;
import java.util.List;

public interface Listener {

    void onSuccessfullRouteFetch(List<List<HashMap<String, String>>> result);
    void onFail();

}
