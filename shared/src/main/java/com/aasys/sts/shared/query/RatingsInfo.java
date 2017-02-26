package com.aasys.sts.shared.query;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.Date;

/**
 * Created by aasys on 2/26/2017.
 */
public class RatingsInfo implements IsSerializable {
    private int stars;
    private String comment;
    private Date comdate;
    private String userName;

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getComdate() {
        return comdate;
    }

    public void setComdate(Date comdate) {
        this.comdate = comdate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
