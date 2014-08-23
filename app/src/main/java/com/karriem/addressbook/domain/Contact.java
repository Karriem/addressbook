package com.karriem.addressbook.domain;

/**
 * Created by karriem on 8/19/14.
 */
public class Contact {

    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String cellPhoneNumber;
    private String homeAddress;

    public Contact() {
    }

    public Contact(Builder build) {
        id = build.id;
        firstName = build.firstName;
        lastName = build.lastName;
        emailAddress = build.emailAddress;
        cellPhoneNumber = build.cellPhoneNumber;
        homeAddress = build.homeAddress;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public static class Builder {

        private int id;
        private String firstName;
        private String lastName;
        private String emailAddress;
        private String cellPhoneNumber;
        private String homeAddress;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder cellPhoneNumber(String cellPhoneNumber) {
            this.cellPhoneNumber = cellPhoneNumber;
            return this;
        }

        public Builder homeAddress(String homeAddress) {
            this.homeAddress = homeAddress;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }

        public Builder contact(Contact con) {
            this.id = con.getId();
            this.firstName = con.getFirstName();
            this.lastName = con.getLastName();
            this.emailAddress = con.getEmailAddress();
            this.cellPhoneNumber = con.getCellPhoneNumber();
            this.homeAddress = con.getHomeAddress();

            return this;
        }
    }
}
