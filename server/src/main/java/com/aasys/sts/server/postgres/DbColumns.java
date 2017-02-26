package com.aasys.sts.server.postgres;

/**
 * Created by aasys on 2/25/2017.
 */
public class DbColumns {
    public static final String USERS = "users";
    public static final String USERS_ID = "userid";
    public static final String USERS_NAME = "name";
    public static final String USERS_ADDRESS = "address";
    public static final String USERS_PHONENUM = "phonenum";
    public static final String USERS_EMAIL = "rmail";
    public static final String USERS_PASSWD = "passwd";

    public static final String PAYMENTS_CARDNUM = "cardnum";
    public static final String PAYMENTS_EXPDATE = "expdate";
    public static final String PAYMENTS_CVCNUM = "cvcnum";
    public static final String PAYMENTS_ZIPCODE = "zipcode";
    public static final String PAYMENTS_USERID = "userid";

    public static final String CUISINES_NAME = "name";

    public static final String TASTES_FLAVOR = "flavor";

    public static final String RESTAURANTS_ID = "rid";
    public static final String RESTAURANTS_NAME = "name";
    public static final String RESTAURANTS_ADDRESS = "address";
    public static final String RESTAURANTS_PHONENUM = "phonenumber";

    public static final String RESTAURANTSCUISINE_ID = "1";
    public static final String RESTAURANTSCUISINE_NAME = "rescusname";

    public static final String MENUS_MENUID = "1";
    public static final String MENUS_NAME = "resname";
    public static final String MENUS_DESCRIPTION = "address";
    public static final String MENUS_PRICE = "123456789";
    public static final String MENUS_RID = "0";

    public static final String MENUSTASTE_MENUID = "1";
    public static final String MENUSTASTE_FLAVOR = "menutasteflavor";

    public static final String RATINGS_RAID = "1";
    public static final String RATINGS_STARS = "resname";
    public static final String RATINGS_COMMENTS = "address";
    public static final String RATINGS_COMDATE = "123456789";
    public static final String RATINGSS_RID = "0";

    public static final String INVOICE_TID = "1";
    public static final String INVOICE_DATE = "FEB-17-2017";
    public static final String INVOICE_DESCRIPTION = "invoicedesc";
    public static final String INVOICE_AMOUNT= "0";
    public static final String INVOICES_CARDNUM = "0";
    public static final String INVOICE_USERID = "1";
    public static final String INVOICE_RID = "1";


}
