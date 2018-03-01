package com.hardware.fiesta.Database;


import com.hardware.fiesta.Model.AdminLog;
import com.hardware.fiesta.Model.Item;
import com.hardware.fiesta.Model.Log;

import java.sql.*;



public class StockDatabaseConnection {


    private static final String desktopLocation = "jdbc:sqlite:C:\\Users\\aircellozano\\Desktop\\";
   // private static final String projectLocation = "jdbc:sqlite:C:\\Users\\aircellozano\\IdeaProjects\\keigo\\src\\databases\\";


    private static final String DATABASE_NAME = "Stocks.db";
    private static final String CONNECTION_STRING = desktopLocation + DATABASE_NAME;


    //TABLE NAMES
    private static final String TABLE_HANDTOOLS = "HandTools";
    private static final String TABLE_ELEC = "Electrical";
    private static final String TABLE_PAINT = "Paints";
    private static final String TABLE_ADMINLOG = "AdminLog";


    //ITEM COLUMN NAMES


    private static final String ITEM_ID = "ID";
    private static final String ITEM_NAME = "NAME";
    private static final String ITEM_PRICE = "PRICE";
    private static final String ITEM_STATUS = "STATUS";

    //ADMINLOG COLUMN NAMES

    private static final String ADMIN_ID = "ID";
    private static final String ADMIN_TYPE = "TYPE";
    private static final String ADMIN_LOG_TIME = "TIME";
    private static final String ADMIN_LOG_ACTION = "ACTION";


    //CREATE TABLE TOOLS
    private static final String CREATE_HANDTOOLTB = "CREATE TABLE IF NOT EXISTS " + TABLE_HANDTOOLS +
            " (" + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ITEM_NAME + " TEXT,"
            + ITEM_PRICE + " REAL, "
            + ITEM_STATUS + " TEXT "
            + ")";

    //CREATE ELECTRIC TABLE

    private static final String CREATE_ELECTB = "CREATE TABLE IF NOT EXISTS " + TABLE_ELEC +
            " (" + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ITEM_NAME + " TEXT,"
            + ITEM_PRICE + " REAL, "
            + ITEM_STATUS + " TEXT "
            + ")";

    // CREATE PAINT TABLE


    private static final String CREATE_PAINTTB = "CREATE TABLE IF NOT EXISTS " + TABLE_PAINT +
            " (" + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ITEM_NAME + " TEXT,"
            + ITEM_PRICE + " REAL, "
            + ITEM_STATUS + " TEXT "
            + ")";

    // CREATE ADMIN LOG TABLE
    private static final String CREATE_ADMINLOG = "CREATE TABLE IF NOT EXISTS " + TABLE_ADMINLOG +
            " (" + ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ADMIN_TYPE + " TEXT,"
            + ADMIN_LOG_TIME + " TEXT,"
            + ADMIN_LOG_ACTION + " TEXT"
            + ")";

    private Connection conn;
    private Statement statement;

    StockDatabaseConnection() {

        try {

            openConnection();

            String outputMessage = "System Startup";
            createAdminLog(outputMessage);
            statement = conn.createStatement();


            statement.execute(CREATE_HANDTOOLTB);
            statement.execute(CREATE_ELECTB);
            statement.execute(CREATE_PAINTTB);
            statement.execute(CREATE_ADMINLOG);
            statement.close();

        } catch (SQLException e) {

            e.getMessage();
            e.printStackTrace();

        }

    }


    private void openConnection() {

        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);

            String outputMessage = "Establishing Connection to Database";
            createAdminLog(outputMessage);

        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void closeConnection() {

        try {


            String outputMessage = "Database Connection Closing!!!!!!";
            createAdminLog(outputMessage);
            conn.close();

        } catch (SQLException e) {

            e.getMessage();
            e.printStackTrace();

        }

    }

    public void systemShutdown(){


        String outputMessage = "System Shutdown...";
        createAdminLog(outputMessage);

        closeConnection();

    }

    public boolean addItem(Item item) {


        int type = item.getType();
        String table = "";


        if (type == 1) {

            table = TABLE_HANDTOOLS;

        } else if (type == 2) {

            table = TABLE_ELEC;

        } else if (type == 3) {

            table = TABLE_PAINT;

        }

        if(!validateItem(item)) {

            try {
                statement = conn.createStatement();
                statement.execute("INSERT INTO " + table +
                        " (" + ITEM_NAME + ","
                        + ITEM_PRICE + ","
                        + ITEM_STATUS + ") VALUES " +
                        "('" + item.getName() + "',"
                        + item.getCost() + "," +
                        "'Enabled')");

                statement.close();


                String outputMessage = "Added item "+item.getName()+" with a price of "+item.getCost()+" to table "+table+" successfully!!!";
                createAdminLog(outputMessage);
                return true;
            } catch (SQLException e) {

                e.getMessage();
                e.printStackTrace();

                return false;

            }
        }

        System.out.println("Item "+item.getName()+" is already in the Database!!!");
        return false;
    }

    public boolean updateItem(Item oldItem, Item newItem) {

        if (oldItem.getType() != newItem.getType()) {

            return false;

        }

        if(validateItem(oldItem)) {

            int type = oldItem.getType();
            String table = "";

            if (type == 1) {

                table = TABLE_HANDTOOLS;
            } else if (type == 2) {
                table = TABLE_ELEC;
            } else if (type == 3) {

                table = TABLE_PAINT;
            }

            try {

                statement = conn.createStatement();

                statement.execute("UPDATE " + table + " SET " + ITEM_NAME + " = '" + newItem.getName()
                        + "', " + ITEM_PRICE + " = " + newItem.getCost()
                        + " WHERE " + ITEM_NAME + " = '" + oldItem.getName() + "' AND " + ITEM_PRICE + " = " + oldItem.getCost());

                statement.close();



                String outputMessage = "Updated item "+oldItem.getName()+" to "+newItem.getName()+" and updated price from "+oldItem.getCost()+" to "+newItem.getCost()+" in "+table+" table.";
                createAdminLog(outputMessage);
                return true;

            } catch (SQLException e) {

                e.getMessage();
                e.printStackTrace();

                return false;


            }
        }
        return false;
    }

    private boolean validateItem(Item item){

        int type = item.getType();
        String table;

        if(type==1){

            table = TABLE_HANDTOOLS;

        }else if(type==2){

            table = TABLE_ELEC;
        }else if(type==3){

            table = TABLE_PAINT;
        }else{
            return false;
        }


        try{

            statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM "+table);

            while(result.next()){

                if(result.getString(ITEM_NAME).equals(item.getName())){

                    return true;
                }

            }

            statement.close();

        }catch(SQLException e){

            e.getMessage();
            e.printStackTrace();

            return false;

        }

        return false;

    }

    private Item searchItem(String name, String tableName, int type){

        try{
            statement = conn.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM "+tableName);

            while(result.next()){

                if(result.getString(ITEM_NAME).equals(name)) {
                    if (result.getString(ITEM_STATUS).equals("Enabled")) {
                        return new Item(result.getString(ITEM_NAME), result.getDouble(ITEM_PRICE), true, type);
                    }
                        return new Item(result.getString(ITEM_NAME),result.getDouble(ITEM_PRICE),false,type);
                }

            }

            statement.close();

        }catch (SQLException e){


            e.getMessage();
            e.printStackTrace();
            return null;
        }

        return null;

    }

    public Item searchItem(String name){

        Item item;

       if(searchItem(name,TABLE_HANDTOOLS,1)!=null){

           item = searchItem(name,TABLE_HANDTOOLS,1);

       }else if(searchItem(name,TABLE_ELEC,2)!=null){

           item = searchItem(name,TABLE_ELEC,2);
       }else{

           item =  searchItem(name,TABLE_PAINT,3);
       }

      return item;

    }

    public boolean disableItem(Item item){

        int type = item.getType();
        String table = "";

        if(type==1){

            table = TABLE_HANDTOOLS;

        }else if(type==2){

            table = TABLE_ELEC;

        }else if(type==3){

            table = TABLE_PAINT;
        }


        try{

           statement = conn.createStatement();

           statement.execute("UPDATE " + table + " SET " + ITEM_STATUS + " = 'Disabled'"
                    + " WHERE " + ITEM_NAME + " = '" + item.getName()+"'");



            String outputMessage = "Disabled item "+item.getName()+" successfully!!!";
            createAdminLog(outputMessage);

           statement.close();


           return true;
        }catch (SQLException e){

            e.printStackTrace();
            e.getMessage();
            return false;
        }

    }

    public boolean enableItem(Item item){

        int type = item.getType();
        String table;

        if(type==1){

            table = TABLE_HANDTOOLS;

        }else if(type==2){

            table = TABLE_ELEC;

        }else if(type==3){

            table = TABLE_PAINT;
        }else{

            return false;
        }


        try{

            statement = conn.createStatement();

            statement.execute("UPDATE " + table + " SET " + ITEM_STATUS + " = 'Enabled'"
                    + " WHERE " + ITEM_NAME + " = '" + item.getName()+"'");



            String outputMessage = "Enabled item "+item.getName()+" successfully!!!";
            createAdminLog(outputMessage);
            statement.close();

            return true;
        }catch (SQLException e){

            e.printStackTrace();
            e.getMessage();
            return false;
        }

    }

    private void deleteTableData(String tableName, String recreateTable){


        try {

            closeConnection();
            openConnection();

            statement = conn.createStatement();
            statement.execute("DROP TABLE IF EXISTS "+tableName);
            statement.execute(recreateTable);



            String outputMessage = "Deleted data of table "+tableName;
            createAdminLog(outputMessage);

            statement.close();

        } catch (SQLException e) {



            String outputMessage = "Abnormal Shutdown due to database problem:  \n" +
                                    e.getMessage();
            createAdminLog(outputMessage);

            e.getMessage();
            e.printStackTrace();

        }

    }

    public boolean deleteTableData(int type){

        switch(type){

            case 1: deleteTableData(TABLE_HANDTOOLS,CREATE_HANDTOOLTB);
                    return true;

            case 2: deleteTableData(TABLE_ELEC,CREATE_ELECTB);
                    return true;

            case 3: deleteTableData(TABLE_PAINT,CREATE_PAINTTB);
                    return true;

            default: return false;


        }




    }

    private void createAdminLog(String description){

        Log log = new AdminLog(description);


        try {
            statement = conn.createStatement();
            statement.execute("INSERT INTO " + TABLE_ADMINLOG +
                    " (" + ADMIN_TYPE + ","
                    + ADMIN_LOG_TIME + ","
                    + ADMIN_LOG_ACTION + ") VALUES " + "('"
                    + log.getType() + "','"
                    + log.getStrTime() + "','"
                    + log.getDescription()+"')");

            statement.close();
        } catch (SQLException e) {

            e.getMessage();
            e.printStackTrace();
        }

    }



}
