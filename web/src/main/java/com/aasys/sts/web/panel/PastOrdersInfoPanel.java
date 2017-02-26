package com.aasys.sts.web.panel;

import com.aasys.sts.shared.core.Invoices;
import com.aasys.sts.shared.query.PastOrdersInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialLink;

/**
 * Created by aasys on 2/25/2017.
 */
public class PastOrdersInfoPanel extends Composite {
    private static PastOrdersInfoPanelUiBinder uiBinder = GWT.create(PastOrdersInfoPanelUiBinder.class);

    interface PastOrdersInfoPanelUiBinder extends UiBinder<Widget, PastOrdersInfoPanel> {
    }
    @UiField
    MaterialCard resCard;
    @UiField
    MaterialLink txtOrder;
    @UiField
    MaterialLink txtStars;

    private final PastOrdersInfo pastOrdersInfo;

    public PastOrdersInfoPanel(PastOrdersInfo pastOrdersInfo) {
        initWidget(uiBinder.createAndBindUi(this));
        this.pastOrdersInfo = pastOrdersInfo;

        resCard.setTitle(pastOrdersInfo.getInvoices().getResname());
        resCard.setDescription(pastOrdersInfo.getInvoices().getDescription());
        //txtStars.setText(String.valueOf(restaurantInfo.getRatings()));
    }
}

