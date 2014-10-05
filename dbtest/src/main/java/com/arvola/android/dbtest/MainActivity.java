package com.arvola.android.dbtest;

import android.app.Activity;
import android.os.Bundle;
import com.arvola.android.dbtest.persistence.PersistenceDatabase;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersistenceDatabase persistence = new PersistenceDatabase(getApplicationContext());
        Tester tester = new Tester(persistence, new JsonLoader(getApplicationContext()));
        tester.runCreate();
        tester.runAddData();

    }
}
