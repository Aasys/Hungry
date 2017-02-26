package com.aasys.sts.web;

import com.aasys.sts.shared.core.Cuisines;
import com.aasys.sts.shared.core.Restaurants;
import com.aasys.sts.shared.core.Tastes;
import com.aasys.sts.shared.query.RatingsInfo;
import com.aasys.sts.shared.query.RestaurantInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * Created by aasys on 2/25/2017.
 */
public interface RestaurantsServiceAsync {

    void getRestaurants(AsyncCallback<List<RestaurantInfo>> callback) throws Exception;
    void getRestaurants(String likeQuery, AsyncCallback<List<RestaurantInfo>> callback) throws Exception;

    void getRestaurants(Cuisines cuisines, AsyncCallback<List<RestaurantInfo>> async) throws Exception;

    void getRestaurants(Tastes t, AsyncCallback<List<RestaurantInfo>> callback) throws Exception;

    void getRatings(Restaurants restaurant, AsyncCallback<List<RatingsInfo>> async);
}
