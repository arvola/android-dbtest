package com.arvola.android.dbtest.persistence;

import android.content.Context;
import com.arvola.android.dbtest.Database;
import com.codeslap.persistence.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * https://github.com/casidiablo/persistence
 */
public class PersistenceDatabase implements Database {

    Context context;

    List<Student> students;

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

        // This seems to be required for the actual database to be created
        adapter.findFirst(Subject.class, "", null);
    }

    @Override
    public void loadStudents(Gson gson, String json) {
        Type collectionType = new TypeToken<List<Student>>(){}.getType();
        students = gson.fromJson(json, collectionType);
    }

    @Override
    public void addData() {
        SqlAdapter adapter = Persistence.getAdapter(context);

        School school = new School();
        school.setName("Foo");

        adapter.store(school);

        Grade grade = new Grade();
        grade.setGrade(1);

        adapter.store(grade, school);

        adapter.storeCollection(students, grade, null);
    }
}
