package com.aasys.sts.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aasys on 10/27/15.
 */
public class User implements IsSerializable {
    private String _fullName;
    private String _email;

    public User(String email) {
        _email = email;
    }



    public User() {
    }

    public void setEmail(String email) {
        _email = email;
    }

    public void setFullName(String fullName) {
        _fullName = fullName;
    }

    public String getEmail() {
        return _email;
    }

    public String getFullName() {
        return _fullName;
    }

}
