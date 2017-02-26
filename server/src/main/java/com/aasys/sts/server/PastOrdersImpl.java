package com.aasys.sts.server;

import com.aasys.sts.server.postgres.PostgreSQLJDBC;
import com.aasys.sts.shared.query.PastOrdersInfo;
import com.aasys.sts.web.PastOrdersService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@SuppressWarnings("serial")
public class PastOrdersImpl extends RemoteServiceServlet implements PastOrdersService {

    private static final String RESTURNATS_QUERY = "SELECT invoices.tid as tid, name, date, description, amount " +
            "FROM invoices ";


    public List<PastOrdersInfo> getInvoices() throws Exception {
        Connection connection = PostgreSQLJDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(RESTURNATS_QUERY);
        return null;//parseResult(rs);
    }

}
