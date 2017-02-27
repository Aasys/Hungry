package com.aasys.sts.web;

import com.aasys.sts.shared.core.Cuisines;
import com.aasys.sts.shared.core.Restaurants;
import com.aasys.sts.shared.core.Tastes;
import com.aasys.sts.shared.query.MenuInfo;
import com.aasys.sts.shared.query.RatingsInfo;
import com.aasys.sts.shared.query.RestaurantInfo;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * Created by aasys on 2/25/2017.
 */
@RemoteServiceRelativePath("restaurants")
public interface RestaurantsService extends RemoteService {

    List<RestaurantInfo> getRestaurants() throws Exception;
    List<RestaurantInfo> getRestaurants(String likeQuery) throws Exception;
    List<RestaurantInfo> getRestaurants(Cuisines cuisines) throws Exception;

    List<RestaurantInfo> getRestaurants(Tastes t) throws Exception;

    List<RatingsInfo> getRatings(Restaurants restaurant) throws Exception;

    List<MenuInfo> getMenu(Restaurants restaurants) throws Exception;
}
