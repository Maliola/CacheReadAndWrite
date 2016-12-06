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
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    TextView show;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show= (TextView) findViewById(R.id.show);
    }

    /***
     * 序列化对象
     * @param person
     * @return
     */
    private String serialize(Person person){
        String serStr=null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(person);
            serStr = byteArrayOutputStream.toString("ISO-8859-1");
            serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serStr;
    }

    /**
     * 反序列化对象
     * @param str
     * @return
     */
    private Person deSerialization(String str){
        String redStr = null;
        Person person=null;
        try {
            redStr = java.net.URLDecoder.decode(str, "UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            person = (Person) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                saveObject(serialize(new Person("小白",20,"朝阳")));
                break;
            case R.id.button2:
                Person person = deSerialization(getObject());
                show.setText(person.toString());
                break;
            default:
                break;
        }
    }
}
