package com.aasys.sts.web.panel;

import com.aasys.sts.shared.query.PastOrdersInfo;
import com.aasys.sts.shared.util.StringUtil;
import com.aasys.sts.web.PastOrdersService;
import com.aasys.sts.web.PastOrdersServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialTextBox;

import java.util.List;

/**
 * Created by kb on 2/26/17.
 */
public class PastOrdersPanel extends Composite {
    private static PastOrdersPanel uiBinder = GWT.create(PastOrdersService.class);

    interface PastOrdersPanlUiBinder extends UiBinder<Widget, PastOrdersPanel> {
    }

    private final PastOrdersServiceAsync pastOrdersService = GWT.create(PastOrdersService.class);

    @UiField
    MaterialColumn mCol;

    @UiField
    MaterialTextBox txtSearch;

    public PastOrdersPanel() {
        initWidget(uiBinder.createAndBindUi(this));
        populate();

        txtSearch.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent keyUpEvent) {
                if (keyUpEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER || StringUtil.isEmptyOrNull(txtSearch.getText())) {
                    populate();
                }
            }
        });
    }

    private void populate() {
        AsyncCallback<List<PastOrdersInfo>> callback = new AsyncCallback<List<PastOrdersInfo>>() {
            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(List<PastOrdersInfo> pastOrdersInfos) {
                mCol.clear();
                for (PastOrdersInfo pesInfo : pastOrdersInfos) {
                    mCol.add(new PastOrdersInfoPanel(pesInfo));
                }
            }
        };

        try {
            if (StringUtil.isEmptyOrNull(txtSearch.getText())) {
                pastOrdersService.getInvoices(callback);
            } else {
               // pastOrdersService.getInvoices(txtSearch.getText(), callback);
            }
        } catch (Exception e) {
//            /e.printStackTrace();
        }
    }

}
