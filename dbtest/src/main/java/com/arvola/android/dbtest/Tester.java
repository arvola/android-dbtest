package com.arvola.android.dbtest;

import android.util.Log;

public class Tester {
    Database database;

    public Tester(Database db) {
        database = db;
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
