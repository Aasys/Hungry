package com.aasys.sts.server;

import com.aasys.sts.server.postgres.DbColumns;
import com.aasys.sts.server.postgres.PostgreSQLJDBC;
import com.aasys.sts.shared.core.Cuisines;
import com.aasys.sts.shared.core.Restaurants;
import com.aasys.sts.shared.core.Tastes;
import com.aasys.sts.shared.query.RestaurantInfo;
import com.aasys.sts.shared.util.StringUtil;
import com.aasys.sts.web.RestaurantsService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by aasys on 2/25/2017.
 */
@SuppressWarnings("serial")
public class ResturantsServiceImpl  extends RemoteServiceServlet implements RestaurantsService {
    private static final String RESTURNATS_QUERY = "SELECT restaurants.rid as rid, name, address, phonenumber, avgstars " +
            "FROM restaurants " +
            "LEFT OUTER JOIN " +
            "(SELECT rid, avg(stars) AS avgstars " +
            "FROM ratings " +
            "GROUP BY rid) AS stars " +
            "ON stars.rid = restaurants.rid " +
            "ORDER BY name ASC;";

    private static final String RESTAURANTS_LIKE_QUERY = "SELECT restaurants.rid as rid, name, address, phonenumber, avgstars " +
            "FROM restaurants " +
            "LEFT OUTER JOIN " +
            "(SELECT rid, avg(stars) AS avgstars " +
            "FROM ratings " +
            "GROUP BY rid) AS stars " +
            "ON stars.rid = restaurants.rid " +
            "WHERE name LIKE '%$1%' " +
            "OR restaurants.address LIKE '%$1%' " +
            "ORDER BY name ASC;";


    private static final String RWSTAURANTS_CUISINE_QUERY = "SELECT restaurants.rid as rid, restaurants.name, address, phonenumber, avgstars " +
            "FROM restaurants " +
            "LEFT OUTER JOIN " +
            "(SELECT rid, avg(stars) AS avgstars " +
            "FROM ratings " +
            "GROUP BY rid) AS stars " +
            "ON stars.rid = restaurants.rid " +
            "INNER JOIN restaurantcuisine " +
            "ON restaurants.rid = restaurantcuisine.rid " +
            "WHERE restaurantcuisine.name='$1' " +
            "ORDER BY restaurants.name ASC;";

    private static final String RESTURANTS_TASTE_QUERY = "SELECT restaurants.rid as rid, restaurants.name, address, phonenumber, avgstars " +
            "FROM restaurants " +
            "LEFT OUTER JOIN " +
            "(SELECT rid, avg(stars) AS avgstars " +
            "FROM ratings " +
            "GROUP BY rid) AS stars " +
            "ON stars.rid = restaurants.rid " +
            "INNER JOIN menus " +
            "ON restaurants.rid = menus.rid " +
            "INNER JOIN menustaste " +
            "ON menus.menuid = menustaste.menuid " +
            "WHERE menustaste.flavor = '$1' " +
            "ORDER BY restaurants.name ASC;";

    @Override
    public List<RestaurantInfo> getRestaurants() throws Exception {
        Connection connection = PostgreSQLJDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(RESTURNATS_QUERY);

        return parseResult(rs);
    }
    @Override
    public List<RestaurantInfo> getRestaurants(String likeQuery) throws Exception {
        Connection connection = PostgreSQLJDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(RESTAURANTS_LIKE_QUERY.replace("$1", likeQuery));

        return parseResult(rs);
    }

    @Override
    public List<RestaurantInfo> getRestaurants(Cuisines cuisines) throws Exception {
        if (cuisines == null || StringUtil.isEmptyOrNull(cuisines.getName()))
            return getRestaurants();
        Connection connection = PostgreSQLJDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(RWSTAURANTS_CUISINE_QUERY.replace("$1", cuisines.getName()));

        return parseResult(rs);
    }

    @Override
    public List<RestaurantInfo> getRestaurants(Tastes t) throws Exception {
        if (t == null || StringUtil.isEmptyOrNull(t.getFlavor()))
            return getRestaurants();
        Connection connection = PostgreSQLJDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(RESTURANTS_TASTE_QUERY.replace("$1", t.getFlavor()));

        return parseResult(rs);
    }

    private List<RestaurantInfo> parseResult(ResultSet rs) throws Exception {
        List<RestaurantInfo> restaurantInfos = new LinkedList<>();

        while (rs.next()) {
            RestaurantInfo restaurantInfo = new RestaurantInfo();
            Restaurants restaurant = new Restaurants();

            restaurant.setrId(rs.getInt(DbColumns.RESTAURANTS_ID));
            restaurant.setName(rs.getString(DbColumns.RESTAURANTS_NAME));
            restaurant.setAddress(rs.getString(DbColumns.RESTAURANTS_ADDRESS));
            restaurant.setPhone(rs.getString(DbColumns.RESTAURANTS_PHONENUM));
            restaurantInfo.setRatings(rs.getDouble("avgstars"));
            restaurantInfo.setRestaurant(restaurant);
            restaurantInfos.add(restaurantInfo);
        }
        rs.close();
        return restaurantInfos;
    }
}
