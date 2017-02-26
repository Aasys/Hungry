package com.aasys.sts.server;

import com.aasys.sts.server.postgres.DbColumns;
import com.aasys.sts.server.postgres.PostgreSQLJDBC;
import com.aasys.sts.shared.query.PastOrdersInfo;
import com.aasys.sts.web.PastOrdersService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.aasys.sts.shared.core.Invoices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

@SuppressWarnings("serial")
public class PastOrdersImpl extends RemoteServiceServlet implements PastOrdersService {

    private static final String PASTORDERS_QUERY = "SELECT invoices.tid,invoices.decription,invoices.amount,invoices.invdate, restaurants.name " +
            "FROM invoices " +
            "LEFT OUTER JOIN restaurants " +
            "ON invoices.rid = restaurants.rid " +
            "where invoices.userid =1;";


    public List<PastOrdersInfo> getInvoices() throws Exception {
        Connection connection = PostgreSQLJDBC.getConnection();
        Statement statement = connection.createStatement();
        System.out.println(PASTORDERS_QUERY);
        ResultSet rs = statement.executeQuery(PASTORDERS_QUERY);
       return parseResult(rs);
    }

    private List<PastOrdersInfo> parseResult(ResultSet rs) throws Exception {
        List<PastOrdersInfo> pastOrderInfos = new LinkedList<>();

        while (rs.next()) {
            PastOrdersInfo pastOrdersInfo = new PastOrdersInfo();
            Invoices invoices = new Invoices();

            invoices.settId(rs.getInt(DbColumns.INVOICE_TID));
            System.out.println(invoices.gettId());
            invoices.setDate(rs.getString(DbColumns.INVOICE_DATE));
            invoices.setDescription(rs.getString(DbColumns.INVOICE_DESCRIPTION));
            invoices.setAmount(rs.getInt(DbColumns.INVOICE_AMOUNT));
            invoices.setResname(rs.getString(DbColumns.RESTAURANTS_NAME));
            System.out.println(invoices.getResname());


            pastOrdersInfo.setInvoices(invoices);
            pastOrderInfos.add(pastOrdersInfo);


        }
        rs.close();
        return pastOrderInfos;
    }
}
