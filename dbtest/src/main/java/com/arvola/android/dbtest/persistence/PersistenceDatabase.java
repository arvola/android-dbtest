package com.arvola.android.dbtest.persistence;

import android.content.Context;
import com.arvola.android.dbtest.Database;
import com.codeslap.persistence.*;

/**
 * https://github.com/casidiablo/persistence
 */
public class PersistenceDatabase implements Database {

    Context context;

    public PersistenceDatabase(Context context) {
        this.context = context;
    }

    @Override
    public void create() {
        DatabaseSpec database = PersistenceConfig.registerSpec(2);
        database.match(School.class, Grade.class, Student.class, Subject.class);
        database.match(new HasMany(School.class, Grade.class));
        database.match(new HasMany(Grade.class, Student.class));
        database.match(new ManyToMany(Student.class, Subject.class));

        SqlAdapter adapter = Persistence.getAdapter(context);

        adapter.findFirst(Subject.class, "", null);
    }

    @Override
    public void addData() {
        SqlAdapter adapter = Persistence.getAdapter(context);

        School west = new School();
        west.setName("West");
        adapter.store(west);

        School east = new School();
        east.setName("East");
        adapter.store(east);

        Grade west1 = new Grade();
        west1.setSchool(west);
        west1.setGrade(1);
        adapter.store(west1);

        west.getGrades().add(west1);
        adapter.store(west);
    }
}
