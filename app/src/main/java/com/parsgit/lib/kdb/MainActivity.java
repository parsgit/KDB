package com.parsgit.lib.kdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init DB
        KDB.init(MainActivity.this,"db_name");

        // create table \/
        KDB.CreateTableQuery createTableQuery=new KDB.CreateTableQuery();
        createTableQuery.Table("users")
                .Column("id").INTEGER().KEY_AUTO().Add()
                .Column("name").TEXT().Add()
                .Column("username").TEXT().TEXT().NULL_ABLE(false).Add()
                .Column("age").INTEGER().DEFAULT(0).Add()
                .Build();

        // ======== insert data type 1 =====
        JsonObject values=new JsonObject();
        values.addProperty("name","benjamin");
        values.addProperty("username","parsgit");
        values.addProperty("age",24);

        KDB.query("insert into users (name,username,age) values (:name,:username,:age)",values)
                .exec();

        // ======= insert data type 2  =====
        ArrayList<String> params=new ArrayList<>();
        params.add("myname-test");
        params.add("abcd");
        params.add("24");

        KDB.query("insert into users (name,username,age) values (?,?,?)",params).exec();


        // ===== select sample ====
        JsonArray array=KDB.query("select * from users").getJsonArray();

        Log.i("KDB",array.toString());

        // output:[{"id":"1","name":"benjamin","username":"parsgit","age":"24"},{"id":"2","name":"myname-test","username":"abcd","age":"24"}]

    }
}
