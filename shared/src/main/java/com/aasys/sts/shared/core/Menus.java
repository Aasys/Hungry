package com.aasys.sts.shared.core;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by kb on 2/25/17.
 */
public class Menus implements IsSerializable {

    private int menuId;
    private String name;
    private String description;
    private double price;
    private int rId;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }



}
