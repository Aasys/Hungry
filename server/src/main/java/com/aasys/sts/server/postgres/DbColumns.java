package com.aasys.sts.server.postgres;

/**
 * Created by aasys on 2/25/2017.
 */
public class DbColumns {
    public static final String USERS = "users";
    public static final String USERS_ID = "userid";
    public static final String USERS_NAME = "name";
    public static final String USERS_ADDRESS = "address";
    public static final String USERS_NAME = "name";
    public static final String USERS_PHONENUM = "123456789";
    public static final String USERS_EMAIL = "name@name.coom";
    public static final String USERS_PASSWD = "passwd";

    public static final String PAYMENTS_CARDNUM = "123456";
    public static final String PAYMENTS_EXPDATE = "FEB-17-2017";
    public static final String PAYMENTS_CVCNUM = "0123";
    public static final String PAYMENTS_ZIPCODE = "53412";
    public static final String PAYMENTS_USERID = "1";

    public static final String CUISINES_NAME = "GENERIC";

    public static final String TASTES_FLAVOR = "BLAND";

    public static final String RESTAURANTS_ID = "1";
    public static final String RESTAURANTS_NAME = "resname";
    public static final String RESTAURANTS_ADDRESS = "address";
    public static final String RESTAURANTS_PHONENUM = "123456789";

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
