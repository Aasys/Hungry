package com.aasys.sts.shared.query;

import com.aasys.sts.shared.core.Restaurants;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;

/**
 * Created by aasys on 2/25/2017.
 */
public class RestaurantInfo implements IsSerializable {
    private Restaurants restaurant;
    private List<String> cuisines;
    private double ratings;

    public Restaurants getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurants restaurant) {
        this.restaurant = restaurant;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }
}
