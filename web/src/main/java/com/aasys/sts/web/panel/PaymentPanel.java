package com.aasys.sts.web.panel;

import com.aasys.sts.shared.core.Payments;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCheckBox;

import java.util.List;

/**
 * Created by aasys on 2/25/2017.
 */
public class PaymentPanel extends Composite {
    private static PaymentPanelUiBinder uiBinder = GWT.create(PaymentPanelUiBinder.class);

    public void setChecked(boolean checked) {
        chkOrder.setChecked(checked);
    }


    interface PaymentPanelUiBinder extends UiBinder<Widget, PaymentPanel> {
    }

    @UiField
    MaterialCard resCard;
    @UiField
    MaterialCheckBox chkOrder;

    private final Payments payments;

    List<PaymentPanel> paymentPanels;
    PaymentPanel thisPanel;

    public PaymentPanel(Payments _payment) {
        payments = _payment;
        initWidget(uiBinder.createAndBindUi(this));
        thisPanel = this;
        resCard.setTitle("********* " + _payment.getCardnum().substring(_payment.getCardnum().length() - 4));
        resCard.setDescription("Expiry Date: " + _payment.getExpiryDate().toString());
        chkOrder.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if (chkOrder.isChecked()) {
                    for (PaymentPanel pp : paymentPanels) {
                        if (pp != thisPanel) {
                            pp.setChecked(false);
                        }
                    }
                }
            }
        });
    }


    public boolean isChecked() {
        return chkOrder.isChecked();
    }

    public Payments getPayment() {
        return payments;
    }


    public void showUse(List<PaymentPanel> paymentPanels) {
        chkOrder.setVisible(true);
        this.paymentPanels = paymentPanels;
    }
}

