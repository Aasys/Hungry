package com.aasys.sts.web.panel;

import com.aasys.sts.shared.core.User;
import com.aasys.sts.web.SessionCache;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.*;

public class HomePanel extends Composite {

    private static HomePanelUiBinder uiBinder = GWT.create(HomePanelUiBinder.class);

    interface HomePanelUiBinder extends UiBinder<Widget, HomePanel> {
    }

    RestaurantsPanel restaurantsPanel = null;
    PastOrdersPanel pastOrdersPanel = null;

    private final User _user;

    @UiField
    MaterialNavBar UI_navBar;

    @UiField
    MaterialTopNav UI_topNav;

    @UiField
    MaterialLink UI_lblEmail;

    @UiField
    MaterialContainer UI_canvas;

    @UiField
    MaterialLink lnkOrder;

    @UiField
    MaterialLink lnkPast;

    @UiField
    MaterialLink lnkAccount;

    @UiField
    MaterialLink lnkLogout;


    public HomePanel(User user) {
        initWidget(uiBinder.createAndBindUi(this));
        _user = user;

        lnkOrder.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                SessionCache.setToCanvas(restaurantsPanel, "Order");
            }
        });

        lnkPast.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if (pastOrdersPanel == null)
                    pastOrdersPanel = new PastOrdersPanel();
                SessionCache.setToCanvas(pastOrdersPanel, "Past Orders");
            }
        });

        lnkAccount.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {

            }
        });

        lnkLogout.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                SessionCache.logout();
            }
        });
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        UI_topNav.setProfileName(_user.getName());
        UI_lblEmail.setText(_user.getEmail());
        SessionCache.UI_canvas = UI_canvas;
        SessionCache.UI_navBar = UI_navBar;
        if (restaurantsPanel == null)
            restaurantsPanel = new RestaurantsPanel();
        SessionCache.setToCanvas(restaurantsPanel, "Order");
    }

}
