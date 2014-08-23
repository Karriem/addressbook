package com.karriem.addressbook.repository;




import com.karriem.addressbook.domain.Contact;

import java.util.List;

/**
 * Created by karriem on 8/19/14.
 */
public interface DatasourceDAO {

    public void createContact(Contact contact);

    public void deleteDB();

    public List<Contact> getContactList();

    public String searchLastName(String value);
}
