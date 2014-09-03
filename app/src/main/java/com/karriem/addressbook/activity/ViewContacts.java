package com.karriem.addressbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karriem.addressbook.R;
import com.karriem.addressbook.domain.Contact;
import com.karriem.addressbook.repository.DatasourceDAO;
import com.karriem.addressbook.repository.impl.DatasourceDAOImpl;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by karriem on 8/19/14.
 */
public class ViewContacts extends Activity {

    List<Contact> contactList = new ArrayList<Contact>();
    private String value;
    private int num = 0;
    int count = 0;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontacts);

        final EditText lastN = (EditText) findViewById(R.id.lastTxtV);
        final EditText cell = (EditText) findViewById(R.id.cellTxtV);
        final EditText name = (EditText) findViewById(R.id.nameTxtV);
        final EditText home = (EditText) findViewById(R.id.homeTxtV);
        final EditText email = (EditText) findViewById(R.id.emailTxtV);

        final Button back = (Button) findViewById(R.id.viewBckBBtn);
        final Button update = (Button) findViewById(R.id.btnUp);
        final Button delete = (Button) findViewById(R.id.btnDel);

        final DatasourceDAO dao = new DatasourceDAOImpl(this);

        contactList = dao.getContactList();

        Intent intent = getIntent();
        value = intent.getStringExtra("list");
        num = Integer.parseInt(value);

        for (Contact con : contactList){

           if(con.getId() == num){
               System.out.println("Record Found");
               System.out.println(con.getId());
               break;
           }
               count++;
        }

        lastN.setText(contactList.get(count).getLastName());
        cell.setText(contactList.get(count).getCellPhoneNumber());
        name.setText(contactList.get(count).getFirstName());
        home.setText(contactList.get(count).getHomeAddress());
        email.setText(contactList.get(count).getEmailAddress());


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backMenu = new Intent(getApplicationContext(), Menu.class);
                dao.delete(value);
                startActivity(backMenu);
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"First name field is empty, please fill in field", Toast.LENGTH_SHORT).show();
                }
                else
                if (lastN.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Last name field is empty, please fill in field", Toast.LENGTH_SHORT).show();
                }
                else
                if (home.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Home address field is empty, please fill in field", Toast.LENGTH_SHORT).show();
                }
                else
                if (cell.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Cell phone number field is empty, please fill in field", Toast.LENGTH_SHORT).show();
                }
                else
                if (email.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Email field is empty, please fill in field", Toast.LENGTH_SHORT).show();
                }
                else{
                    Contact conUp = new Contact.Builder()
                                    .id(num)
                                    .cellPhoneNumber(cell.getText().toString())
                                    .emailAddress(email.getText().toString())
                                    .firstName(name.getText().toString())
                                    .homeAddress(home.getText().toString())
                                    .lastName(lastN.getText().toString())
                                    .build();

                    Contact newCon = new Contact.Builder().contact(conUp).build();

                    dao.update(newCon);

                    Intent backMen = new Intent(getApplicationContext(), Menu.class);
                    startActivity(backMen);
                    finish();
                }
            }
        });



        //Search method-----------------------------------------------------------------------------
        //ok.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
            //    final String count = dao.searchLastName(search.getText().toString());

             //   contactList = dao.getContactList();

               // if (count != null) {
                 //   final int counter = Integer.parseInt(count);

                 //   lastN.setText(contactList.get(counter).getLastName());
                  //  cell.setText(contactList.get(counter).getCellPhoneNumber());

                  //  name.setText(" ");
                  //  home.setText(" ");
                   // email.setText(" ");

                   // more.setOnClickListener(new View.OnClickListener() {
                    //    @Override
                   //     public void onClick(View v) {
                    //        name.setText(contactList.get(counter).getFirstName());
                      //      home.setText(contactList.get(counter).getHomeAddress());
                      //      email.setText(contactList.get(counter).getEmailAddress());
                      //  }
                  //  });
              //  }
             //   else{
                 //   lastN.setText(" ");
                //    cell.setText(" ");
                //    name.setText(" ");
                //    home.setText(" ");
               //     email.setText(" ");
                //    search.setText("No Record found");
              //  }
           // }
        //});
        //-----------------------------------------------------------------------------------------
    }

   // public boolean Emptycheck(){

      //  if (cell.getText().toString().isEmpty()){
     //        Toast.makeText(getApplicationContext(),"Cell phone number field is empty, please fill in field", Toast.LENGTH_SHORT).show();
      //       return false;
        }
      //  else
      //  if (email.getText().toString().isEmpty()){
      //      Toast.makeText(getApplicationContext(),"Email field is empty, please fill in field", Toast.LENGTH_SHORT).show();
      ///      return false;
      //  }
      //  else
      //  if (name.getText().toString().isEmpty()){
     //       Toast.makeText(getApplicationContext(),"First name field is empty, please fill in field", Toast.LENGTH_SHORT).show();
     //       return false;
     //   }
     //   else
     //   if (home.getText().toString().isEmpty()){
     //           Toast.makeText(getApplicationContext(),"Home address field is empty, please fill in field", Toast.LENGTH_SHORT).show();
     //           return false;
     //   }
     ///   else
      //  if (lastN.getText().toString().isEmpty()){
       //      Toast.makeText(getApplicationContext(),"Last name field is empty, please fill in field", Toast.LENGTH_SHORT).show();
      //       return false;
     //   }
    //    else
    //        return true;
  // }
