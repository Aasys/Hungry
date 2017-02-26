package com.aasys.sts.shared.core;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by kb on 2/25/17.
 */
public class Cuisines  implements IsSerializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
