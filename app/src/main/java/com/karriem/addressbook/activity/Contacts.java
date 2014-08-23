package com.karriem.addressbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.karriem.addressbook.R;
import com.karriem.addressbook.domain.Contact;
import com.karriem.addressbook.repository.DatasourceDAO;
import com.karriem.addressbook.repository.impl.DatasourceDAOImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karriem on 8/20/14.
 */
public class Contacts extends Activity {

    List<Contact> listCon = new ArrayList<Contact>();
    ListView list;

    private DatasourceDAO dao = new DatasourceDAOImpl(this);

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        private  String[] web;
        private  String[] imageId;
        public CustomList(Activity context,
                          String[] web, String[] imageId) {
            super(context, R.layout.list_single, web);
            this.context = context;
            this.web = web;
            this.imageId = imageId;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.list_single, null, true);

            TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
            TextView imageView = (TextView) rowView.findViewById(R.id.img);

            Button btn = (Button) rowView.findViewById(R.id.moreBtn2);

            txtTitle.setText(web[position]);
            imageView.setText(imageId[position]);

            btn.setTag(imageView.getText().toString());

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent next = new Intent(getApplicationContext(), ViewContacts.class);

                    String value = (String)v.getTag();

                    next.putExtra("list", value);
                    startActivity(next);
                }
            });

            return rowView;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);

        listCon = dao.getContactList();

        String[] item = new String[listCon.size()];
        String[] subItem = new String[listCon.size()];

        listItems(item);
        subItems(subItem);

        CustomList adapter = new
                CustomList(this, subItem, item);
        list=(ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);

        Button back = (Button)findViewById(R.id.bckBtnC);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        registerForContextMenu(list);

        //Old List view  ---------------------------------------------------------------

        //ListView list = (ListView)findViewById(R.id.listView);

        //simpleAdapter = new SimpleAdapter(this, items, android.R.layout.simple_list_item_1,
          //      new String[] {"LastName"}, new int[] {android.R.id.text1});
        //list.setAdapter(simpleAdapter);

       // list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          //  @Override
          //  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           //     Intent next = new Intent(getApplicationContext(), ViewContacts.class);
            //    TextView clickedView = (TextView) view;

            //    String a = clickedView.getText().toString();
            //    int count = 0;

             //   for (Contact con : listCon){

             //       if(con.getLastName().equalsIgnoreCase(a)){
            //            System.out.println("Record Found");
            //            break;
            //        }
            //        count++;
           //     }

           //     next.putExtra("list", count);
           //     startActivity(next);
          //  }
        //});

        //old list view end ============================================================
    }

    //method creates list
    private void listItems(String[] item){
        int count = 0;

        if(listCon.isEmpty()){
            System.out.println("List Empty");
        }

        for(Contact con : listCon){

            item[count] = con.getLastName();
            count++;
        }
    }
    //method calls list
    //private HashMap<String, String> createItems(String key, String name) {

       // HashMap<String, String> itemsList = new HashMap<String, String>();

       // itemsList.put(key, name);
       // return itemsList;
   // }

    //Adds sub-Items
    private void subItems(String[] subItem){
        int count = 0;

        for(Contact con : listCon){

            subItem[count] = con.getCellPhoneNumber();
            count++;
        }
    }
}