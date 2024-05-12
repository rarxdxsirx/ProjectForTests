package com.example.projectfortests;

import android.content.Context;
import android.content.SharedPreferences;


public class DataImpl{
    private static final String SHARED_PREFS_NAME = "shared_prefs_name";

    private static final String KEY_FIRST_DATA = "firstData";

    private static final String KEY_SECOND_DATA = "secondData";

    private static final String KEY_THIRD_DATA = "thirdData";

    private static final String DEFAULT_DATA = "Default";

    Context context;
    private SharedPreferences sharedPreferences;

    public DataImpl(Context context, String prefsName) { // Добавляем параметр для имени SharedPreferences
        this.context = context;
        sharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
    }

    public DataImpl(Context context) {

        this.context = context;

        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }
    Boolean saveData(DataModel data){
        if (data != null && Integer.valueOf(data.firstData) != 0){
            sharedPreferences.edit().putString(KEY_FIRST_DATA, data.firstData).apply();
            sharedPreferences.edit().putString(KEY_SECOND_DATA, data.secondData).apply();
            sharedPreferences.edit().putString(KEY_THIRD_DATA, data.thirdData).apply();
            return true;
        }
        return false;
    }
    DataModel getData(){
        String firstData = sharedPreferences.getString(KEY_FIRST_DATA, DEFAULT_DATA);
        String secondData = sharedPreferences.getString(KEY_SECOND_DATA, DEFAULT_DATA);
        String thirdData = sharedPreferences.getString(KEY_THIRD_DATA, DEFAULT_DATA);
        if (firstData == null){
            firstData = DEFAULT_DATA;
            secondData = DEFAULT_DATA;
            thirdData = DEFAULT_DATA;
        }
        return new DataModel(firstData, secondData, thirdData);
    }


}
