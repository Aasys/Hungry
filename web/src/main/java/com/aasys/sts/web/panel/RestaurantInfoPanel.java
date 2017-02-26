package com.aasys.sts.web.panel;

import com.aasys.sts.shared.query.RestaurantInfo;
import com.aasys.sts.web.SessionCache;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialLink;

/**
 * Created by aasys on 2/25/2017.
 */
public class RestaurantInfoPanel extends Composite {
    private static RestaurantInfoPanelUiBinder uiBinder = GWT.create(RestaurantInfoPanelUiBinder.class);

    interface RestaurantInfoPanelUiBinder extends UiBinder<Widget, RestaurantInfoPanel> {
    }
    @UiField
    MaterialCard resCard;
    @UiField
    MaterialLink txtOrder;
    @UiField
    MaterialLink txtStars;

    private final RestaurantInfo restaurantInfo;

    public RestaurantInfoPanel(RestaurantInfo _restaurantInfo) {
        this.restaurantInfo = _restaurantInfo;
        initWidget(uiBinder.createAndBindUi(this));
        resCard.setTitle(restaurantInfo.getRestaurant().getName());
        resCard.setDescription(restaurantInfo.getRestaurant().getAddress());
        txtStars.setText(String.valueOf(restaurantInfo.getRatings()));
        txtOrder.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                SessionCache.setToCanvas(
                        new OrderPanel(restaurantInfo),
                        "Order - " + restaurantInfo.getRestaurant().getName()
                );
            }
        });
    }
}