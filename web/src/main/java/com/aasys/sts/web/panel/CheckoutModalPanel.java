package com.aasys.sts.web.panel;

import com.aasys.sts.shared.core.Payments;
import com.aasys.sts.web.PastOrdersService;
import com.aasys.sts.web.PastOrdersServiceAsync;
import com.aasys.sts.web.SessionCache;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by aasys on 2/25/2017.
 */
public class CheckoutModalPanel extends Composite {
    private static CheckoutModalPanelUiBinder uiBinder = GWT.create(CheckoutModalPanelUiBinder.class);

    interface CheckoutModalPanelUiBinder extends UiBinder<Widget, CheckoutModalPanel> {
    }

    private final PastOrdersServiceAsync pastOrdersService = GWT.create(PastOrdersService.class);
    @UiField
    MaterialTitle modTitle;

    @UiField
    MaterialLabel noCards;

    @UiField
    MaterialRow paymentPanel;

    @UiField
    MaterialButton btnCheckout;


    List<PaymentPanel> paymentPanels = new LinkedList<>();

    public CheckoutModalPanel(final double total, final  int count, final String des, final int rid) {
        initWidget(uiBinder.createAndBindUi(this));


        modTitle.setTitle("Checkout");
        modTitle.setDescription(count + " items totaling $" + total);
        pastOrdersService.getPayments(SessionCache.getCurrentUser(), new AsyncCallback<List<Payments>>() {
            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(List<Payments> paymentss) {
                for (Payments p:paymentss) {
                    PaymentPanel pp = new PaymentPanel(p);
                    pp.showUse(paymentPanels);
                    paymentPanel.add(pp);
                    paymentPanels.add(pp);
                }
            }
        });

        btnCheckout.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Payments selectPayment= null;
                for (PaymentPanel pp: paymentPanels) {
                    if (pp.isChecked()) {
                        selectPayment = pp.getPayment();
                        break;
                    }
                }

                if (selectPayment == null) {
                    MaterialToast.alert("No payment method!!");
                } else {
                    btnCheckout.setDisable(true);
                    pastOrdersService.processPayment(des, total,selectPayment.getCardnum(), rid, SessionCache.getCurrentUser().getUserId(), new AsyncCallback<Boolean>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            btnCheckout.setDisable(false);
                            MaterialToast.alert(throwable.getMessage());
                        }

                        @Override
                        public void onSuccess(Boolean aBoolean) {
                            MaterialModal.closeModal();
                            MaterialToast.alert("Done Processing..");
                        }
                    });
                }
            }
        });
    }
}

