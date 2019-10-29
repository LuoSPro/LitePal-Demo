package com.example.litepaltest;

import android.content.ContentValues;
import android.os.Bundle;
import android.print.PrinterId;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG= "LitePal";
    private Button createdatabase;
    private Button addData;
    private Button deleteData;
    private Button updataData;
    private Button queryData;
    private EditText mEditTextId;
    private EditText mEditTextName;
    private EditText mEditTextSex;
    private EditText mEditTextAge;
    private ListView personListView;
    private List<Person> personList;
    private Person person;
    private PersonAdapter mAdapter;

    private int id;
    private String name;
    private String sex;
    private int age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.initialize(this);
        initData();//初始化数据
        //绑定事件
        addData.setOnClickListener(this);
        deleteData.setOnClickListener(this);
        updataData.setOnClickListener(this);
        queryData.setOnClickListener(this);
        createdatabase.setOnClickListener(this);
    }

    private void initData() {
        createdatabase = findViewById(R.id.create_database);
        addData = findViewById(R.id.add_data);
        deleteData = findViewById(R.id.delete_data);
        updataData = findViewById(R.id.update_data);
        queryData = findViewById(R.id.query_data);
        mEditTextId = findViewById(R.id.personId);
        mEditTextName = findViewById(R.id.personName);
        mEditTextSex = findViewById(R.id.personSex);
        mEditTextAge = findViewById(R.id.personAge);
        personListView = findViewById(R.id.personData);
        personList = new ArrayList<>();

        personList = LitePal.findAll(Person.class);//把数据库中的数据读取出来

        mAdapter = new PersonAdapter(personList,MainActivity.this);
        personListView.setAdapter(mAdapter);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.create_database://创建数据库
                LitePal.getDatabase();
                break;
            case R.id.add_data://增加数据
                person = new Person();
                person.setName(mEditTextName.getText().toString());
                person.setSex(mEditTextSex.getText().toString());
                person.setAge(Integer.parseInt(mEditTextAge.getText().toString()));
                if (person.save()){
                    Toast.makeText(MainActivity.this,"添加成功",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"添加失败",Toast.LENGTH_LONG).show();
                }

                //查询数据库全部数据，便于在ListView中展示
                personList = LitePal.findAll(Person.class);
                mAdapter.setPersonList(personList);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.delete_data://删除数据
                id = Integer.parseInt(mEditTextId.getText().toString());
                LitePal.delete(Person.class,id);
                //查询数据库全部数据，便于在ListView中展示
                personList = LitePal.findAll(Person.class);
                mAdapter.setPersonList(personList);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.update_data://修改数据
                id = Integer.parseInt(mEditTextId.getText().toString());
                person = LitePal.find(Person.class,id);
                if (person == null){
                    break;
                }
                person.setName(mEditTextName.getText().toString());
                person.setSex(mEditTextSex.getText().toString());
                person.setAge(Integer.parseInt(mEditTextAge.getText().toString()));
                if ("".equals(mEditTextId.getText().toString())){
                    Toast.makeText(MainActivity.this,"修改数据时，ID不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                if (!"".equals(name)){
                    person.setName(mEditTextName.getText().toString());
                }
                if (!"".equals(sex)){
                    person.setSex(mEditTextSex.getText().toString());
                }
                if (!"".equals(mEditTextAge.getText().toString())){
                    person.setAge(Integer.parseInt(mEditTextAge.getText().toString()));
                }
                person.update(id);
                personList = LitePal.findAll(Person.class);
                mAdapter.setPersonList(personList);
                mAdapter.notifyDataSetChanged();

                /*name = mEditTextName.getText().toString();
                sex = mEditTextSex.getText().toString();
                ContentValues values = new ContentValues();
                if ("".equals(mEditTextId.getText().toString())){
                    Toast.makeText(MainActivity.this,"修改数据时，ID不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                if (!"".equals(name)){
                    values.put("name",name);
                }
                if (!"".equals(sex)){
                    values.put("sex",sex);
                }
                if (!"".equals(mEditTextAge.getText().toString())){
                    age = Integer.parseInt(mEditTextAge.getText().toString());
                    values.put("age",age);
                }
                LitePal.update(Person.class,values,id);
                //查询数据库全部数据，便于在ListView中展示
                personList = LitePal.findAll(Person.class);
                mAdapter.notifyDataSetChanged();*/
                break;
            case R.id.query_data://查询数据
                id = Integer.parseInt(mEditTextId.getText().toString());
                personList = LitePal.findAll(Person.class,id);
                mAdapter.setPersonList(personList);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }
}
