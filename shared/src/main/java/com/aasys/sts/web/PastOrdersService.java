package com.aasys.sts.web;

import com.aasys.sts.shared.query.PastOrdersInfo;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * Created by aasys on 2/25/2017.
 */
@RemoteServiceRelativePath("restaurants")
public interface PastOrdersService extends RemoteService {

    List<PastOrdersInfo> getInvoices() throws Exception;

}
