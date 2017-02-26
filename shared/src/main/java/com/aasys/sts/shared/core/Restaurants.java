package com.aasys.sts.shared.core;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by kb on 2/25/17.
 */
public class Restaurants implements IsSerializable{
    private int rId;
    private String name;

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





}
