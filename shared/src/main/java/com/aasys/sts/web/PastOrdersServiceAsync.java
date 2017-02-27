package com.aasys.sts.web;

import com.aasys.sts.shared.core.User;
import com.aasys.sts.shared.query.PastOrdersInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * Created by aasys on 2/25/2017.
 */
public interface PastOrdersServiceAsync {


    void getInvoices(User user, AsyncCallback<List<PastOrdersInfo>> async);


}
