package com.aasys.sts.shared.query;

import com.aasys.sts.shared.core.Invoices;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;

/**
 * Created by aasys on 2/25/2017.
 */
public class PastOrdersInfo implements IsSerializable {
    private Invoices invoice;

    public Invoices getInvoices() {
        return invoice;
    }

}
