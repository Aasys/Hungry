package com.aasys.sts.shared.core;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by kb on 2/25/17.
 */

public class Ratings implements IsSerializable {

    private int raId;
    private int stars;
    private String comments;
    private String date;
    private int userId;
    private int rId;

    public int getRaId() {
        return raId;
    }

    public void setRaId(int raId) {
        this.raId = raId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }








}
