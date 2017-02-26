package com.aasys.sts.shared.core;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by kb on 2/25/17.
 */
public class Restaurants implements IsSerializable{
    private int rId;
    private String name;
    private String address;
    private String phone;

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
