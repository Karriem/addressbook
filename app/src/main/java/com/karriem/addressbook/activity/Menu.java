package com.karriem.addressbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.karriem.addressbook.R;
import com.karriem.addressbook.repository.DatasourceDAO;
import com.karriem.addressbook.repository.impl.DatasourceDAOImpl;

/**
 * Created by karriem on 8/19/14.
 */
public class Menu extends Activity {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        final DatasourceDAO dao = new DatasourceDAOImpl(this);

        Button create = (Button) findViewById(R.id.createBtn);
        Button view = (Button) findViewById(R.id.viewBtn);
        //Button clearDB = (Button) findViewById(R.id.clearDB);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), CreateContact.class);
                startActivity(next);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Contacts.class);
                startActivity(intent);
            }
        });

        //clearDB.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  dao.deleteDB();
            //}
        //});
    }
}