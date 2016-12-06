package com.example.administrator.cachereadandwrite;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.cachereadandwrite.bean.Person;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {
    /*long startTime = 0l;
    long endTime = 0l;*/
    TextView show;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show= (TextView) findViewById(R.id.show);
    }

    /**
     * 序列化对象
     *
     * @param person
     * @return
     * @throws IOException
     */
    private String serialize(Person person) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(person);
        String serStr = byteArrayOutputStream.toString("ISO-8859-1");
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return serStr;
    }

    /**
     * 反序列化对象
     *
     * @param str
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private Person deSerialization(String str) throws IOException,
            ClassNotFoundException {
        String redStr = java.net.URLDecoder.decode(str, "UTF-8");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Person person = (Person) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return person;
    }

    void saveObject(String strObject) {
        SharedPreferences sp = getSharedPreferences("person", 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("person", strObject);
        edit.commit();
    }

    String getObject() {
        SharedPreferences sp = getSharedPreferences("person", 0);
        return sp.getString("person", null);
    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                try {
                    saveObject(serialize(new Person("LULU",20,"北京淀","学","112","bch")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button2:
                try {
                    Person person = deSerialization(getObject());
                    show.setText(person.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
