package com.aasys.sts.shared.core;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.Date;
import java.util.List;

/**
 * Created by kb on 2/25/17.
 */
public class Payments implements IsSerializable {
    private String cardnum;
    private String date;
    private int cvcnum;
    private int zipcode;
    private int userid;
    private Date expiryDate;

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCvcnum() {
        return cvcnum;
    }

    public void setCvcnum(int cvcnum) {
        this.cvcnum = cvcnum;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


}
