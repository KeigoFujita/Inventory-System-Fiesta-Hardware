package com.hardware.fiesta.Database;

import com.hardware.fiesta.Model.Account;
import com.hardware.fiesta.Model.Employee;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeesDatabaseConnector {


    private static EmployeesDatabaseConnector instance = null;

    //Location of the Database
    private static final String desktopLocation = "jdbc:sqlite:"+System.getProperty("user.home") + "\\Desktop\\";

    //Name of the database
    private static final String DATABASE_NAME = "Employees.db";

    //Connection string used for database Connection
    private static final String CONNECTION_STRING = desktopLocation + DATABASE_NAME;

    //Table Names
    private static final String TABLE_EMPLOYEES = "Employees";
    private static final String TABLE_ACCOUNTS = "Accounts";

    //ACCOUNTS COLUMN NAMES

    private static final String ACCOUNT_ID = "account_id";
    private static final String ACCOUNT_EMPLOYEE_ID = "employee_id";
    private static final String ACCOUNT_USERNAME = "account_username";
    private static final String ACCOUNT_PASSWORD = "account_password";
    private static final String ACCOUNT_TYPE = "account_type";
    private static final String ACCOUNT_LAST_LOGIN = "account_last_login";
    private static final String ACCOUNT_STATUS = "account_status";

    //EMPLOYEES COLUMN NAME

    private static final String EMPLOYEE_ID = "employee_id";
    private static final String EMPLOYEE_FIRST_NAME = "employee_first_name";
    private static final String EMPLOYEE_MIDDLE_NAME = "employee_middle_name";
    private static final String EMPLOYEE_LAST_NAME = "employee_last_name";
    private static final String EMPLOYEE_BIRTHDATE = "employee_birth_date";
    private static final String EMPLOYEE_ADDRESS = "employee_address";
    private static final String EMPLOYEE_CONTACT_NUMBER = "employee_contact_number";
    private static final String EMPLOYEE_EMAIL = "employee_email";
    private static final String EMPLOYEE_STATUS = "employee_status";
    private static final String EMPLOYEE_LAST_UPDATED = "employee_last_updated";



    //SET PRAGMA KEYS ON
    private static final String SET_PRAGMA_KEYS_ON =
            "PRAGMA foreign_keys = ON ";


    //CREATE ACCOUNTS TABLE

    private static final String CREATE_TABLE_ACCOUNTS =
            "CREATE TABLE IF NOT EXISTS "+TABLE_ACCOUNTS +
         " ("+ACCOUNT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
             +ACCOUNT_EMPLOYEE_ID+" INTEGER NOT NULL, "
             +ACCOUNT_USERNAME   +" TEXT NOT NULL, "
             +ACCOUNT_PASSWORD   +" TEXT NOT NULL, "
             +ACCOUNT_TYPE       +" TEXT NOT NULL, "
             +ACCOUNT_LAST_LOGIN +" TEXT NOT NULL, "
             +ACCOUNT_STATUS     +" TEXT NOT NULL DEFAULT ENABLED, "
             +"FOREIGN KEY ("+ACCOUNT_EMPLOYEE_ID+") REFERENCES "+TABLE_EMPLOYEES+"("+EMPLOYEE_ID+")"
             + ")";

    //CREATE EMPLOYEE TABLE

    private static final String CREATE_TABLE_EMPLOYEES =
            "CREATE TABLE IF NOT EXISTS "+TABLE_EMPLOYEES +
                    " ("+EMPLOYEE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    +EMPLOYEE_FIRST_NAME           +" TEXT NOT NULL, "
                    +EMPLOYEE_MIDDLE_NAME          +" TEXT NOT NULL, "
                    +EMPLOYEE_LAST_NAME            +" TEXT NOT NULL, "
                    +EMPLOYEE_BIRTHDATE            +" TEXT NOT NULL, "
                    +EMPLOYEE_ADDRESS              +" TEXT NOT NULL, "
                    +EMPLOYEE_CONTACT_NUMBER       +" TEXT NOT NULL, "
                    +EMPLOYEE_EMAIL                +" TEXT NOT NULL, "
                    +EMPLOYEE_STATUS               +" TEXT NOT NULL DEFAULT ENABLED, "
                    +EMPLOYEE_LAST_UPDATED         +" TEXT NOT NULL"
                    +")";

    //STATEMENT FOR ADDING EMPLOYEE

    private static final String ADD_EMPLOYEE_STATEMENT =
            "INSERT INTO "+TABLE_EMPLOYEES+
             " ("+EMPLOYEE_FIRST_NAME      +", "
                 +EMPLOYEE_MIDDLE_NAME     +", "
                 +EMPLOYEE_LAST_NAME       +", "
                 +EMPLOYEE_BIRTHDATE       +", "
                 +EMPLOYEE_ADDRESS         +", "
                 +EMPLOYEE_CONTACT_NUMBER  +", "
                 +EMPLOYEE_EMAIL           +", "
                 +EMPLOYEE_LAST_UPDATED
                 +") VALUES "
                 +"(?,?,?,?,?,?,?,?)";

    //STATEMENT FOR UPDATING EMPLOYEE

    private static final String UPDATE_EMPLOYEE_STATEMENT =
            "UPDATE " + TABLE_EMPLOYEES + " SET "
            +EMPLOYEE_FIRST_NAME +" = ? ,"
            +EMPLOYEE_MIDDLE_NAME+" = ? ,"
            +EMPLOYEE_LAST_NAME  +" = ? ,"
            +EMPLOYEE_CONTACT_NUMBER+" = ? ,"
            +EMPLOYEE_ADDRESS +" = ? ,"
            +EMPLOYEE_EMAIL+" = ? ,"
            +EMPLOYEE_BIRTHDATE+" = ? ,"
            +EMPLOYEE_LAST_UPDATED+" = ? WHERE "
            +EMPLOYEE_ID +" = ?";



    // STATEMENT FOR DISABLING EMPLOYEE

    private static final String DISABLE_EMPLOYEE_STATEMENT =
            "UPDATE "+TABLE_EMPLOYEES+ " SET "
            +EMPLOYEE_STATUS +" = 'DISABLED'"
            +"WHERE "+EMPLOYEE_ID +" = ?";

    //STATEMENT FOR ENABLING EMPLOYEE

    private static final String ENABLE_EMPLOYEE_STATEMENT =
            "UPDATE "+TABLE_EMPLOYEES+ " SET "
                    +EMPLOYEE_STATUS +" = 'ENABLED'"
                    +"WHERE "+EMPLOYEE_ID +" = ?";

    //STATEMENT FOR ADDING ACCOUNTS
    private static final String ADD_ACCOUNT_STATEMENT =
            "INSERT INTO "+TABLE_ACCOUNTS+
            "("+ACCOUNT_EMPLOYEE_ID+","
               +ACCOUNT_USERNAME   +","
               +ACCOUNT_PASSWORD   +","
               +ACCOUNT_TYPE       +","
               +ACCOUNT_LAST_LOGIN +") VALUES "
               +"(?,?,?,?,?)";

    // STATEMENT FOR SEARCHING ACCOUNTS GIVING EMPLOYEE ID
    private static final String SEARCH_ACCOUNT_BY_EMP_ID =
            "SELECT * FROM "+ TABLE_ACCOUNTS+
            " WHERE "+ACCOUNT_EMPLOYEE_ID +
            " = ? ";

    // STATEMENT FOR UPDATING ACCOUNTS GIVEN ITEM ID
    private static final String UPDATE_ACOUNT_STATEMENT =
            "UPDATE "+ TABLE_ACCOUNTS +
           " SET "+ ACCOUNT_USERNAME + " = ?, "
                  + ACCOUNT_PASSWORD + " = ?, "
                  + ACCOUNT_TYPE     + " = ?, "
                  + ACCOUNT_LAST_LOGIN+" = ? " +
           " WHERE "+ACCOUNT_ID+" = ?";

    //CONNECTION TOOLS
    private Connection conn;
    private PreparedStatement prep;
    private ResultSet resultSet;
    private Statement statement;




    public static EmployeesDatabaseConnector getInstance() {
        if(instance == null) {
            instance = new EmployeesDatabaseConnector();
        }
        return instance;
    }

    public void openConnection() {

        try {

            conn = DriverManager.getConnection(CONNECTION_STRING);
            System.out.println("Employees Database opened Succesfully");
            setPragmakeysOn();
            statement = conn.createStatement();
            statement.execute(CREATE_TABLE_ACCOUNTS);
            statement.execute(CREATE_TABLE_EMPLOYEES);
            conn.setAutoCommit(false);
            statement.close();

        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void closeConnection(){


        try{

            conn.close();

            System.out.println("Employees Database Closed Succesfully");

        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();
            System.out.println("Could not close database!!!!!");


        }


    }

    private void setPragmakeysOn(){

        try{


            statement = conn.createStatement();
            statement.execute(SET_PRAGMA_KEYS_ON);
            statement.close();
        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();

        }


    }

    public boolean addEmployee(Employee employee){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();


        if(!searchEmployee(employee)){

            if(employee.getFirstName()     == null ||
               employee.getMiddleName()    == null ||
               employee.getLastName()      == null ||
               employee.getBirthDate()     == null ||
               employee.getAddress()       == null ||
               employee.getEmailAddress()  == null ||
               employee.getContactNumber() == null ){

               return false;

            }else if(employee.getFirstName().trim().equals("") ||
                    employee.getMiddleName().trim().equals("")||
                    employee.getLastName() .trim().equals("")||
                    employee.getBirthDate() .trim().equals("")||
                    employee.getAddress() .trim().equals("")||
                    employee.getEmailAddress().trim().equals("")||
                    employee.getContactNumber().trim().equals("")){

                return false;
            }

            try {
                prep = conn.prepareStatement(ADD_EMPLOYEE_STATEMENT);
                prep.setString(1, employee.getFirstName());
                prep.setString(2,employee.getMiddleName());
                prep.setString(3,employee.getLastName());
                prep.setString(4,employee.getBirthDate());
                prep.setString(5,employee.getAddress());
                prep.setString(6,employee.getContactNumber());
                prep.setString(7,employee.getEmailAddress());
                prep.setString(8,dateFormat.format(date));

                prep.execute();
                conn.commit();
                prep.close();

                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    public boolean searchEmployee(Employee employee){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_EMPLOYEES);

            while(resultSet.next()){

                if(resultSet.getString(EMPLOYEE_FIRST_NAME).equals(employee.getFirstName().trim())
                && resultSet.getString(EMPLOYEE_LAST_NAME).equals(employee.getLastName().trim())
                && resultSet.getString(EMPLOYEE_MIDDLE_NAME).equals(employee.getMiddleName().trim())){

                    return true;
                }

            }

            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();

            return false;
        }

        return false;
    }

    public boolean updateEmployee(Employee oldEmployee, Employee newEmployee) {


        Date date = new Date();

        try {
            prep = conn.prepareStatement(UPDATE_EMPLOYEE_STATEMENT);
            prep.setString(1, newEmployee.getFirstName());
            prep.setString(2, newEmployee.getMiddleName());
            prep.setString(3, newEmployee.getLastName());
            prep.setString(4, newEmployee.getContactNumber());
            prep.setString(5, newEmployee.getAddress());
            prep.setString(6, newEmployee.getEmailAddress());
            prep.setString(7, newEmployee.getBirthDate());
            prep.setString(8, date.toString());
            prep.setInt(9, getEmployeeId(oldEmployee));


            prep.execute();
            conn.commit();
            prep.close();

            return true;
        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }

    }


    public boolean disableEmployee(int employee_id){

        try {
            prep = conn.prepareStatement(DISABLE_EMPLOYEE_STATEMENT);
            prep.setInt(1, employee_id);

            prep.execute();
            conn.commit();
            prep.close();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

    }

    public boolean enableEmployee(int employee_id){

        try {
            prep = conn.prepareStatement(ENABLE_EMPLOYEE_STATEMENT);
            prep.setInt(1, employee_id);

            prep.execute();
            conn.commit();
            prep.close();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

    }

    public int getEmployeeId(Employee employee){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_EMPLOYEES);

            while (resultSet.next()){

                if(resultSet.getString(EMPLOYEE_FIRST_NAME).equals(employee.getFirstName()) &&
                   resultSet.getString(EMPLOYEE_MIDDLE_NAME).equals(employee.getMiddleName())&&
                   resultSet.getString(EMPLOYEE_LAST_NAME).equals(employee.getLastName())){

                    return resultSet.getInt(EMPLOYEE_ID);

                }


            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();

            return -1;
        }

        return -1;

    }

    public Employee getEmployee(int index){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_EMPLOYEES);


            while(resultSet.next()){

                if(resultSet.getInt(EMPLOYEE_ID) == index){

                    return new Employee(
                            resultSet.getInt(EMPLOYEE_ID),
                            resultSet.getString(EMPLOYEE_FIRST_NAME),
                            resultSet.getString(EMPLOYEE_MIDDLE_NAME),
                            resultSet.getString(EMPLOYEE_LAST_NAME),
                            resultSet.getString(EMPLOYEE_ADDRESS),
                            resultSet.getString(EMPLOYEE_CONTACT_NUMBER),
                            resultSet.getString(EMPLOYEE_BIRTHDATE),
                            resultSet.getString(EMPLOYEE_EMAIL),
                            resultSet.getString(EMPLOYEE_STATUS),
                            resultSet.getString(EMPLOYEE_LAST_UPDATED)
                    );

                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Employee> getEmployeeList(boolean isEnabled){

        ArrayList<Employee> employees= new ArrayList<Employee>();
        Employee employee;

        try{
            statement = conn.createStatement();
            if(isEnabled){
                resultSet = statement.executeQuery("SELECT * FROM "+TABLE_EMPLOYEES+" WHERE "+EMPLOYEE_STATUS+" = 'ENABLED'");
            }else {
                resultSet = statement.executeQuery("SELECT * FROM "+TABLE_EMPLOYEES+" WHERE "+EMPLOYEE_STATUS+" = 'DISABLED'");
            }
            while (resultSet.next()){

                employee = new Employee(
                        resultSet.getInt(EMPLOYEE_ID),
                        resultSet.getString(EMPLOYEE_FIRST_NAME),
                        resultSet.getString(EMPLOYEE_MIDDLE_NAME),
                        resultSet.getString(EMPLOYEE_LAST_NAME),
                        resultSet.getString(EMPLOYEE_ADDRESS),
                        resultSet.getString(EMPLOYEE_CONTACT_NUMBER),
                        resultSet.getString(EMPLOYEE_BIRTHDATE),
                        resultSet.getString(EMPLOYEE_EMAIL),
                        resultSet.getString(EMPLOYEE_STATUS),
                        resultSet.getString(EMPLOYEE_LAST_UPDATED)
                );

                employees.add(employee);
            }

            statement.close();
            resultSet.close();

            return employees;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();


        }



        return null;

    }
    public ArrayList<Employee> getEmployeeList(){

        ArrayList<Employee> employees= new ArrayList<Employee>();
        Employee employee;

        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_EMPLOYEES);

            while (resultSet.next()){

                employee = new Employee(
                        resultSet.getInt(EMPLOYEE_ID),
                        resultSet.getString(EMPLOYEE_FIRST_NAME),
                        resultSet.getString(EMPLOYEE_MIDDLE_NAME),
                        resultSet.getString(EMPLOYEE_LAST_NAME),
                        resultSet.getString(EMPLOYEE_ADDRESS),
                        resultSet.getString(EMPLOYEE_CONTACT_NUMBER),
                        resultSet.getString(EMPLOYEE_BIRTHDATE),
                        resultSet.getString(EMPLOYEE_EMAIL),
                        resultSet.getString(EMPLOYEE_STATUS),
                        resultSet.getString(EMPLOYEE_LAST_UPDATED)
                );

                employees.add(employee);
            }

            statement.close();
            resultSet.close();

            return employees;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();


        }



        return null;

    }



    public boolean addAccount(Account account, int employeeId){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        try {
            prep = conn.prepareStatement(ADD_ACCOUNT_STATEMENT);


            prep.setInt(1,employeeId );
            prep.setString(2, account.getUsername());
            prep.setString(3, account.getPassword());
            prep.setString(4, account.getType());
            prep.setString(5,dateFormat.format(date));

            prep.execute();
            conn.commit();
            System.out.println("Added data");
            prep.close();

            return true;

        }catch(SQLException e){

            e.getMessage();
            e.printStackTrace();
            System.out.println("Could not add account");

            return false;
        }

    }

    public int searchAccount(String username, String password){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT "+ACCOUNT_ID +" , "+ ACCOUNT_USERNAME+" , "+ACCOUNT_PASSWORD+" FROM "+TABLE_ACCOUNTS);

            while(resultSet.next()){

               if(username.equals(resultSet.getString(ACCOUNT_USERNAME)) && password.equals(resultSet.getString(ACCOUNT_PASSWORD))) {

                   return resultSet.getInt(ACCOUNT_ID);

               }

            }

            return -1;

        } catch (SQLException e) {
            e.printStackTrace();

            return -1;
        }


    }

    public Account searchAccount(int accountID){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_ACCOUNTS+" WHERE "+ACCOUNT_ID+" = "+accountID);

            Account account;

            while(resultSet.next()){

                account = new Account(

                        resultSet.getInt(ACCOUNT_ID),
                        resultSet.getInt(ACCOUNT_EMPLOYEE_ID),
                        resultSet.getString(ACCOUNT_USERNAME),
                        resultSet.getString(ACCOUNT_PASSWORD),
                        resultSet.getString(ACCOUNT_TYPE),
                        resultSet.getString(ACCOUNT_LAST_LOGIN),
                        resultSet.getString(ACCOUNT_STATUS)

                );
                return account;

            }

            return null;

        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }


    }




    public boolean updateAccount(Account oldAccount, Account newAccount){

        Date date = new Date();

        try {
            prep = conn.prepareStatement(UPDATE_ACOUNT_STATEMENT);

            prep.setString(1, newAccount.getUsername());
            prep.setString(2, newAccount.getPassword());
            prep.setString(3, newAccount.getType());
            prep.setString(4, date.toString());
            prep.setInt(5, oldAccount.getId());

            prep.execute();
            conn.commit();
            prep.close();

            return true;
        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }





    }

    public ArrayList<Account> getAccountList(boolean isDecrypted){

        ArrayList<Account> accounts= new ArrayList<Account>();
        Account account = new Account();

        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_ACCOUNTS);

            while (resultSet.next()){

                if(isDecrypted){

                    account = new Account(
                            resultSet.getInt(ACCOUNT_ID),
                            resultSet.getInt(ACCOUNT_EMPLOYEE_ID),
                            resultSet.getString(ACCOUNT_USERNAME),
                            account.decryptPassword(resultSet.getString(ACCOUNT_PASSWORD)),
                            resultSet.getString(ACCOUNT_TYPE),
                            resultSet.getString(ACCOUNT_LAST_LOGIN),
                            resultSet.getString(ACCOUNT_STATUS)
                    );
                    accounts.add(account);

                }else{

                    account = new Account(
                            resultSet.getInt(ACCOUNT_ID),
                            resultSet.getInt(ACCOUNT_EMPLOYEE_ID),
                            resultSet.getString(ACCOUNT_USERNAME),
                            resultSet.getString(ACCOUNT_PASSWORD),
                            resultSet.getString(ACCOUNT_TYPE),
                            resultSet.getString(ACCOUNT_LAST_LOGIN),
                            resultSet.getString(ACCOUNT_STATUS)
                    );

                    accounts.add(account);


                }

            }

            statement.close();
            resultSet.close();

            return accounts;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();


        }



        return null;






    }

    public ArrayList<Account> getAccountList(int id, boolean isDecrypted){


        ArrayList<Account> accounts= new ArrayList();
        Account account = new Account();

        try{
            statement = conn.createStatement();
            prep = conn.prepareStatement(SEARCH_ACCOUNT_BY_EMP_ID);
            prep.setInt(1, id);
            resultSet = prep.executeQuery();

            while (resultSet.next()){



                if(isDecrypted){
                    account = new Account(
                            resultSet.getInt(ACCOUNT_ID),
                            resultSet.getInt(ACCOUNT_EMPLOYEE_ID),
                            resultSet.getString(ACCOUNT_USERNAME),
                            account.decryptPassword(resultSet.getString(ACCOUNT_PASSWORD)),
                            resultSet.getString(ACCOUNT_TYPE),
                            resultSet.getString(ACCOUNT_LAST_LOGIN),
                            resultSet.getString(ACCOUNT_STATUS)
                    );

                    accounts.add(account);

                }else{
                    account = new Account(
                            resultSet.getInt(ACCOUNT_ID),
                            resultSet.getInt(ACCOUNT_EMPLOYEE_ID),
                            resultSet.getString(ACCOUNT_USERNAME),
                            resultSet.getString(ACCOUNT_PASSWORD),
                            resultSet.getString(ACCOUNT_TYPE),
                            resultSet.getString(ACCOUNT_LAST_LOGIN),
                            resultSet.getString(ACCOUNT_STATUS)
                    );

                    accounts.add(account);
                }



            }

            statement.close();
            prep.close();
            resultSet.close();

            return accounts;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();


        }



        return null;






    }





}
