package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ListView listViewContacts;
    Button buttonAdd;

    ArrayList<ContactModel> contacts= new ArrayList<ContactModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        listViewContacts= (ListView) findViewById(R.id.listViewContacts);
        ContactAdapter adapter= new ContactAdapter(this, contacts);
        listViewContacts.setAdapter(adapter);
        listViewContacts.setOnItemClickListener(this);

        buttonAdd= (Button) findViewById(R.id.buttonAddContact);
        buttonAdd.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ContactModel selectedContact= (ContactModel) getIntent().getSerializableExtra("selectedContact");
        ContactModel dataContact= (ContactModel) getIntent().getSerializableExtra("dataContact");
        if(selectedContact!=null){
            contacts.remove(selectedContact);
        }
        if(dataContact!=null && !contacts.contains(dataContact)) {
            contacts.add(dataContact);
            ((BaseAdapter) listViewContacts.getAdapter()).notifyDataSetChanged();
        }
        saveData();
    }

    @Override
    public void onClick(View view) {
        Intent intent= new Intent(this, FormContactActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ContactModel contact= contacts.get(i);
        Intent intent= new Intent(this, FormContactActivity.class);
        intent.putExtra("selectedContact", contact);
        startActivity(intent);
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // creating a new variable for gson.
        Gson gson = new Gson();
        // getting data from gson and storing it in a string.
        String json = gson.toJson(contacts);
        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("contacts", json);
        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        // creating a variable for gson.
        Gson gson = new Gson();
        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("contacts", null);
        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<ContactModel>>() {
        }.getType();
        // in below line we are getting data from gson
        // and saving it to our array list
        contacts = gson.fromJson(json, type);
        // checking below if the array list is empty or not
        if (contacts == null) {
            // if the array list is empty
            // creating a new array list.
            contacts = new ArrayList<>();
            contacts.add(new ContactModel("Joan", "FranÃ§ois", "j.francois@cfa-insta.fr", "0633763367"));
            contacts.add(new ContactModel("Mounira", "Coste", "m.coste@cfa-insta.fr", "0148474849"));
            contacts.add(new ContactModel("Selin", "Sert", "s.sert@cfa-insta.fr", "0148474852"));
        }
    }
}