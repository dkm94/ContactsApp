package com.example.contactsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    private ArrayList<ContactModel> contacts;
    private Context context;

    public ContactAdapter(Context context, ArrayList<ContactModel> contacts) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return this.contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_contact, viewGroup, false);
        }

        TextView textViewName= view.findViewById(R.id.textViewName);
        textViewName.setText(contacts.get(i).getLastname()+" "+ contacts.get(i).getFirstname());

        TextView textViewEmail= view.findViewById(R.id.textViewEmail);
        textViewEmail.setText(contacts.get(i).getEmail());

        TextView textViewPhone= view.findViewById(R.id.textViewPhone);
        textViewPhone.setText(contacts.get(i).getPhone());

        return view;
    }
}
