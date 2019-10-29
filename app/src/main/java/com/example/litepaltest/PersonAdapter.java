package com.example.litepaltest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

class PersonAdapter extends BaseAdapter {
    private List<Person> personList;
    private Context mContext;

    public PersonAdapter(){
        personList = new ArrayList<>();
    }

    public PersonAdapter(List<Person> personList, Context mContext){
        this();
        this.personList = personList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Person getItem(int position) {
        return personList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.person_item,null);
            holder = new Holder();
            holder.personIdData = convertView.findViewById(R.id.personId_list);
            holder.personNameData = convertView.findViewById(R.id.personName_list);
            holder.personSexData = convertView.findViewById(R.id.personSex_list);
            holder.personAgeData = convertView.findViewById(R.id.personAge_list);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        holder.personIdData.setText(String.valueOf(personList.get(position).getId()));
        holder.personNameData.setText(personList.get(position).getName());
        holder.personSexData.setText(personList.get(position).getSex());
        holder.personAgeData.setText(String.valueOf(personList.get(position).getAge()));

        return convertView;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}