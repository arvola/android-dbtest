package com.arvola.android.dbtest;

import android.util.Log;

public class Tester {
    Database database;
    JsonLoader loader;

    public Tester(Database db, JsonLoader loader) {
        database = db;
        this.loader = loader;
    }

    public void runCreate() {
        recordMeasurement("create", measureCall(new Runnable() {
            @Override
            public void run() {
                database.create();
            }
        }));
    }

    public void runAddData() {
        database.loadStudents(loader.getGson(), loader.loadStudents());

        recordMeasurement("addData", measureCall(new Runnable() {
            @Override
            public void run() {
                database.addData();
            }
        }));
    }

    protected long measureCall(Runnable runnable) {
        long start = System.currentTimeMillis();

        runnable.run();

        return System.currentTimeMillis() - start;
    }

    protected void recordMeasurement(String name, long time) {
        Log.i("Tester", "Measurement for '" + name + "': " + time + " ms");
    }
}
