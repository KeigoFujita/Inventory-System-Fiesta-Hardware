package com.hardware.fiesta.Database;

import com.hardware.fiesta.Model.StockBrandName;
import com.hardware.fiesta.Model.StockCategory;
import com.hardware.fiesta.Model.StockSupplier;
import com.hardware.fiesta.Model.StockType;

import java.sql.*;
import java.util.ArrayList;

public class StockDatabaseConnector {


    private static StockDatabaseConnector instance = null;

    //Location of the Database
    private static final String desktopLocation = "jdbc:sqlite:"+System.getProperty("user.home") + "\\Desktop\\";

    //Name of the database
    private static final String DATABASE_NAME = "Stocks.db";

    //Connection string used for database Connection
    private static final String CONNECTION_STRING = desktopLocation + DATABASE_NAME;

    //Table Names
    private static final String TABLE_STOCKS   = "Stocks";
    private static final String TABLE_CATEGORIES  = "Categories";
    private static final String TABLE_TYPES       = "Types";
    private static final String TABLE_SUPPLIERS   = "Suppliers";
    private static final String TABLE_BRAND_NAMES = "BrandNames";

    //STOCKS COLUMN NAMES

    private static final String ITEM_ID = "stock_id";
    private static final String ITEM_NAME = "item_name";
    private static final String ITEM_COST = "item_cost";
    private static final String ITEM_QTY = "item_qty";
    private static final String ITEM_BRAND_NAME = "item_brand_name";
    private static final String ITEM_TYPE = "item_type";
    private static final String ITEM_CATEGORY = "item_category";
    private static final String ITEM_SUPPLIER = "item_supplier";
    private static final String ITEM_BARCODE_ID = "item_barcode_id";
    private static final String ITEM_DESCRIPTION = "item_description";
    private static final String ITEM_STATUS = "item_status";
    private static final String ITEM_LAST_UPDATED = "item_last_updated";


    //CATEGORY COLUMN NAMES

    private static final String CATEGORY_ID = "category_id";
    private static final String CATEGORY_NAME = "category_name";
    private static final String CATEGORY_STATUS = "category_status";

    //TYPE COLUMN NAMES

    private static final String TYPE_ID = "type_id";
    private static final String TYPE_NAME = "type_name";
    private static final String TYPE_STATUS = "type_status";


    //BRAND NAME COLUMN NAMES

    private static final String BRAND_NAME_ID = "brand_id";
    private static final String BRAND_NAME_NAME = "brand_name";
    private static final String BRAND_NAME_STATUS = "brand_status";

    //CATEGORY COLUMN NAMES

    private static final String SUPPLIER_ID = "supplier_id";
    private static final String SUPPLIER_NAME = "supplier_name";
    private static final String SUPPLIER_ADDRESS = "supplier_address";
    private static final String SUPPLIER_CONTACT_NUMBER = "supplier_contact_number";
    private static final String SUPPLIER_EMAIL_ADDRESS = "supplier_email";
    private static final String SUPPLIER_STATUS = "supplier_status";






    //SET PRAGMA KEYS ON
    private static final String SET_PRAGMA_KEYS_ON =
            "PRAGMA foreign_keys = ON ";


    //CREATE ACCOUNTS TABLE

    private static final String CREATE_TABLE_STOCKS =
            "CREATE TABLE IF NOT EXISTS "+TABLE_STOCKS +
                    " ("+ITEM_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    +ITEM_NAME          +" TEXT NOT NULL, "
                    +ITEM_COST          +" REAL NOT NULL, "
                    +ITEM_QTY           +" INTEGER NOT NULL DEFAULT 0, "
                    +ITEM_BRAND_NAME    +" INTEGER NOT NULL REFERENCES " +TABLE_BRAND_NAMES   +"("+BRAND_NAME_ID+"), "
                    +ITEM_TYPE          +" INTEGER NOT NULL REFERENCES " +TABLE_TYPES         +"("+TYPE_ID+"), "
                    +ITEM_CATEGORY      +" INTEGER NOT NULL REFERENCES " +TABLE_CATEGORIES    +"("+CATEGORY_ID+"), "
                    +ITEM_BARCODE_ID    +" INTEGER NOT NULL, "
                    +ITEM_DESCRIPTION   +" TEXT, "
                    +ITEM_STATUS        +" TEXT NOT NULL DEFAULT ENABLED, "
                    +ITEM_LAST_UPDATED  +" TEXT NOT NULL, "
                    +ITEM_SUPPLIER      +" INTEGER NOT NULL REFERENCES " +TABLE_SUPPLIERS      +"("+SUPPLIER_ID+")"
                    + ")";



    //CREATE CATEGORY TABLE
    private static final String CREATE_TABLE_CATEGORY =
            "CREATE TABLE IF NOT EXISTS "+TABLE_CATEGORIES +
                       "  ("
                        +CATEGORY_ID             +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                        +CATEGORY_NAME           +" TEXT NOT NULL, "
                        +CATEGORY_STATUS         +" TEXT DEFAULT ENABLED "
                        +")";

    //CREATE TYPE TABLE
    private static final String CREATE_TABLE_TYPE =
            "CREATE TABLE IF NOT EXISTS "+TABLE_TYPES +
                    "  ("
                    +TYPE_ID             +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    +TYPE_NAME           +" TEXT NOT NULL, "
                    +TYPE_STATUS         +" TEXT NOT NULL DEFAULT ENABLED"
                    +")";



    private static final String CREATE_TABLE_BRAND_NAMES =
            "CREATE TABLE IF NOT EXISTS "+TABLE_BRAND_NAMES +
                    "  ("
                    +BRAND_NAME_ID             +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    +BRAND_NAME_NAME           +" TEXT NOT NULL, "
                    +BRAND_NAME_STATUS         +" TEXT NOT NULL DEFAULT ENABLED"
                    +")";




    //CREATE CATEGORY TABLE
    private static final String CREATE_TABLE_SUPPLIERS =
            "CREATE TABLE IF NOT EXISTS "+TABLE_SUPPLIERS +
                    "  ("
                    +SUPPLIER_ID             +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    +SUPPLIER_NAME           +" TEXT NOT NULL, "
                    +SUPPLIER_ADDRESS        +" TEXT NOT NULL, "
                    +SUPPLIER_CONTACT_NUMBER +" TEXT NOT NULL, "
                    +SUPPLIER_EMAIL_ADDRESS  +" TEXT NOT NULL, "
                    +SUPPLIER_STATUS         +" TEXT NOT NULL DEFAULT ENABLED"
                    +")";


   //STATEMENT FOR ADDING CATEGORY
    public static final String ADD_CATEGORY_STATEMENT  =
             "INSERT INTO "+TABLE_CATEGORIES
            +" ("+CATEGORY_NAME+")"
            +"VALUES (?)";

    //STATEMENT FOR ADDING TYPE
    public static final String ADD_TYPE_STATEMENT  =
            "INSERT INTO "+TABLE_TYPES
                    +" ("+TYPE_NAME+")"
                    +"VALUES (?)";


    //STATEMENT FOR ADDING BRAND NAME
    public static final String ADD_BRAND_STATEMENT  =
            "INSERT INTO "+TABLE_BRAND_NAMES
                    +" ("+BRAND_NAME_NAME+")"
                    +"VALUES (?)";


    //STATEMENT FOR ADDING SUPPLIER
    public static final String ADD_SUPPLIER_STATEMENT  =
            "INSERT INTO "+TABLE_SUPPLIERS
                    +" ( "
                    +SUPPLIER_NAME           +", "
                    +SUPPLIER_ADDRESS        +", "
                    +SUPPLIER_CONTACT_NUMBER +", "
                    +SUPPLIER_EMAIL_ADDRESS
                    +" ) "
                    +"VALUES (?,?,?,?)";


//    //STATEMENT FOR UPDATING EMPLOYEE
//
//    private static final String UPDATE_EMPLOYEE_STATEMENT =
//            "UPDATE " + TABLE_EMPLOYEES + " SET "
//                    +EMPLOYEE_FIRST_NAME +" = ? ,"
//                    +EMPLOYEE_MIDDLE_NAME+" = ? ,"
//                    +EMPLOYEE_LAST_NAME  +" = ? ,"
//                    +EMPLOYEE_CONTACT_NUMBER+" = ? ,"
//                    +EMPLOYEE_ADDRESS +" = ? ,"
//                    +EMPLOYEE_EMAIL+" = ? ,"
//                    +EMPLOYEE_BIRTHDATE+" = ? ,"
//                    +EMPLOYEE_LAST_UPDATED+" = ? WHERE "
//                    +EMPLOYEE_ID +" = ?";
//
//
//
//    // STATEMENT FOR DISABLING EMPLOYEE
//
//    private static final String DISABLE_EMPLOYEE_STATEMENT =
//            "UPDATE "+TABLE_EMPLOYEES+ " SET "
//                    +EMPLOYEE_STATUS +" = 'DISABLED'"
//                    +"WHERE "+EMPLOYEE_ID +" = ?";
//
//    //STATEMENT FOR ENABLING EMPLOYEE
//
//    private static final String ENABLE_EMPLOYEE_STATEMENT =
//            "UPDATE "+TABLE_EMPLOYEES+ " SET "
//                    +EMPLOYEE_STATUS +" = 'ENABLED'"
//                    +"WHERE "+EMPLOYEE_ID +" = ?";
//

    //CONNECTION TOOLS
    private Connection conn;
    private PreparedStatement prep;
    private ResultSet resultSet;
    private Statement statement;




    public static StockDatabaseConnector getInstance() {
        if(instance == null) {
            instance = new StockDatabaseConnector();
        }
        return instance;
    }

    public void openConnection() {

        try {

            conn = DriverManager.getConnection(CONNECTION_STRING);
            System.out.println("Stocks Database opened Succesfully");
            setPragmakeysOn();
            statement = conn.createStatement();
            statement.execute(CREATE_TABLE_STOCKS);
            statement.execute(CREATE_TABLE_CATEGORY);
            statement.execute(CREATE_TABLE_TYPE);
            statement.execute(CREATE_TABLE_BRAND_NAMES);
            statement.execute(CREATE_TABLE_SUPPLIERS);
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

    public boolean addStockCategory(StockCategory stockCategory){

        try {

            if(validateStockCategory(stockCategory)){
                return false;
            } else {
                prep = conn.prepareStatement(ADD_CATEGORY_STATEMENT);
                prep.setString(1, stockCategory.getCategoryName());

                prep.execute();
                conn.commit();
                prep.close();
                return true;
            }

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }
    public boolean addStockType(StockType stockType){

        try {

            if(validateStockType(stockType)){

                return false;
            }else{

                prep = conn.prepareStatement(ADD_TYPE_STATEMENT);
                prep.setString(1, stockType.getTypeName());


                prep.execute();
                conn.commit();
                prep.close();
                return true;
            }


        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }

    }
    public boolean addStockBrandName(StockBrandName stockBrandName){

        try {

            if(validateStockBrandName(stockBrandName)){
                return false;

            }else{

                prep = conn.prepareStatement(ADD_BRAND_STATEMENT);
                prep.setString(1, stockBrandName.getBrandName());

                prep.execute();
                conn.commit();
                prep.close();
                return true;
            }


        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }
    public boolean addStockSupplier(StockSupplier stockSupplier){

        try {

            if(validateStockSupplier(stockSupplier)){
                return false;
            }else{

                prep = conn.prepareStatement(ADD_SUPPLIER_STATEMENT);
                prep.setString(1, stockSupplier.getSupplierName());
                prep.setString(2, stockSupplier.getSupplierAddress());
                prep.setString(3, stockSupplier.getSupplierContactNumber());
                prep.setString(4, stockSupplier.getSupplierEmailAddress());

                prep.execute();
                conn.commit();
                prep.close();

                return true;

            }


        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }


    private boolean validateStockCategory(StockCategory stockCategory){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_CATEGORIES);

            while (resultSet.next()){

                if(resultSet.getString(CATEGORY_NAME).equals(stockCategory.getCategoryName())){

                    return true;
                }

            }

            resultSet.close();
            statement.close();

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }
    private boolean validateStockType(StockType stockType){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_TYPES);

            while (resultSet.next()){

                if(resultSet.getString(TYPE_NAME).equals(stockType.getTypeName())){

                    return true;
                }

            }

            resultSet.close();
            statement.close();

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }
    private boolean validateStockBrandName(StockBrandName stockBrandName){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_BRAND_NAMES);

            while (resultSet.next()){

                if(resultSet.getString(BRAND_NAME_NAME).equals(stockBrandName.getBrandName())){

                    return true;
                }

            }

            resultSet.close();
            statement.close();

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }
    private boolean validateStockSupplier(StockSupplier stockSupplier){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_SUPPLIERS);

            while (resultSet.next()){

                if(resultSet.getString(SUPPLIER_NAME).equals(stockSupplier.getSupplierName()) ||
                   resultSet.getString(SUPPLIER_CONTACT_NUMBER).equals(stockSupplier.getSupplierContactNumber())||
                   resultSet.getString(SUPPLIER_EMAIL_ADDRESS).equals(stockSupplier.getSupplierEmailAddress())){

                    return true;
                }

            }

            resultSet.close();
            statement.close();

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public StockCategory searchStockCategory(int categoryId){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_CATEGORIES
                                                  +" WHERE " + CATEGORY_ID + " = "+categoryId);

            int category_id = resultSet.getInt(CATEGORY_ID);
            String category_name = resultSet.getString(CATEGORY_NAME);
            String category_status = resultSet.getString(CATEGORY_STATUS);

            StockCategory stockCategory = new StockCategory(category_id,category_name,category_status);

            resultSet.close();
            statement.close();

            return stockCategory;

        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();
            return null;
        }

    }
    public StockType searchStockType(int typeId){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_TYPES
                    +" WHERE " + TYPE_ID+ " = "+typeId);

            int type_id = resultSet.getInt(TYPE_ID);
            String type_name = resultSet.getString(TYPE_NAME);
            String type_status = resultSet.getString(TYPE_STATUS);

            StockType stockType = new StockType(type_id,type_name,type_status);

            resultSet.close();
            statement.close();

            return stockType;

        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();
            return null;
        }

    }
    public StockBrandName searchStockBrandName(int brandName){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_CATEGORIES
                    +" WHERE " + BRAND_NAME_ID + " = "+brandName);

            int brand_id = resultSet.getInt(BRAND_NAME_ID);
            String brand_name = resultSet.getString(BRAND_NAME_NAME);
            String brand_status = resultSet.getString(BRAND_NAME_STATUS);

            StockBrandName stockBrandName = new StockBrandName(brand_id,brand_name,brand_status);

            resultSet.close();
            statement.close();

            return stockBrandName;

        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();
            return null;
        }

    }
    public StockSupplier searchStockSupplier(int supplierId){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_SUPPLIERS
                    +" WHERE " + SUPPLIER_ID + " = "+supplierId);

            int    supplier_id = resultSet.getInt(SUPPLIER_ID);
            String supplier_name = resultSet.getString(SUPPLIER_NAME);
            String supplier_address = resultSet.getString(SUPPLIER_ADDRESS);
            String supplier_contact = resultSet.getString(SUPPLIER_CONTACT_NUMBER);
            String supplier_email = resultSet.getString(SUPPLIER_EMAIL_ADDRESS);
            String supplier_status = resultSet.getString(SUPPLIER_STATUS);

            StockSupplier stockSupplier = new StockSupplier(supplier_id,supplier_name,supplier_address,supplier_contact,supplier_email,supplier_status);

            resultSet.close();
            statement.close();

            return stockSupplier;

        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<StockCategory> getStockCategoryList(boolean isEnabled){

        ArrayList<StockCategory> stockCategories = new ArrayList<>();
        StockCategory stockCategory;

        try{
            statement = conn.createStatement();
            if(isEnabled){
                resultSet = statement.executeQuery("SELECT * FROM "+TABLE_CATEGORIES+" WHERE "+CATEGORY_STATUS+" = 'ENABLED'");
            }else {
                resultSet = statement.executeQuery("SELECT * FROM "+TABLE_CATEGORIES+" WHERE "+CATEGORY_STATUS+" = 'DISABLED'");
            }
            while (resultSet.next()){

                stockCategory = new StockCategory(
                        resultSet.getInt(CATEGORY_ID),
                        resultSet.getString(CATEGORY_NAME),
                        resultSet.getString(CATEGORY_STATUS)

                );

              stockCategories.add(stockCategory);
            }

            statement.close();
            resultSet.close();

            return stockCategories;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();


        }



        return null;

    }
    public ArrayList<StockType> getStockTypeList(boolean isEnabled){

        ArrayList<StockType> stockTypes = new ArrayList<>();
        StockType stockType;

        try{
            statement = conn.createStatement();
            if(isEnabled){
                resultSet = statement.executeQuery("SELECT * FROM "+TABLE_TYPES+" WHERE "+TYPE_STATUS+" = 'ENABLED'");
            }else {
                resultSet = statement.executeQuery("SELECT * FROM "+TABLE_TYPES+" WHERE "+TYPE_STATUS+" = 'DISABLED'");
            }
            while (resultSet.next()){

                stockType = new StockType(
                        resultSet.getInt(TYPE_ID),
                        resultSet.getString(TYPE_NAME),
                        resultSet.getString(TYPE_STATUS)

                );

                stockTypes.add(stockType);
            }

            statement.close();
            resultSet.close();

            return stockTypes;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();


        }



        return null;

    }
    public ArrayList<StockBrandName> getStockBrandNameList(boolean isEnabled){

        ArrayList<StockBrandName> stockBrandNames = new ArrayList<>();
        StockBrandName stockBrandName;

        try{
            statement = conn.createStatement();
            if(isEnabled){
                resultSet = statement.executeQuery("SELECT * FROM "+TABLE_BRAND_NAMES+" WHERE "+BRAND_NAME_STATUS+" = 'ENABLED'");
            }else {
                resultSet = statement.executeQuery("SELECT * FROM "+TABLE_BRAND_NAMES+" WHERE "+BRAND_NAME_STATUS+" = 'DISABLED'");
            }
            while (resultSet.next()){

                stockBrandName = new StockBrandName(
                        resultSet.getInt(BRAND_NAME_ID),
                        resultSet.getString(BRAND_NAME_NAME),
                        resultSet.getString(BRAND_NAME_STATUS)

                );

                stockBrandNames.add(stockBrandName);
            }

            statement.close();
            resultSet.close();

            return stockBrandNames;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();


        }



        return null;

    }
    public ArrayList<StockSupplier> getStockSupplierList(boolean isEnabled){

        ArrayList<StockSupplier> stockBrandNames = new ArrayList<>();
        StockSupplier stockBrandName;

        try{
            statement = conn.createStatement();
            if(isEnabled){
                resultSet = statement.executeQuery("SELECT * FROM "+TABLE_SUPPLIERS+" WHERE "+SUPPLIER_STATUS+" = 'ENABLED'");
            }else {
                resultSet = statement.executeQuery("SELECT * FROM "+TABLE_SUPPLIERS+" WHERE "+SUPPLIER_STATUS+" = 'DISABLED'");
            }
            while (resultSet.next()){

                stockBrandName = new StockSupplier(
                        resultSet.getInt(SUPPLIER_ID),
                        resultSet.getString(SUPPLIER_NAME),
                        resultSet.getString(SUPPLIER_ADDRESS),
                        resultSet.getString(SUPPLIER_CONTACT_NUMBER),
                        resultSet.getString(SUPPLIER_EMAIL_ADDRESS),
                        resultSet.getString(SUPPLIER_STATUS)

                );

               stockBrandNames.add(stockBrandName);
            }

            statement.close();
            resultSet.close();

            return stockBrandNames;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();


        }



        return null;

    }

    public ArrayList<StockCategory> getStockCategoryList(){

        ArrayList<StockCategory> stockCategories = new ArrayList<>();
        StockCategory stockCategory;

        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_CATEGORIES);

            while (resultSet.next()){

                stockCategory = new StockCategory(
                        resultSet.getInt(CATEGORY_ID),
                        resultSet.getString(CATEGORY_NAME),
                        resultSet.getString(CATEGORY_STATUS)

                );

                stockCategories.add(stockCategory);
            }

            statement.close();
            resultSet.close();

            return stockCategories;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();


        }



        return null;

    }
    public ArrayList<StockType> getStockTypeList(){

        ArrayList<StockType> stockTypes = new ArrayList<>();
        StockType stockType;

        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_TYPES);

            while (resultSet.next()){

                stockType = new StockType(
                        resultSet.getInt(TYPE_ID),
                        resultSet.getString(TYPE_NAME),
                        resultSet.getString(TYPE_STATUS)

                );

                stockTypes.add(stockType);
            }

            statement.close();
            resultSet.close();

            return stockTypes;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();


        }



        return null;

    }
    public ArrayList<StockBrandName> getStockBrandNameList(){

        ArrayList<StockBrandName> stockBrandNames = new ArrayList<>();
        StockBrandName stockBrandName;

        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_BRAND_NAMES);

            while (resultSet.next()){

                stockBrandName = new StockBrandName(
                        resultSet.getInt(BRAND_NAME_ID),
                        resultSet.getString(BRAND_NAME_NAME),
                        resultSet.getString(BRAND_NAME_STATUS)

                );

                stockBrandNames.add(stockBrandName);
            }

            statement.close();
            resultSet.close();

            return stockBrandNames;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();


        }



        return null;

    }
    public ArrayList<StockSupplier> getStockSupplierList(){

        ArrayList<StockSupplier> stockBrandNames = new ArrayList<>();
        StockSupplier stockBrandName;

        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_SUPPLIERS);

            while (resultSet.next()){

                stockBrandName = new StockSupplier(
                        resultSet.getInt(SUPPLIER_ID),
                        resultSet.getString(SUPPLIER_NAME),
                        resultSet.getString(SUPPLIER_ADDRESS),
                        resultSet.getString(SUPPLIER_CONTACT_NUMBER),
                        resultSet.getString(SUPPLIER_EMAIL_ADDRESS),
                        resultSet.getString(SUPPLIER_STATUS)

                );

                stockBrandNames.add(stockBrandName);
            }

            statement.close();
            resultSet.close();

            return stockBrandNames;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();


        }



        return null;

    }











//    public boolean addEmployee(Employee employee){
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        java.util.Date date = new java.util.Date();
//
//
//        if(!searchEmployee(employee)){
//
//            if(employee.getFirstName()     == null ||
//                    employee.getMiddleName()    == null ||
//                    employee.getLastName()      == null ||
//                    employee.getBirthDate()     == null ||
//                    employee.getAddress()       == null ||
//                    employee.getEmailAddress()  == null ||
//                    employee.getContactNumber() == null ){
//
//                return false;
//
//            }else if(employee.getFirstName().trim().equals("") ||
//                    employee.getMiddleName().trim().equals("")||
//                    employee.getLastName() .trim().equals("")||
//                    employee.getBirthDate() .trim().equals("")||
//                    employee.getAddress() .trim().equals("")||
//                    employee.getEmailAddress().trim().equals("")||
//                    employee.getContactNumber().trim().equals("")){
//
//                return false;
//            }
//
//            try {
//                prep = conn.prepareStatement(ADD_EMPLOYEE_STATEMENT);
//                prep.setString(1, employee.getFirstName());
//                prep.setString(2,employee.getMiddleName());
//                prep.setString(3,employee.getLastName());
//                prep.setString(4,employee.getBirthDate());
//                prep.setString(5,employee.getAddress());
//                prep.setString(6,employee.getContactNumber());
//                prep.setString(7,employee.getEmailAddress());
//                prep.setString(8,dateFormat.format(date));
//
//                prep.execute();
//                conn.commit();
//                prep.close();
//
//                return true;
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        return false;
//    }
//
//    public boolean searchEmployee(Employee employee){
//
//        try {
//            statement = conn.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_EMPLOYEES);
//
//            while(resultSet.next()){
//
//                if(resultSet.getString(EMPLOYEE_FIRST_NAME).equals(employee.getFirstName().trim())
//                        && resultSet.getString(EMPLOYEE_LAST_NAME).equals(employee.getLastName().trim())
//                        && resultSet.getString(EMPLOYEE_MIDDLE_NAME).equals(employee.getMiddleName().trim())){
//
//                    return true;
//                }
//
//            }
//
//            statement.close();
//            resultSet.close();
//
//        } catch (SQLException e) {
//            e.getMessage();
//            e.printStackTrace();
//
//            return false;
//        }
//
//        return false;
//    }
//
//    public boolean updateEmployee(Employee oldEmployee, Employee newEmployee) {
//
//
//        java.util.Date date = new java.util.Date();
//
//        try {
//            prep = conn.prepareStatement(UPDATE_EMPLOYEE_STATEMENT);
//            prep.setString(1, newEmployee.getFirstName());
//            prep.setString(2, newEmployee.getMiddleName());
//            prep.setString(3, newEmployee.getLastName());
//            prep.setString(4, newEmployee.getContactNumber());
//            prep.setString(5, newEmployee.getAddress());
//            prep.setString(6, newEmployee.getEmailAddress());
//            prep.setString(7, newEmployee.getBirthDate());
//            prep.setString(8, date.toString());
//            prep.setInt(9, getEmployeeId(oldEmployee));
//
//
//            prep.execute();
//            conn.commit();
//            prep.close();
//
//            return true;
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//
//            return false;
//        }
//
//    }
//
//
//    public boolean disableEmployee(int employee_id){
//
//        try {
//            prep = conn.prepareStatement(DISABLE_EMPLOYEE_STATEMENT);
//            prep.setInt(1, employee_id);
//
//            prep.execute();
//            conn.commit();
//            prep.close();
//
//            return true;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//            return false;
//        }
//
//    }
//
//    public boolean enableEmployee(int employee_id){
//
//        try {
//            prep = conn.prepareStatement(ENABLE_EMPLOYEE_STATEMENT);
//            prep.setInt(1, employee_id);
//
//            prep.execute();
//            conn.commit();
//            prep.close();
//
//            return true;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//            return false;
//        }
//
//    }
//
//    public int getEmployeeId(Employee employee){
//
//        try {
//            statement = conn.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_EMPLOYEES);
//
//            while (resultSet.next()){
//
//                if(resultSet.getString(EMPLOYEE_FIRST_NAME).equals(employee.getFirstName()) &&
//                        resultSet.getString(EMPLOYEE_MIDDLE_NAME).equals(employee.getMiddleName())&&
//                        resultSet.getString(EMPLOYEE_LAST_NAME).equals(employee.getLastName())){
//
//                    return resultSet.getInt(EMPLOYEE_ID);
//
//                }
//
//
//            }
//
//            resultSet.close();
//            statement.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//            return -1;
//        }
//
//        return -1;
//
//    }
//
//    public Employee getEmployee(int index){
//
//        try {
//            statement = conn.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_EMPLOYEES);
//
//
//            while(resultSet.next()){
//
//                if(resultSet.getInt(EMPLOYEE_ID) == index){
//
//                    return new Employee(
//                            resultSet.getInt(EMPLOYEE_ID),
//                            resultSet.getString(EMPLOYEE_FIRST_NAME),
//                            resultSet.getString(EMPLOYEE_MIDDLE_NAME),
//                            resultSet.getString(EMPLOYEE_LAST_NAME),
//                            resultSet.getString(EMPLOYEE_ADDRESS),
//                            resultSet.getString(EMPLOYEE_CONTACT_NUMBER),
//                            resultSet.getString(EMPLOYEE_BIRTHDATE),
//                            resultSet.getString(EMPLOYEE_EMAIL),
//                            resultSet.getString(EMPLOYEE_STATUS),
//                            resultSet.getString(EMPLOYEE_LAST_UPDATED)
//                    );
//
//                }
//
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public ArrayList<Employee> getEmployeeList(boolean isEnabled){
//
//        ArrayList<Employee> employees= new ArrayList<Employee>();
//        Employee employee;
//
//        try{
//            statement = conn.createStatement();
//            if(isEnabled){
//                resultSet = statement.executeQuery("SELECT * FROM "+TABLE_EMPLOYEES+" WHERE "+EMPLOYEE_STATUS+" = 'ENABLED'");
//            }else {
//                resultSet = statement.executeQuery("SELECT * FROM "+TABLE_EMPLOYEES+" WHERE "+EMPLOYEE_STATUS+" = 'DISABLED'");
//            }
//            while (resultSet.next()){
//
//                employee = new Employee(
//                        resultSet.getInt(EMPLOYEE_ID),
//                        resultSet.getString(EMPLOYEE_FIRST_NAME),
//                        resultSet.getString(EMPLOYEE_MIDDLE_NAME),
//                        resultSet.getString(EMPLOYEE_LAST_NAME),
//                        resultSet.getString(EMPLOYEE_ADDRESS),
//                        resultSet.getString(EMPLOYEE_CONTACT_NUMBER),
//                        resultSet.getString(EMPLOYEE_BIRTHDATE),
//                        resultSet.getString(EMPLOYEE_EMAIL),
//                        resultSet.getString(EMPLOYEE_STATUS),
//                        resultSet.getString(EMPLOYEE_LAST_UPDATED)
//                );
//
//                employees.add(employee);
//            }
//
//            statement.close();
//            resultSet.close();
//
//            return employees;
//
//
//
//        }catch (SQLException e){
//
//            e.getMessage();
//            e.printStackTrace();
//
//
//        }
//
//
//
//        return null;
//
//    }
//    public ArrayList<Employee> getEmployeeList(){
//
//        ArrayList<Employee> employees= new ArrayList<Employee>();
//        Employee employee;
//
//        try{
//            statement = conn.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_EMPLOYEES);
//
//            while (resultSet.next()){
//
//                employee = new Employee(
//                        resultSet.getInt(EMPLOYEE_ID),
//                        resultSet.getString(EMPLOYEE_FIRST_NAME),
//                        resultSet.getString(EMPLOYEE_MIDDLE_NAME),
//                        resultSet.getString(EMPLOYEE_LAST_NAME),
//                        resultSet.getString(EMPLOYEE_ADDRESS),
//                        resultSet.getString(EMPLOYEE_CONTACT_NUMBER),
//                        resultSet.getString(EMPLOYEE_BIRTHDATE),
//                        resultSet.getString(EMPLOYEE_EMAIL),
//                        resultSet.getString(EMPLOYEE_STATUS),
//                        resultSet.getString(EMPLOYEE_LAST_UPDATED)
//                );
//
//                employees.add(employee);
//            }
//
//            statement.close();
//            resultSet.close();
//
//            return employees;
//
//
//
//        }catch (SQLException e){
//
//            e.getMessage();
//            e.printStackTrace();
//
//
//        }
//
//
//
//        return null;
//
//    }
//
//
//
//    public boolean addAccount(Account account, int employeeId){
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        java.util.Date date = new java.util.Date();
//
//        try {
//            prep = conn.prepareStatement(ADD_ACCOUNT_STATEMENT);
//
//
//            prep.setInt(1,employeeId );
//            prep.setString(2, account.getUsername());
//            prep.setString(3, account.getPassword());
//            prep.setString(4, account.getType());
//            prep.setString(5,dateFormat.format(date));
//
//            prep.execute();
//            conn.commit();
//            System.out.println("Added data");
//            prep.close();
//
//            return true;
//
//        }catch(SQLException e){
//
//            e.getMessage();
//            e.printStackTrace();
//            System.out.println("Could not add account");
//
//            return false;
//        }
//
//    }
//
//    public boolean searchAccount(Account account){
//
//        try {
//            statement = conn.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_ACCOUNTS);
//
//            while(resultSet.next()){
//
//                String username = account.getUsername();
//                String password = account.getPassword();
//
//                if(username.equals(resultSet.getString(ACCOUNT_USERNAME)) && password.equals(resultSet.getString(ACCOUNT_PASSWORD))) {
//
//                    return true;
//
//                }
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        return false;
//    }
//
//    public boolean updateAccount(Account oldAccount, Account newAccount){
//
//        java.util.Date date = new Date();
//
//        try {
//            prep = conn.prepareStatement(UPDATE_ACOUNT_STATEMENT);
//
//            prep.setString(1, newAccount.getUsername());
//            prep.setString(2, newAccount.getPassword());
//            prep.setString(3, newAccount.getType());
//            prep.setString(4, date.toString());
//            prep.setInt(5, oldAccount.getId());
//
//            prep.execute();
//            conn.commit();
//            prep.close();
//
//            return true;
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//
//            return false;
//        }
//
//
//
//
//
//    }
//
//    public ArrayList<Account> getAccountList(boolean isDecrypted){
//
//        ArrayList<Account> accounts= new ArrayList<Account>();
//        Account account = new Account();
//
//        try{
//            statement = conn.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_ACCOUNTS);
//
//            while (resultSet.next()){
//
//                if(isDecrypted){
//
//                    account = new Account(
//                            resultSet.getInt(ACCOUNT_ID),
//                            resultSet.getInt(ACCOUNT_EMPLOYEE_ID),
//                            resultSet.getString(ACCOUNT_USERNAME),
//                            account.decryptPassword(resultSet.getString(ACCOUNT_PASSWORD)),
//                            resultSet.getString(ACCOUNT_TYPE),
//                            resultSet.getString(ACCOUNT_LAST_LOGIN),
//                            resultSet.getString(ACCOUNT_STATUS)
//                    );
//                    accounts.add(account);
//
//                }else{
//
//                    account = new Account(
//                            resultSet.getInt(ACCOUNT_ID),
//                            resultSet.getInt(ACCOUNT_EMPLOYEE_ID),
//                            resultSet.getString(ACCOUNT_USERNAME),
//                            resultSet.getString(ACCOUNT_PASSWORD),
//                            resultSet.getString(ACCOUNT_TYPE),
//                            resultSet.getString(ACCOUNT_LAST_LOGIN),
//                            resultSet.getString(ACCOUNT_STATUS)
//                    );
//
//                    accounts.add(account);
//
//
//                }
//
//            }
//
//            statement.close();
//            resultSet.close();
//
//            return accounts;
//
//
//
//        }catch (SQLException e){
//
//            e.getMessage();
//            e.printStackTrace();
//
//
//        }
//
//
//
//        return null;
//
//
//
//
//
//
//    }
//
//    public ArrayList<Account> getAccountList(int id, boolean isDecrypted){
//
//
//        ArrayList<Account> accounts= new ArrayList();
//        Account account = new Account();
//
//        try{
//            statement = conn.createStatement();
//            prep = conn.prepareStatement(SEARCH_ACCOUNT_BY_EMP_ID);
//            prep.setInt(1, id);
//            resultSet = prep.executeQuery();
//
//            while (resultSet.next()){
//
//
//
//                if(isDecrypted){
//                    account = new Account(
//                            resultSet.getInt(ACCOUNT_ID),
//                            resultSet.getInt(ACCOUNT_EMPLOYEE_ID),
//                            resultSet.getString(ACCOUNT_USERNAME),
//                            account.decryptPassword(resultSet.getString(ACCOUNT_PASSWORD)),
//                            resultSet.getString(ACCOUNT_TYPE),
//                            resultSet.getString(ACCOUNT_LAST_LOGIN),
//                            resultSet.getString(ACCOUNT_STATUS)
//                    );
//
//                    accounts.add(account);
//
//                }else{
//                    account = new Account(
//                            resultSet.getInt(ACCOUNT_ID),
//                            resultSet.getInt(ACCOUNT_EMPLOYEE_ID),
//                            resultSet.getString(ACCOUNT_USERNAME),
//                            resultSet.getString(ACCOUNT_PASSWORD),
//                            resultSet.getString(ACCOUNT_TYPE),
//                            resultSet.getString(ACCOUNT_LAST_LOGIN),
//                            resultSet.getString(ACCOUNT_STATUS)
//                    );
//
//                    accounts.add(account);
//                }
//
//
//
//            }
//
//            statement.close();
//            prep.close();
//            resultSet.close();
//
//            return accounts;
//
//
//
//        }catch (SQLException e){
//
//            e.getMessage();
//            e.printStackTrace();
//
//
//        }
//
//
//
//        return null;
//
//
//
//
//
//
//    }


}
