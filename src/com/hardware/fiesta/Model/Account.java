package com.hardware.fiesta.Model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Account{

        private SimpleIntegerProperty id;
        private SimpleIntegerProperty empId;
        private SimpleStringProperty username;
        private SimpleStringProperty type;
        private SimpleStringProperty password;
        private SimpleStringProperty lastlogin;
        private SimpleStringProperty status;


    public void setPassword(String password) {
        this.password.set(encryptPassword(password.trim()));
    }

    public Account(String username, String password, String type){

            if(username.trim().isEmpty() && username.isEmpty() ||
               password.trim().isEmpty() && username.isEmpty() ||
               type.trim().isEmpty()     && type.isEmpty()){

                this.username = null;
                this.password = null;
                this.type     = null;

            }else{

                this.username = new SimpleStringProperty(username.trim());
                this.type     = new SimpleStringProperty(type.trim());
                this.password = new SimpleStringProperty(encryptPassword(password.trim()));

            }
        }
        public Account(){


        }

        public Account(String username, String password){

            if(username.trim().isEmpty() && username.isEmpty() ||
                    password.trim().isEmpty() && username.isEmpty()
                    ){

                this.username = null;
                this.password = null;
                this.type     = null;

            }else{

                this.username = new SimpleStringProperty(username.trim());
                this.password = new SimpleStringProperty(encryptPassword(password.trim()));

            }
        }

        public Account(int id, int empId, String username, String password, String type,String lastlogin, String status){

        this.id = new SimpleIntegerProperty(id);
        this.empId = new SimpleIntegerProperty(empId);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.type = new SimpleStringProperty(type);
        this.lastlogin = new SimpleStringProperty(lastlogin);
        this.status = new SimpleStringProperty(status);
        this.password = new SimpleStringProperty(password);

    }






        public String getUsername() {
            return username.get();
        }

        public String getType() {
            return type.get();
        }


        private String encryptPassword(String password){

            String encPassReversed = new StringBuilder(password).reverse().toString();
            StringBuilder encPass = new StringBuilder();

            for(int i = 0; i < encPassReversed.length(); i++){

                int intCh = encPassReversed.charAt(i) + 1;
                encPass.append((char) intCh);

            }

            return encPass.toString();
        }


    public String decryptPassword(String encryptedPassword){

            String decPassReversed = new StringBuilder(encryptedPassword).reverse().toString();
            StringBuilder decPass = new StringBuilder();

            for(int i = 0; i < decPassReversed.length(); i++){

                int intCh =decPassReversed.charAt(i) - 1;
                decPass.append((char) intCh);

            }

            return decPass.toString();

        }


    public int getId() {
        return id.get();
    }

    public String getStatus(){

         return status.get();
    }

    public String getLastlogin(){

        return lastlogin.get();
    }

    public int getEmpId(){

         return empId.get();
    }

    public String getPassword(){

         return password.get();
    }
}