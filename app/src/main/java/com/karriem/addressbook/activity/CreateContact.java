package com.karriem.addressbook.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karriem.addressbook.R;
import com.karriem.addressbook.domain.Contact;
import com.karriem.addressbook.repository.DatasourceDAO;
import com.karriem.addressbook.repository.impl.DatasourceDAOImpl;


/**
 * Created by karriem on 8/19/14.
 */
public class CreateContact extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createcontact);

        final DatasourceDAO dao = new DatasourceDAOImpl(this);

        final EditText nameTxt = (EditText) findViewById(R.id.nameTxt);
        final EditText lastNTxt = (EditText) findViewById(R.id.surnametxt);
        final EditText homeTxt = (EditText) findViewById(R.id.homeTxt);
        final EditText cellTxt = (EditText) findViewById(R.id.cellTxt);
        final EditText emailTxt = (EditText) findViewById(R.id.emailTxt);

        Button create = (Button) findViewById(R.id.createBtn);
        final Button back = (Button) findViewById(R.id.createBackBtn);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameTxt.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"First name field is empty, please fill in field", Toast.LENGTH_SHORT).show();
                }
                else
                if (lastNTxt.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Last name field is empty, please fill in field", Toast.LENGTH_SHORT).show();
                }
                else
                if (homeTxt.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Home address field is empty, please fill in field", Toast.LENGTH_SHORT).show();
                }
                else
                if (cellTxt.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Cell phone number field is empty, please fill in field", Toast.LENGTH_SHORT).show();
                }
                else
                if (emailTxt.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Email field is empty, please fill in field", Toast.LENGTH_SHORT).show();
                }
                else {
                    Contact contact = new Contact.Builder()
                            .id(dao.getID())
                            .firstName(nameTxt.getText().toString())
                            .lastName(lastNTxt.getText().toString())
                            .homeAddress(homeTxt.getText().toString())
                            .cellPhoneNumber(cellTxt.getText().toString())
                            .emailAddress(emailTxt.getText().toString())
                            .build();
                    dao.createContact(contact);
                    finish();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}