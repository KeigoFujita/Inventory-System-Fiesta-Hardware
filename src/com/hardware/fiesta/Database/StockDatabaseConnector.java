package com.hardware.fiesta.Database;

import com.hardware.fiesta.Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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
    private static final String TABLE_INVENTORY   = "Inventory";
    private static final String TABLE_SALES       = "Sales";
    private static final String TABLE_STOCK_REPLENISHMENT = "StockReplenishment";


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
    private static final String ITEM_UNIT         ="item_unit";


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


    //INVENTORY COLUMN NAMES

    private static final String INVENTORY_ID = "inventory_id";
    private static final String INVENTORY_ITEM_ID = "item_id";
    private static final String INVENTORY_INIT_STOCK = "initial_stock";
    private static final String INVENTORY_INIT_DATE  = "initial_date";
    private static final String INVENTORY_FINAL_STOCK = "final_stock";
    private static final String INVENTORY_FINAL_DATE = "final_date";
    private static final String INVENTORY_ITEM_SALES = "item_sales";
    private static final String INVENTORY_ITEM_STATUS = "inventory_status";

    //SALES COLUMN NAMES

    private static final String SALES_ID = "sales_id";
    private static final String SALES_INVENTORY_ID = "inventory_id";
    private static final String SALES_ITEM_ID = "item_id";
    private static final String SALES_ITEM_SALES  = "item_sales";
    private static final String SALES_GROSS = "gross_sales";
    private static final String SALES_INCLUSIVE_DATES = "inclusive_dates";
    private static final String SALES_STATUS = "sales_status";



    //INVENTORY COLUMN NAMES

    private static final String REPLENISH_ID = "sr_id";
    private static final String REPLENISH_ITEM_ID = "item_id";
    private static final String REPLENISH_STOCK_REMAINING = "stock_remaining";
    private static final String REPLENISH_STOCK_ADDED  = "stock_added";
    private static final String REPLENISH_STOCK_FINAL = "stock_final";
    private static final String REPLENISH_DATE = "date";


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
                    +ITEM_UNIT          +" TEXT NOT NULL DEFAULT PIECES,"
                    +ITEM_BRAND_NAME    +" INTEGER NOT NULL REFERENCES " +TABLE_BRAND_NAMES   +"("+BRAND_NAME_ID+"), "
                    +ITEM_TYPE          +" INTEGER NOT NULL REFERENCES " +TABLE_TYPES         +"("+TYPE_ID+"), "
                    +ITEM_CATEGORY      +" INTEGER NOT NULL REFERENCES " +TABLE_CATEGORIES    +"("+CATEGORY_ID+"), "
                    +ITEM_BARCODE_ID    +" TEXT NOT NULL, "
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


    // CREATE INVENTORY TABLE

    private static final String CREATE_TABLE_INVENTORY =
            "CREATE TABLE IF NOT EXISTS "+TABLE_INVENTORY+
                    " ( "
                    + INVENTORY_ID           +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + INVENTORY_ITEM_ID      +" INTEGER NOT NULL REFERENCES "+TABLE_STOCKS +"("+ITEM_ID+"), "
                    + INVENTORY_INIT_STOCK   +" INTEGER NOT NULL DEFAULT 0, "
                    + INVENTORY_INIT_DATE    +" TEXT NOT NULL, "
                    + INVENTORY_FINAL_STOCK  +" INTEGER NOT NULL DEFAULT 0, "
                    + INVENTORY_FINAL_DATE   +" TEXT NOT NULL, "
                    + INVENTORY_ITEM_SALES   +" INTEGER NOT NULL DEFAULT 0,"
                    + INVENTORY_ITEM_STATUS  +" TEXT NOT NULL DEFAULT ENABLED"
                +   " ) ";

    // CREATE SALES TABLE

    private static final String CREATE_TABLE_SALES =
            "CREATE TABLE IF NOT EXISTS "+TABLE_SALES+
                    " ( "
                    + SALES_ID               +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + SALES_INVENTORY_ID     +" INTEGER NOT NULL REFERENCES "+TABLE_INVENTORY +"("+INVENTORY_ID+"), "
                    + SALES_ITEM_ID          +" INTEGER NOT NULL REFERENCES "+TABLE_STOCKS +"("+ITEM_ID+"), "
                    + SALES_ITEM_SALES       +" INTEGER NOT NULL, "
                    + SALES_GROSS            +" REAL NOT NULL DEFAULT 0, "
                    + SALES_INCLUSIVE_DATES  +" TEXT NOT NULL DEFAULT Pending, "
                    + SALES_STATUS           +" TEXT NOT NULL DEFAULT ENABLED"
                    +   " ) ";


    // CREATE SALES TABLE

    private static final String CREATE_TABLE_REPLENISHMENT =
            "CREATE TABLE IF NOT EXISTS "+TABLE_STOCK_REPLENISHMENT+
                    " ( "
                    + REPLENISH_ID               +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + REPLENISH_ITEM_ID          +" INTEGER NOT NULL REFERENCES "+TABLE_STOCKS +"("+ITEM_ID+"), "
                    + REPLENISH_STOCK_REMAINING  +" INTEGER NOT NULL, "
                    + REPLENISH_STOCK_ADDED      +" INTEGER NOT NULL, "
                    + REPLENISH_STOCK_FINAL      +" INTEGER NOT NULL, "
                    + REPLENISH_DATE             +" TEXT NOT NULL "
                +   " ) ";



    //STATEMENT FOR ADDING INVENTORY ACTIONS

    public static final String ADD_INVENTORY_STATEMENT =
            " INSERT INTO "+TABLE_INVENTORY+
            " ( "
                    +INVENTORY_ITEM_ID      + ", "
                    +INVENTORY_INIT_STOCK   + ", "
                    +INVENTORY_INIT_DATE    + ", "
                    +INVENTORY_FINAL_STOCK  + ", "
                    +INVENTORY_FINAL_DATE   + ", "
                    +INVENTORY_ITEM_SALES   + "  "
                                            +
            " ) "                           +

            " VALUES (?,?,?,?,?,?) ";


    //STATEMENT FOR ADDING SALES ACTIONS

    public static final String ADD_SALES_STATEMENT =
            " INSERT INTO "+TABLE_SALES+
                    " ( "
                    +SALES_INVENTORY_ID      + ", "
                    +SALES_ITEM_ID           + ", "
                    +SALES_ITEM_SALES        + ", "
                    +SALES_GROSS             + ", "
                    +SALES_INCLUSIVE_DATES   + "  "
                    +

                    " ) "                    +

            " VALUES (?,?,?,?,?) ";


    //STATEMENT FOR ADDING STOCK REPLENISHMENT  ACTIONS

    public static final String ADD_REPLENISH_STATEMENT =
            " INSERT INTO "+TABLE_STOCK_REPLENISHMENT+
                    " ( "
                    +REPLENISH_ITEM_ID               + ", "
                    +REPLENISH_STOCK_REMAINING       + ", "
                    +REPLENISH_STOCK_ADDED           + ", "
                    +REPLENISH_STOCK_FINAL           + ", "
                    +REPLENISH_DATE                  +
                    " ) "                            +

             " VALUES (?,?,?,?,?) ";




    //STATEMENT FOR ADDING ITEM

    public static final String ADD_ITEM_STATEMENT =
            " INSERT INTO "+TABLE_STOCKS+
            " ( "
                    +ITEM_NAME             + ", "
                    +ITEM_COST             + ", "
                    +ITEM_QTY              + ", "
                    +ITEM_UNIT             + ", "
                    +ITEM_BRAND_NAME       + ", "
                    +ITEM_TYPE             + ", "
                    +ITEM_CATEGORY         + ", "
                    +ITEM_BARCODE_ID       + ", "
                    +ITEM_DESCRIPTION      + ", "
                    +ITEM_LAST_UPDATED     + ", "
                    +ITEM_SUPPLIER         +
            " ) "+
            " VALUES (?,?,?,?,?,?,?,?,?,?,?) ";





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


    //STATEMENT FOR DISABLING ITEM

    public static final String DISABLE_ITEM_STATEMENT =

            " UPDATE "+ TABLE_STOCKS+
            " SET    "+ ITEM_STATUS +" = 'DISABLED'" +
            " WHERE  "+ ITEM_ID     +" = ? ";

    //STATEMENT FOR DISABLING ITEM

    public static final String ENABLE_ITEM_STATEMENT =

            " UPDATE "+ TABLE_STOCKS+
            " SET    "+ ITEM_STATUS +" = 'ENABLED'" +
            " WHERE  "+ ITEM_ID     +" = ? ";



    //STATEMENT OF DISABLING CATEGORY

    public static final String DISABLE_CATEGORY_STATEMEMNT =
            " UPDATE "+TABLE_CATEGORIES+
            " SET "+CATEGORY_STATUS+" = 'DISABLED'"+
            " WHERE "+CATEGORY_ID+" = ?";

    //STATEMENT OF ENABLING CATEGORY

    public static final String ENABLE_CATEGORY_STATEMEMNT =
            "UPDATE "+TABLE_CATEGORIES+
                    " SET "+CATEGORY_STATUS+" = 'ENABLED'"+
                    " WHERE "+CATEGORY_ID+" = ?";


    //STATEMENT OF DISABLING TYPE
    public static final String DISABLE_TYPE_STATEMENT =
            " UPDATE "+TABLE_TYPES+
                    " SET "+TYPE_STATUS+" = 'DISABLED'"+
                    " WHERE "+TYPE_ID+" = ?";

    //STATEMENT OF ENABLING TYPE
    public static final String ENABLE_TYPE_STATEMENT =
            "UPDATE "+TABLE_TYPES+
                    " SET "+TYPE_STATUS+" = 'ENABLED'"+
                    " WHERE "+TYPE_ID+" = ?";



    //STATEMENT OF DISABLING BRAND
    public static final String DISABLE_BRAND_STATEMENT =
            " UPDATE "+TABLE_BRAND_NAMES+
                    " SET "+BRAND_NAME_STATUS+" = 'DISABLED'"+
                    " WHERE "+BRAND_NAME_ID+" = ?";


    //STATEMENT OF ENABLING BRAND
    public static final String ENABLE_BRAND_STATEMENT =
            "UPDATE "+TABLE_BRAND_NAMES+
                    " SET "+BRAND_NAME_STATUS+" = 'ENABLED'"+
                    " WHERE "+BRAND_NAME_ID+" = ?";



    //STATEMENT OF DISABLING SUPPLIER
    public static final String DISABLE_SUPPLIER_STATEMENT =
            " UPDATE "+TABLE_SUPPLIERS+
                    " SET "+SUPPLIER_STATUS+" = 'DISABLED'"+
                    " WHERE "+SUPPLIER_ID+" = ?";


    //STATEMENT OF ENABLING SUPPLIER
    public static final String ENABLE_SUPPLIER_STATEMENT =
            "UPDATE "+TABLE_SUPPLIERS+
                    " SET "+SUPPLIER_STATUS+" = 'ENABLED'"+
                    " WHERE "+SUPPLIER_ID+" = ?";



    //STATEMENT FOR UPDATING ITEM

    public static final String UPDATE_ITEM_STATEMENT =

            " UPDATE "  +TABLE_STOCKS+
            " SET    "  +ITEM_NAME        + " = ? , "+
                         ITEM_COST        + " = ? , "+
                         ITEM_QTY         + " = ? , "+
                         ITEM_UNIT        + " = ? , "+
                         ITEM_BARCODE_ID  + " = ? , "+
                         ITEM_DESCRIPTION + " = ? , "+
                         ITEM_BRAND_NAME  + " = ? , "+
                         ITEM_CATEGORY    + " = ? , "+
                         ITEM_TYPE        + " = ? , "+
                         ITEM_SUPPLIER    + " = ?   "+
            " WHERE "   +ITEM_ID          + " = ?    ";


    //STATEMENT FOR UPDATING CATEGORY

    public static final String UPDATE_CATEGORY_STATEMENT =
            "UPDATE " +TABLE_CATEGORIES+
            " SET   " +CATEGORY_NAME   +" = ?"+
            " WHERE " +CATEGORY_ID     +" = ?";


    //STATEMENT FOR UPDATING CATEGORY

    public static final String UPDATE_TYPE_STATEMENT =
            "UPDATE " +TABLE_TYPES+
                    " SET   " +TYPE_NAME   +" = ?"+
                    " WHERE " +TYPE_ID     +" = ?";



    //STATEMENT FOR UPDATING BRAND NAME

    public static final String UPDATE_BRAND_STATEMENT =
            "UPDATE " +TABLE_BRAND_NAMES+
                    " SET   " +BRAND_NAME_NAME   +" = ?"+
                    " WHERE " +BRAND_NAME_ID     +" = ?";



    //STATEMENT FOR UPDATING SUPPLIER

    public static final String UPDATE_SUPPLIER_STATEMENT =
                    "UPDATE " +TABLE_SUPPLIERS+
                    " SET   " +SUPPLIER_NAME            +" = ? ,"
                              +SUPPLIER_ADDRESS         +" = ? ,"
                              +SUPPLIER_CONTACT_NUMBER  +" = ? ,"
                              +SUPPLIER_EMAIL_ADDRESS   +" = ?  "
                  + " WHERE " +SUPPLIER_ID              +" = ?";


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
            statement.execute(CREATE_TABLE_INVENTORY);
            statement.execute(CREATE_TABLE_SALES);
            statement.execute(CREATE_TABLE_REPLENISHMENT);
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

            System.out.println("Stocks Database Closed Succesfully");

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



    public boolean addInventory(Inventory inventory){

        try {
            prep = conn.prepareStatement(ADD_INVENTORY_STATEMENT);
            prep.setInt(1, inventory.getInventoryItemId());
            prep.setInt(2,inventory.getInitialStock());
            prep.setString(3,inventory.getInitialDate());
            prep.setInt(4,inventory.getFinalStock());
            prep.setString(5,inventory.getFinalDate());
            prep.setInt(6,inventory.getItemSales());

            prep.execute();
            conn.commit();
            prep.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean addSales(Sales sales){

        try {
            prep = conn.prepareStatement(ADD_SALES_STATEMENT);
            prep.setInt(1,sales.getSalesInventoryId());
            prep.setInt(2,sales.getSalesItemId());
            prep.setInt(3,sales.getItemSales());
            prep.setDouble(4,sales.getItemsGrossSales());
            prep.setString(5,sales.getInclusiveDates());

            prep.execute();
            conn.commit();
            prep.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean addReplenish(StockReplenishment stockReplenishment){

        try {
            prep = conn.prepareStatement(ADD_REPLENISH_STATEMENT);
            prep.setInt(1,stockReplenishment.getReplenishItemId());
            prep.setInt(2,stockReplenishment.getReplenishStockRemaining());
            prep.setInt(3,stockReplenishment.getReplenishStockAdded());
            prep.setDouble(4,stockReplenishment.getReplenishStockFinal());
            prep.setString(5,stockReplenishment.getReplenishDate());

            prep.execute();
            conn.commit();
            prep.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public boolean updateInventory(int itemId, int qty){

        try {

            String UPDATE_INVENTORY_STATEMENT =
                    " UPDATE "  +TABLE_INVENTORY               +
                    " SET "     +INVENTORY_INIT_STOCK +" = ? " +
                    " WHERE   " +INVENTORY_ITEM_ID    +" = ? " +
                    " AND "     +INVENTORY_ITEM_STATUS+" = 'ENABLED' ";

            prep = conn.prepareStatement(UPDATE_INVENTORY_STATEMENT);
            prep.setInt(1,qty);
            prep.setInt(2,itemId);

            prep.execute();
            conn.commit();
            prep.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }
    public boolean updateInventory(Inventory inventory){

        String UPDATE_INVENTORY_STATEMENT =
                " UPDATE " + TABLE_INVENTORY         +
                " SET "    + INVENTORY_FINAL_STOCK   +" = ?, " +
                             INVENTORY_FINAL_DATE    +" = ?, " +
                             INVENTORY_ITEM_SALES    +" = ?,  " +
                             INVENTORY_ITEM_STATUS   +" = 'DISABLED' " +
                " WHERE   " +INVENTORY_ITEM_ID       +" = ? " +
                " AND     " +INVENTORY_ITEM_STATUS   +" = 'ENABLED'";

        try {

            prep = conn.prepareStatement(UPDATE_INVENTORY_STATEMENT);
            prep.setInt(1,inventory.getFinalStock());
            prep.setString(2,inventory.getFinalDate());
            prep.setInt(3,inventory.getItemSales());
            prep.setInt(4,inventory.getInventoryItemId());

            prep.execute();
            conn.commit();
            prep.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public int getInventoryId(Inventory inventory){


        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery( " SELECT "       +INVENTORY_ID
                                                    +" FROM "        +TABLE_INVENTORY +
                                                     " WHERE  "      +INVENTORY_INIT_DATE +" = '"+inventory.getInitialDate()+"'"+
                                                     " AND "         +INVENTORY_FINAL_DATE+" = '"+inventory.getFinalDate()+"'");


            while(resultSet.next()){

                return resultSet.getInt(INVENTORY_ID);


            }

            resultSet.close();
            statement.close();

            return -1;
        } catch (SQLException e) {
            e.printStackTrace();

            return -1;
        }


    }


    public boolean addItem(StockItem stockItem){

        Date date = new Date();
        try {



                prep = conn.prepareStatement(ADD_ITEM_STATEMENT);
                prep.setString(1,stockItem.getItemName());
                prep.setDouble(2,stockItem.getItemCost());
                prep.setInt(3,stockItem.getItemQty());
                prep.setString(4,stockItem.getItemUnit());
                prep.setInt(5,stockItem.getItemBrandName());
                prep.setInt(6,stockItem.getItemType());
                prep.setInt(7,stockItem.getItemCategory());
                prep.setString(8,stockItem.getItemBarcodeId());
                prep.setString(9,stockItem.getItemDescription());
                prep.setString(10,date.toString());
                prep.setInt(11,stockItem.getItemSupplier());

                prep.execute();
                conn.commit();
                prep.close();

                return true;



        } catch (SQLException e) {
            e.printStackTrace();

            return false;
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

                prep = conn.prepareStatement(ADD_SUPPLIER_STATEMENT);
                prep.setString(1, stockSupplier.getSupplierName());
                prep.setString(2, stockSupplier.getSupplierAddress());
                prep.setString(3, stockSupplier.getSupplierContactNumber());
                prep.setString(4, stockSupplier.getSupplierEmailAddress());

                prep.execute();
                conn.commit();
                prep.close();

                return true;

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


    public boolean disableItem(StockItem stockItem){

        try{
            prep = conn.prepareStatement(DISABLE_ITEM_STATEMENT);
            prep.setInt(1,stockItem.getItemId());

            prep.execute();
            conn.commit();
            prep.close();

            return true;

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();

            return false;
        }


    }
    public boolean enableItem(StockItem stockItem){

        try{
            prep = conn.prepareStatement(ENABLE_ITEM_STATEMENT);
            prep.setInt(1,stockItem.getItemId());

            prep.execute();
            conn.commit();
            prep.close();

            return true;

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();

            return false;
        }


    }

    public boolean disableCategory(StockCategory stockCategory){

        try{
            System.out.println(stockCategory.getCategoryId());
            prep = conn.prepareStatement(DISABLE_CATEGORY_STATEMEMNT);
            prep.setInt(1,stockCategory.getCategoryId());

            prep.execute();
            conn.commit();
            prep.close();

            return true;

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();

            return false;
        }

    }
    public boolean enableCategory(StockCategory stockCategory){

        try{
            System.out.println(stockCategory.getCategoryId());
            prep = conn.prepareStatement(ENABLE_CATEGORY_STATEMEMNT);
            prep.setInt(1,stockCategory.getCategoryId());

            prep.execute();
            conn.commit();
            prep.close();

            return true;

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();

            return false;
        }

    }

    public boolean disableType(StockType stockType){

        try{
            System.out.println(stockType.getTypeId());
            prep = conn.prepareStatement(DISABLE_TYPE_STATEMENT);
            prep.setInt(1,stockType.getTypeId());

            prep.execute();
            conn.commit();
            prep.close();

            return true;

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();

            return false;
        }

    }
    public boolean enableType(StockType stockType){

        try{
            System.out.println(stockType.getTypeId());
            prep = conn.prepareStatement(ENABLE_TYPE_STATEMENT);
            prep.setInt(1,stockType.getTypeId());

            prep.execute();
            conn.commit();
            prep.close();

            return true;

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();

            return false;
        }

    }

    public boolean disableBrand(StockBrandName stockBrandName){

        try{
            System.out.println(stockBrandName.getBrandName());
            prep = conn.prepareStatement(DISABLE_BRAND_STATEMENT);
            prep.setInt(1,stockBrandName.getBrandId());

            prep.execute();
            conn.commit();
            prep.close();

            return true;

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();

            return false;
        }

    }
    public boolean enableBrand(StockBrandName stockBrandName){

        try{
            System.out.println(stockBrandName.getBrandId());
            prep = conn.prepareStatement(ENABLE_BRAND_STATEMENT);
            prep.setInt(1,stockBrandName.getBrandId());

            prep.execute();
            conn.commit();
            prep.close();

            return true;

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();

            return false;
        }

    }


    public boolean disableSupplier(StockSupplier stockSupplier){

        try{
            System.out.println(stockSupplier.getSupplierId());
            prep = conn.prepareStatement(DISABLE_SUPPLIER_STATEMENT);
            prep.setInt(1,stockSupplier.getSupplierId());

            prep.execute();
            conn.commit();
            prep.close();

            return true;

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();

            return false;
        }

    }
    public boolean enableSupplier(StockSupplier stockSupplier){

        try{

            prep = conn.prepareStatement(ENABLE_SUPPLIER_STATEMENT);
            prep.setInt(1,stockSupplier.getSupplierId());

            prep.execute();
            conn.commit();
            prep.close();

            return true;

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();

            return false;
        }

    }


    public boolean updateItem(StockItem oldStockItem, StockItem newStockItem){

        try {


                prep = conn.prepareStatement(UPDATE_ITEM_STATEMENT);
                prep.setString(1,newStockItem.getItemName());
                prep.setDouble(2,newStockItem.getItemCost());
                prep.setInt(3, newStockItem.getItemQty());
                prep.setString(4,newStockItem.getItemUnit());
                prep.setString(5,newStockItem.getItemBarcodeId());
                prep.setString(6,newStockItem.getItemDescription());
                prep.setInt(7,newStockItem.getItemBrandName());
                prep.setInt(8,newStockItem.getItemCategory());
                prep.setInt(9,newStockItem.getItemType());
                prep.setInt(10,newStockItem.getItemSupplier());
                prep.setInt(11,oldStockItem.getItemId());

                prep.execute();
                conn.commit();
                prep.close();

                return true;


        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();

            return false;

        }

    }
    public boolean updateCategory(StockCategory oldCategory, StockCategory newCategory){

        try {

            if(validateStockCategory(newCategory)){

                return false;

            }else{

                prep = conn.prepareStatement(UPDATE_CATEGORY_STATEMENT);
                prep.setString(1,newCategory.getCategoryName());
                prep.setInt(2,oldCategory.getCategoryId());

                prep.execute();
                conn.commit();
                prep.close();

                return true;

            }



        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();

            return false;

        }




    }
    public boolean updateType(StockType oldStockType, StockType newStockType){

        try {

            if (validateStockType(newStockType)){

                return false;
            }else{


                prep = conn.prepareStatement(UPDATE_TYPE_STATEMENT);
                prep.setString(1,newStockType.getTypeName());
                prep.setInt(2,oldStockType.getTypeId());

                prep.execute();
                conn.commit();
                prep.close();

                return true;


            }

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();

            return false;

        }




    }
    public boolean updateBrandName(StockBrandName oldBrandName, StockBrandName newBrandName){

        try {

            if(validateStockBrandName(newBrandName)){

                return false;

            }else{

                prep = conn.prepareStatement(UPDATE_BRAND_STATEMENT);
                prep.setString(1,newBrandName.getBrandName());
                prep.setInt(2,oldBrandName.getBrandId());

                prep.execute();
                conn.commit();
                prep.close();

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean updateSupplier(StockSupplier oldSupplier, StockSupplier newSupplier){

        try {

                prep = conn.prepareStatement(UPDATE_SUPPLIER_STATEMENT);
                prep.setString(1, newSupplier.getSupplierName());
                prep.setString(2, newSupplier.getSupplierAddress());
                prep.setString(3, newSupplier.getSupplierContactNumber());
                prep.setString(4, newSupplier.getSupplierEmailAddress());
                prep.setInt(5,oldSupplier.getSupplierId());


                prep.execute();
                conn.commit();
                prep.close();

                return true;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean validateStockItemName(String stockName){

        try {
            statement = conn.createStatement();

            resultSet = statement.executeQuery("SELECT "+ITEM_NAME+" FROM "+TABLE_STOCKS+" WHERE "+ITEM_NAME+" = '"+stockName+"'");


            while (resultSet.next()){

                if (resultSet.getString(ITEM_NAME).equals(stockName))

                    return true;
            }

            statement.close();
            resultSet.close();

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean validateStockItemBarcode(String stockBarcode){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(" SELECT "+ITEM_BARCODE_ID+" FROM "+TABLE_STOCKS+" WHERE "+ITEM_BARCODE_ID+" = '"+stockBarcode+"'");


            while (resultSet.next()){

                if (resultSet.getString(ITEM_BARCODE_ID).equals(stockBarcode))

                    return true;
            }

            statement.close();
            resultSet.close();

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
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


    public boolean  validateSupplierName(StockSupplier stockSupplier){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT "+SUPPLIER_NAME+" FROM "+TABLE_SUPPLIERS);

            while (resultSet.next()){

                if(resultSet.getString(SUPPLIER_NAME).equals(stockSupplier.getSupplierName())){

                    resultSet.close();
                    statement.close();
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
    public boolean  validateSupplierContact(StockSupplier stockSupplier){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT "+SUPPLIER_CONTACT_NUMBER+" FROM "+TABLE_SUPPLIERS);

            while (resultSet.next()){

                if(resultSet.getString(SUPPLIER_CONTACT_NUMBER).equals(stockSupplier.getSupplierContactNumber())){

                    resultSet.close();
                    statement.close();
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
    public boolean  validateSupplierEmail(StockSupplier stockSupplier){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT "+SUPPLIER_EMAIL_ADDRESS+" FROM "+TABLE_SUPPLIERS);

            while (resultSet.next()){

                if(resultSet.getString(SUPPLIER_EMAIL_ADDRESS).equals(stockSupplier.getSupplierEmailAddress())){

                    resultSet.close();
                    statement.close();
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




    public int getItemId(String barcodeId){

        try {
            statement = conn.createStatement();
            resultSet =  statement.executeQuery(" SELECT "+ITEM_ID+" FROM "+TABLE_STOCKS+" WHERE "+ITEM_BARCODE_ID +" = '"+barcodeId+"'");

            while (resultSet.next()){

                return resultSet.getInt(ITEM_ID);
            }

            return -1;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }
    public String getItemName(int itemId){
        try {
            statement = conn.createStatement();
            resultSet =  statement.executeQuery(" SELECT "+ITEM_NAME+" FROM "+TABLE_STOCKS+" WHERE "+ITEM_ID +" = "+itemId);

            while (resultSet.next()){

                return resultSet.getString(ITEM_NAME);
            }

            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_BRAND_NAMES
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

    public StockCategory searchStockCategory(String categoryName){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_CATEGORIES
                    +" WHERE " + CATEGORY_NAME + " = '"+categoryName+"'");

            while (resultSet.next()){

                int category_id = resultSet.getInt(CATEGORY_ID);
                String category_name = resultSet.getString(CATEGORY_NAME);
                String category_status = resultSet.getString(CATEGORY_STATUS);

                StockCategory stockCategory = new StockCategory(category_id,category_name,category_status);
                return stockCategory;

            }


            resultSet.close();
            statement.close();

            return null;

        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();
            return null;
        }

    }
    public StockType searchStockType(String typeName){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_TYPES
                    +" WHERE " + TYPE_NAME+ " = '"+typeName+"'");

            while (resultSet.next()){

                int type_id = resultSet.getInt(TYPE_ID);
                String type_name = resultSet.getString(TYPE_NAME);
                String type_status = resultSet.getString(TYPE_STATUS);

                StockType stockType = new StockType(type_id,type_name,type_status);
                return stockType;

            }

            resultSet.close();
            statement.close();

            return null;

        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();
            return null;
        }

    }
    public StockBrandName searchStockBrandName(String brandName){

        try {

            prep = conn.prepareStatement("SELECT * FROM "+TABLE_BRAND_NAMES
                    +" WHERE " + BRAND_NAME_NAME + " = ?");

            prep.setString(1,brandName);

            resultSet = prep.executeQuery();

            StockBrandName stockBrandName;
            while (resultSet.next()){

                    int brand_id = resultSet.getInt(BRAND_NAME_ID);
                    String brand_name = resultSet.getString(BRAND_NAME_NAME);
                    String brand_status = resultSet.getString(BRAND_NAME_STATUS);

                    stockBrandName = new StockBrandName(brand_id,brand_name,brand_status);
                    return stockBrandName;


            }

            resultSet.close();
            statement.close();

            return null;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();
            return null;
        }

    }
    public StockSupplier searchStockSupplier(String supplierName){

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_SUPPLIERS
                    +" WHERE " + SUPPLIER_NAME + " = '"+supplierName+"'");


            while (resultSet.next()){

                int    supplier_id = resultSet.getInt(SUPPLIER_ID);
                String supplier_name = resultSet.getString(SUPPLIER_NAME);
                String supplier_address = resultSet.getString(SUPPLIER_ADDRESS);
                String supplier_contact = resultSet.getString(SUPPLIER_CONTACT_NUMBER);
                String supplier_email = resultSet.getString(SUPPLIER_EMAIL_ADDRESS);
                String supplier_status = resultSet.getString(SUPPLIER_STATUS);

                StockSupplier stockSupplier = new StockSupplier(supplier_id,supplier_name,supplier_address,supplier_contact,supplier_email,supplier_status);

                return stockSupplier;
            }

            resultSet.close();
            statement.close();

            return null;

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


    public ArrayList<StockItem>  getItemList(){

        ArrayList<StockItem> stockItems = new ArrayList<>();
        StockItem stockItem;

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT  * FROM "+TABLE_STOCKS);

            while (resultSet.next()){

                stockItem = new StockItem(

                        resultSet.getInt(ITEM_ID),
                        resultSet.getString(ITEM_NAME),
                        resultSet.getDouble(ITEM_COST),
                        resultSet.getInt(ITEM_QTY),
                        resultSet.getString(ITEM_UNIT),
                        resultSet.getInt(ITEM_BRAND_NAME),
                        resultSet.getInt(ITEM_TYPE),
                        resultSet.getInt(ITEM_CATEGORY),
                        resultSet.getInt(ITEM_SUPPLIER),
                        resultSet.getString(ITEM_BARCODE_ID),
                        resultSet.getString(ITEM_DESCRIPTION),
                        resultSet.getString(ITEM_STATUS),
                        resultSet.getString(ITEM_LAST_UPDATED)

                );

                stockItems.add(stockItem);
            }

            resultSet.close();
            statement.close();

            return stockItems;
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }

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
    public ArrayList<StockReplenishment> getReplenishmentList(){

        ArrayList<StockReplenishment> stockReplenishments = new ArrayList<>();
        StockReplenishment stockReplenishment;

        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_STOCK_REPLENISHMENT);

            while (resultSet.next()){

                stockReplenishment = new StockReplenishment(
                        resultSet.getInt(REPLENISH_ID),
                        resultSet.getInt(REPLENISH_ITEM_ID),
                        resultSet.getInt(REPLENISH_STOCK_REMAINING),
                        resultSet.getInt(REPLENISH_STOCK_ADDED),
                        resultSet.getInt(REPLENISH_STOCK_FINAL),
                        resultSet.getString(REPLENISH_DATE)

                );

                stockReplenishments.add(stockReplenishment);
            }

            statement.close();
            resultSet.close();

            return stockReplenishments;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();
            return null;


        }

    }
    public ArrayList<Inventory> getInventoryList(){

        ArrayList<Inventory> inventories = new ArrayList<>();
        Inventory inventory;

        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_INVENTORY);

            while (resultSet.next()){

                inventory= new Inventory(
                        resultSet.getInt(INVENTORY_ID),
                        resultSet.getInt(INVENTORY_ITEM_ID),
                        resultSet.getInt(INVENTORY_INIT_STOCK),
                        resultSet.getString(INVENTORY_INIT_DATE),
                        resultSet.getInt(INVENTORY_FINAL_STOCK),
                        resultSet.getString(INVENTORY_FINAL_DATE),
                        resultSet.getInt(INVENTORY_ITEM_SALES),
                        resultSet.getString(INVENTORY_ITEM_STATUS)
                );

                inventories.add(inventory);
            }

            statement.close();
            resultSet.close();

            return inventories;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();
            return null;


        }



    }
    public ArrayList<Sales>     getInventorySales(){


        ArrayList<Sales> sales = new ArrayList<>();
        Sales sale;

        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_SALES);

            while (resultSet.next()){

                sale= new Sales(
                        resultSet.getInt(SALES_ID),
                        resultSet.getInt(SALES_INVENTORY_ID),
                        resultSet.getInt(SALES_ITEM_ID),
                        resultSet.getInt(SALES_ITEM_SALES),
                        resultSet.getDouble(SALES_GROSS),
                        resultSet.getString(SALES_INCLUSIVE_DATES)
                );

                sales.add(sale);
            }

            statement.close();
            resultSet.close();

            return sales;



        }catch (SQLException e){

            e.getMessage();
            e.printStackTrace();
            return null;


        }






    }




    public ArrayList<String> getStockItemColumnName(){


        ArrayList<String> itemColumnNames = new ArrayList<>();
        itemColumnNames.add(ITEM_ID);
        itemColumnNames.add(ITEM_NAME);
        itemColumnNames.add(ITEM_COST);
        itemColumnNames.add(ITEM_QTY);
        itemColumnNames.add(ITEM_UNIT);
        itemColumnNames.add(ITEM_BRAND_NAME);
        itemColumnNames.add(ITEM_TYPE);
        itemColumnNames.add(ITEM_CATEGORY);
        itemColumnNames.add(ITEM_SUPPLIER);
        itemColumnNames.add(ITEM_BARCODE_ID);
        itemColumnNames.add(ITEM_DESCRIPTION);
        itemColumnNames.add(ITEM_LAST_UPDATED);
        itemColumnNames.add(ITEM_STATUS);


        return itemColumnNames;

    }

    public ArrayList<String> getStockSuppliersColumnName(){

        ArrayList<String> columnNames = new ArrayList<>();

        columnNames.add(SUPPLIER_ID);
        columnNames.add(SUPPLIER_NAME);
        columnNames.add(SUPPLIER_ADDRESS);
        columnNames.add(SUPPLIER_CONTACT_NUMBER);
        columnNames.add(SUPPLIER_EMAIL_ADDRESS);
        columnNames.add(SUPPLIER_STATUS);

        return columnNames;
    }

    public ArrayList<String> getStockCategoriesColumnName(){

        ArrayList<String> columnNames = new ArrayList<>();

        columnNames.add(CATEGORY_ID);
        columnNames.add(CATEGORY_NAME);
        columnNames.add(CATEGORY_STATUS);

        return columnNames;
    }

    public ArrayList<String> getStockTypesColumnName(){

        ArrayList<String> columnNames = new ArrayList<>();

        columnNames.add(TYPE_ID);
        columnNames.add(TYPE_NAME);
        columnNames.add(TYPE_STATUS);

        return columnNames;
    }

    public ArrayList<String> getStockBrandNamesColumnName(){

        ArrayList<String> columnNames = new ArrayList<>();

        columnNames.add(BRAND_NAME_ID);
        columnNames.add(BRAND_NAME_NAME);
        columnNames.add(BRAND_NAME_STATUS);

        return columnNames;
    }

    public ArrayList<String> getInventoryColumnName(){

        ArrayList<String> columnNames = new ArrayList<>();

        columnNames.add(INVENTORY_ID);
        columnNames.add(INVENTORY_ITEM_ID);
        columnNames.add(INVENTORY_INIT_STOCK);
        columnNames.add(INVENTORY_INIT_DATE);
        columnNames.add(INVENTORY_FINAL_STOCK);
        columnNames.add(INVENTORY_FINAL_DATE);
        columnNames.add(INVENTORY_ITEM_SALES);

        return columnNames;




    }

    public ArrayList<String> getReplenishColumnName(){

        ArrayList<String> columnNames = new ArrayList<>();

        columnNames.add(REPLENISH_ID);
        columnNames.add(REPLENISH_ITEM_ID);
        columnNames.add(REPLENISH_STOCK_REMAINING);
        columnNames.add(REPLENISH_STOCK_ADDED);
        columnNames.add(REPLENISH_STOCK_FINAL);
        columnNames.add(REPLENISH_DATE);
        return columnNames;



    }

    public ArrayList<String> getSalesColumnName(){

        ArrayList<String> columnNames = new ArrayList<>();

        columnNames.add(SALES_ID);
        columnNames.add(SALES_INVENTORY_ID);
        columnNames.add(SALES_ITEM_ID);
        columnNames.add(SALES_ITEM_SALES);
        columnNames.add(SALES_GROSS);
        return columnNames;



    }

}
