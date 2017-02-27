package com.aasys.sts.shared.query;

import com.aasys.sts.shared.core.Menus;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;

/**
 * Created by aasys on 2/26/2017.
 */
public class MenuInfo implements IsSerializable{
    private Menus menus;
    private List<String> flavors;

    public Menus getMenus() {
        return menus;
    }

    public void setMenus(Menus menus) {
        this.menus = menus;
    }

    public List<String> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<String> flavors) {
        this.flavors = flavors;
    }
}
