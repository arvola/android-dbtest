package com.arvola.android.dbtest;

import com.google.gson.Gson;

public interface Database {
    void create();

    void addData();

    void loadStudents(Gson gson, String json);
}
