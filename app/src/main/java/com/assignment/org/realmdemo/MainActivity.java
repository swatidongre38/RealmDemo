package com.assignment.org.realmdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    TextView mTextViewStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewStudent = findViewById(R.id.textViewStudent);
        realm = Realm.getDefaultInstance();
        displayStudentData(realm);
    }

    private void displayStudentData(Realm realm) {
        clearDatabase();
        createStudentsEntry(realm);
        displayStudentList(realm);
        checkStudentCount(realm);
        checkBetweenMethod(realm);
        checkBeginsWithMethod(realm);
    }

    private void createStudentsEntry(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Student student = realm.createObject(Student.class);
                student.setName("Mehul Verma");
                student.setAge(10);
            }
        });

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Student students = realm.createObject(Student.class);
                students.setName("Rachel Zane");
                students.setAge(15);
            }
        });
    }

    private void displayStudentList(Realm realm) {
        final RealmResults<Student> allStudent = realm.where(Student.class).findAll();
        for (Student stusentList : allStudent) {
            mTextViewStudent.setText("Name: " + stusentList.getName() + "Roll No:"
                    + stusentList.getAge());
        }
    }

    private void checkBeginsWithMethod(Realm realm) {
        RealmResults<Student> beginList = realm.where(Student.class)
                .beginsWith("name", "R").findAll();

        Toast.makeText(this, "Name of student: " + beginList.get(0).getName(), Toast.LENGTH_SHORT)
                .show();
    }

    private void checkBetweenMethod(Realm realm) {
        // Method used to find student whose age between 1 to 12
        RealmResults<Student> results = realm.where(Student.class)
                .between("age", 1, 12).findAll();
        Toast.makeText(this, "Age of student: " + results.get(0).getName(), Toast.LENGTH_SHORT).show();
    }

    private void checkStudentCount(Realm realm) {
        long count = realm.where(Student.class).count();
        Toast.makeText(this, "Count of studnets: " + count, Toast.LENGTH_LONG).show();
    }

    private void clearDatabase() {
        // Delete all persons
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Student.class);
            }
        });
    }
}
