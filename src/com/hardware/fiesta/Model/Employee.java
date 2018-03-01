package com.hardware.fiesta.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {

   private SimpleIntegerProperty id;
   private SimpleStringProperty  firstName;
   private SimpleStringProperty  middleName;
   private SimpleStringProperty  lastName;
   private SimpleStringProperty  address;
   private SimpleStringProperty  contactNumber;
   private SimpleStringProperty  birthDate;
   private SimpleStringProperty emailAddress;
   private SimpleStringProperty  status;
   private SimpleStringProperty  lastUpdated;



    public Employee(int id, String firstName, String middleName, String lastName, String address, String contactNumber, String birthDate, String emailAddress,String status, String lastUpdated){
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.middleName = new SimpleStringProperty(middleName);
        this.lastName = new SimpleStringProperty(lastName);
        this.address =new SimpleStringProperty(address);
        this.contactNumber = new SimpleStringProperty(contactNumber);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.emailAddress = new SimpleStringProperty(emailAddress);
        this.status = new SimpleStringProperty(status);
        this.lastUpdated = new SimpleStringProperty(lastUpdated);
    }


    public Employee(String firstName, String middleName, String lastName, String address, String contactNumber, String birthDate, String emailAddress){
        this.firstName = new SimpleStringProperty(firstName);
        this.middleName = new SimpleStringProperty(middleName);
        this.lastName = new SimpleStringProperty(lastName);
        this.address =new SimpleStringProperty(address);
        this.contactNumber = new SimpleStringProperty(contactNumber);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.emailAddress = new SimpleStringProperty(emailAddress);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public String getBirthDate() {
        return birthDate.get();
    }

    public String getEmailAddress() {
        return emailAddress.get();
    }

    public int getId() {
        return id.get();
    }

    public String getStatus() {
        return status.get();
    }

    public String getLastUpdated() {
        return lastUpdated.get();
    }

}
