package com.example.contactsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FormContactActivity extends AppCompatActivity implements View.OnClickListener {


    EditText editTextFirstName, editTextLastname, editTextEmail, editTextPhone;
    Button buttonSave, buttonCancel;

    ContactModel selectedContact;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formcontact);

        editTextFirstName= (EditText) findViewById(R.id.editTextFirstname);
        editTextLastname= (EditText) findViewById(R.id.editTextLastname);
        editTextEmail= (EditText)  findViewById(R.id.editTextEmail);
        editTextPhone= (EditText) findViewById(R.id.editTextPhone);

        buttonSave= (Button)  findViewById(R.id.buttonSaveContact);
        buttonSave.setOnClickListener(this);
        buttonCancel= (Button) findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button b= (Button) view;
        if(b.equals(buttonCancel)){
            this.onBackPressed();
        } else {
            ContactModel dataContact = new ContactModel(editTextFirstName.getText().toString(),
                    editTextLastname.getText().toString(),
                    editTextEmail.getText().toString(),
                    editTextPhone.getText().toString());
            Intent intent= new Intent(this, MainActivity.class);
            intent.putExtra("selectedContact", selectedContact);
            intent.putExtra("dataContact", dataContact);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ContactModel contact= (ContactModel) getIntent().getSerializableExtra("selectedContact");
        if(contact!= null){
            editTextLastname.setText((contact.getLastname()));
            editTextFirstName.setText((contact.getFirstname()));
            editTextEmail.setText((contact.getEmail()));
            editTextPhone.setText((contact.getPhone()));

        }
    }
}
