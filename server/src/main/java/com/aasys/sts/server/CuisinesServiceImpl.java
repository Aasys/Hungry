package com.aasys.sts.server;

import com.aasys.sts.server.postgres.DbColumns;
import com.aasys.sts.server.postgres.PostgreSQLJDBC;
import com.aasys.sts.shared.core.Cuisines;
import com.aasys.sts.shared.core.Tastes;
import com.aasys.sts.shared.core.User;
import com.aasys.sts.shared.query.RestaurantInfo;
import com.aasys.sts.web.CuisinesService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by aasys on 2/26/2017.
 */
@SuppressWarnings("serial")
public class CuisinesServiceImpl   extends RemoteServiceServlet implements CuisinesService {
    @Override
    public List<Cuisines> getCuisines() throws Exception {
        Connection connection = PostgreSQLJDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM cuisines ORDER BY name ASC;");

        List<Cuisines> cuisines =  new LinkedList<>();
        while (rs.next()) {
            Cuisines c = new Cuisines();
            c.setName(rs.getString(DbColumns.CUISINES_NAME));
            cuisines.add(c);
        }

        rs.close();
        return cuisines;
    }

    @Override
    public List<Tastes> getTastes() throws Exception {
        Connection connection = PostgreSQLJDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM tastes ORDER BY flavor ASC;");

        List<Tastes> tastes =  new LinkedList<>();
        while (rs.next()) {
            Tastes c = new Tastes();
            c.setFlavor(rs.getString(DbColumns.TASTES_FLAVOR));
            tastes.add(c);
        }

        rs.close();
        return tastes;
    }

    public static final String RES_TASTE_QUERY = "SELECT DISTINCT menustaste.flavor " +
            "FROM menustaste " +
            "INNER JOIN menus " +
            "ON menustaste.menuid = menus.menuid " +
            "WHERE menus.rid = $1 " +
            "ORDER BY menustaste.flavor ASC ; ";
    @Override
    public List<Tastes> getTastes(RestaurantInfo restaurantInfo) throws Exception {
        Connection connection = PostgreSQLJDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(RES_TASTE_QUERY.replace("$1", String.valueOf(restaurantInfo.getRestaurant().getrId())));

        List<Tastes> tastes =  new LinkedList<>();
        while (rs.next()) {
            Tastes c = new Tastes();
            c.setFlavor(rs.getString(DbColumns.TASTES_FLAVOR));
            tastes.add(c);
        }

        rs.close();
        return tastes;
    }
}
