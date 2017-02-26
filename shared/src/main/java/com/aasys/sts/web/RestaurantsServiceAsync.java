package com.aasys.sts.web;

import com.aasys.sts.shared.query.RestaurantInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * Created by aasys on 2/25/2017.
 */
public interface RestaurantsServiceAsync {

    void getRestaurants(AsyncCallback<List<RestaurantInfo>> callback) throws Exception;
}
