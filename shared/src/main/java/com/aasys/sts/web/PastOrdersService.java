package com.aasys.sts.web;

import com.aasys.sts.shared.core.Payments;
import com.aasys.sts.shared.core.User;
import com.aasys.sts.shared.query.PastOrdersInfo;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * Created by aasys on 2/25/2017.
 */
@RemoteServiceRelativePath("pastorder")
public interface PastOrdersService extends RemoteService {

    List<PastOrdersInfo> getInvoices(User user) throws Exception;

    List<Payments> getPayments(User user) throws Exception;


    boolean processPayment(String des, double total, String cardnum, int rid, int userid) throws Exception;
}
