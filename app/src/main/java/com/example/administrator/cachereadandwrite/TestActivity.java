package com.example.administrator.cachereadandwrite;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.cachereadandwrite.bean.Person;
import com.example.administrator.cachereadandwrite.bean.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/5.
 */

public class TestActivity extends Activity{
    TextView show;
    HashMap<String,List> map=new HashMap<String,List>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show= (TextView) findViewById(R.id.show);
    }
    public void onclick(View v){
        switch (v.getId()){
            case R.id.button1:
                save();
                break;
            case R.id.button2:
                Map<String,List> map=show();
                List<Person> persons=map.get("person");
                List<User> users=map.get("user");
                StringBuilder sb=new StringBuilder();
                for(int i=0;i<persons.size();i++){
                    sb.append(persons.get(i).toString()+"\n");
                }
                for(int i=0;i<users.size();i++){
                    sb.append(users.get(i).toString()+"\n");
                }
                show.setText(sb.toString());
                break;
        }
    }
    public void save(){
        try{
            File dir = new File(getSDPath()+"/test/");
            if(!dir.exists()){
                dir.mkdirs();
            }
            File file=new File(getSDPath()+"/test/recommend");
            if (!file.exists()) {
                //在指定的文件夹中创建文件
                file.createNewFile();
            }
            FileOutputStream out;
            out = new FileOutputStream(file);
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            List<Person> list=new ArrayList<Person>();
            list.add(new Person("小白",17,"北京"));
            list.add(new Person("小红",18,"上海"));
            list.add(new Person("小兰",19,"广州"));
            map.put("person",list);
            List<User> list1=new ArrayList<User>();
            list1.add(new User("小黑",20));
            list1.add(new User("二狗",21));
            list1.add(new User("狗蛋",22));
            map.put("user",list1);
            objOut.writeObject(map);
            objOut.flush();
            objOut.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Map<String,List> show(){
        Map<String,List> temp=null;
        File file =new File(getSDPath()+"/test/recommend");
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            temp= (Map<String, List>) objIn.readObject();
            objIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }
    public String getSDPath() {
        File sdDir=null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在*/
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        } else {
            Toast.makeText(TestActivity.this,"没有sd卡",Toast.LENGTH_SHORT).show();
        }
        return sdDir.toString();
    }
}
