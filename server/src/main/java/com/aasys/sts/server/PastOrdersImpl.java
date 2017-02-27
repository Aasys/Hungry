package com.aasys.sts.server;

import com.aasys.sts.server.postgres.DbColumns;
import com.aasys.sts.server.postgres.PostgreSQLJDBC;
import com.aasys.sts.shared.core.Invoices;
import com.aasys.sts.shared.core.Payments;
import com.aasys.sts.shared.core.User;
import com.aasys.sts.shared.query.PastOrdersInfo;
import com.aasys.sts.web.PastOrdersService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class PastOrdersImpl extends RemoteServiceServlet implements PastOrdersService {

    private static final String PASTORDERS_QUERY_TEST = "SELECT invoices.tid,invoices.decription,invoices.amount,invoices.invdate, restaurants.name " +
            "FROM invoices " +
            "LEFT OUTER JOIN restaurants " +
            "ON invoices.rid = restaurants.rid " +
            "where invoices.userid =1;";

    private static final String PASTORDERS_QUERY = "SELECT invoices.tid,invoices.decription,invoices.amount,invoices.invdate, restaurants.name " +
            "FROM invoices " +
            "LEFT OUTER JOIN restaurants " +
            "ON invoices.rid = restaurants.rid " +
            "where invoices.userid =$1 ORDER BY invoices.invdate DESC;";

    private static final String USER_PAYMENTS = "select * From payments WHERE userid=$1;";

    public List<PastOrdersInfo> getInvoices() throws Exception {
        Connection connection = PostgreSQLJDBC.getConnection();
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(PASTORDERS_QUERY_TEST);
        return parseResult(rs);
    }

    private List<PastOrdersInfo> parseResult(ResultSet rs) throws Exception {
        List<PastOrdersInfo> pastOrderInfos = new LinkedList<>();

        while (rs.next()) {
            PastOrdersInfo pastOrdersInfo = new PastOrdersInfo();
            Invoices invoices = new Invoices();

            invoices.settId(rs.getInt(DbColumns.INVOICE_TID));

            invoices.setDate(rs.getString(DbColumns.INVOICE_DATE));
            invoices.setDescription(rs.getString(DbColumns.INVOICE_DESCRIPTION));
            invoices.setAmount(rs.getInt(DbColumns.INVOICE_AMOUNT));
            invoices.setResname(rs.getString(DbColumns.RESTAURANTS_NAME));


            pastOrdersInfo.setInvoices(invoices);
            pastOrderInfos.add(pastOrdersInfo);


        }
        rs.close();
        return pastOrderInfos;
    }

    @Override
    public List<PastOrdersInfo> getInvoices(User user) throws Exception {
        Connection connection = PostgreSQLJDBC.getConnection();
        Statement statement = connection.createStatement();


        ResultSet rs = statement.executeQuery(PASTORDERS_QUERY.replace("$1", Integer.toString(user.getUserId())));

        return parseResult(rs);
    }

    @Override
    public List<Payments> getPayments(User user) throws Exception {

        Connection connection = PostgreSQLJDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(USER_PAYMENTS.replace("$1", String.valueOf(user.getUserId())));

        List<Payments> paymentss = new LinkedList<>();

        while (rs.next()) {
            Payments p = new Payments();

            p.setUserid(user.getUserId());
            p.setCardnum(rs.getString(DbColumns.PAYMENTS_CARDNUM));
            p.setCvcnum(rs.getInt(DbColumns.PAYMENTS_CVCNUM));
            p.setZipcode(rs.getInt(DbColumns.PAYMENTS_ZIPCODE));
            p.setExpiryDate(rs.getDate(DbColumns.PAYMENTS_EXPDATE));

            paymentss.add(p);
        }
        rs.close();
        return paymentss;
    }

    private static final String INSERT_INVOICE = "INSERT INTO invoices (tid, invdate, decription, amount, cardnum, userid, rid) VALUES ($1, '$2', '$3', $4, '$5', $6, $7);";
    private static final String MAX_TID = "select max(invoices.tid) From invoices;";

    @Override
    public boolean processPayment(String des, double total, String cardnum, int rid, int userid) throws Exception {

        Connection connection = PostgreSQLJDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(MAX_TID);
        int newID = 0;
        if (rs.next())
            newID = rs.getInt("max");
        newID++;
        rs.close();


        Statement statement2 = connection.createStatement();
        return statement.execute(INSERT_INVOICE.replace("$1", String.valueOf(newID))
                .replace("$2", new Date().toString())
                .replace("$3", des)
                .replace("$4", String.valueOf(total))
                .replace("$5", cardnum)
                .replace("$6", String.valueOf(userid))
                .replace("$7", String.valueOf(rid)));
    }
}
