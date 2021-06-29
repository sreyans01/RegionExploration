package com.app.regionexploration.db;

import androidx.room.TypeConverter;

import com.app.regionexploration.model.Language;
import com.app.regionexploration.model.Region;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

class Converters {

    @TypeConverter
    public String listToJsonString(List<String> list){
        Gson gson = new Gson();
        String arrayData = gson.toJson(list);
        return arrayData;
    }

    @TypeConverter
    public List<String> jsonStringToList(String jsonString){

        Gson gson = new Gson();
        List<String> list = gson.fromJson(jsonString,  ArrayList.class);
        return list;
    }
}