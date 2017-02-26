package com.aasys.sts.web;

import com.aasys.sts.shared.core.Cuisines;
import com.aasys.sts.shared.core.Tastes;
import com.aasys.sts.shared.query.RestaurantInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * Created by aasys on 2/26/2017.
 */
public interface CuisinesServiceAsync {
    void getCuisines(AsyncCallback<List<Cuisines>> async);

    void getTastes(AsyncCallback<List<Tastes>> asyncCallback);

    void getTastes(RestaurantInfo restaurantInfo, AsyncCallback<List<Tastes>> asyncCallback);
}
