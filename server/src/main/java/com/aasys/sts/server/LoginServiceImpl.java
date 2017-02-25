package com.aasys.sts.server;

import com.aasys.sts.shared.LoginUser;
import com.aasys.sts.shared.User;
import com.aasys.sts.web.LoginService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aasys on 10/27/15.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
    //TODO; robust this and storage of username password
    @Override
    public User loginServer(LoginUser loginUser) throws Exception {
        String userFullName = USER_MAP.get(loginUser.getEmail());
        if (userFullName != null) {
            if (loginUser.getPassword().equals("password")) {
                User user = new User(loginUser.getEmail());
                user.setFullName(userFullName);
                return user;
            }
        }
        throw new IllegalArgumentException("Invalid email/password!");
    }


    private static Map<String, String> USER_MAP = new HashMap<>();
    static {
        USER_MAP.put("mail@aasys.co", "Aasys Sresta");
    }
}
