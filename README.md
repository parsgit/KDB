[![](https://jitpack.io/v/parsgit/KDB.svg)](https://jitpack.io/#parsgit/KDB)

**KDB is an Android library for using the database**

### Install KDB

1. **Add the JitPack repository to your build file**

 Add it in your root build.gradle at the end of repositories:
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2. **Add the dependency**
```gradle
	dependencies {
          ...
         implementation 'com.google.code.gson:gson:2.2.4'
         implementation 'com.github.parsgit:KDB:1.0.1'
	}
```


### init DB and create database
```java 
        KDB.init(MainActivity.this,"db_name");
```

### create table 
```java
        KDB.CreateTableQuery createTableQuery=new KDB.CreateTableQuery();
        createTableQuery.Table("users")
                .Column("id").INTEGER().KEY_AUTO().Add()
                .Column("name").TEXT().Add()
                .Column("username").TEXT().NULL_ABLE(false).Add()
                .Column("age").INTEGER().DEFAULT(0).Add()
                .Build();
```
### insert data type 1 
```java
        JsonObject values=new JsonObject();
        values.addProperty("name","benjamin");
        values.addProperty("username","parsgit");
        values.addProperty("age",24);

        KDB.query("insert into users (name,username,age) values (:name,:username,:age)",values)
                .exec();
```

### insert data type 2
```java
        ArrayList<String> params=new ArrayList<>();
        params.add("myname-test");
        params.add("abcd");
        params.add("24");

        KDB.query("insert into users (name,username,age) values (?,?,?)",params).exec();
```

### select and get JsonArray data
```java
        JsonArray array=KDB.query("select * from users").getJsonArray();

        Log.i("KDB",array.toString());

        // output:[{"id":"1","name":"benjamin","username":"parsgit","age":"24"},{"id":"2","name":"myname-test","username":"abcd","age":"24"}]

```
