package com.aasys.sts.web.panel;

import com.aasys.sts.shared.query.RatingsInfo;
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
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;

/**
 * Created by aasys on 2/25/2017.
 */
public class RatingsCard extends Composite {
    private static RatingsCardUiBinder uiBinder = GWT.create(RatingsCardUiBinder.class);

    interface RatingsCardUiBinder extends UiBinder<Widget, RatingsCard> {
    }
    @UiField
    MaterialCard resCard;
    @UiField
    MaterialLink txtStars;

    private final RatingsInfo ratingsInfo;

    public RatingsCard(RatingsInfo _ratingsInfo) {
        this.ratingsInfo = _ratingsInfo;
        initWidget(uiBinder.createAndBindUi(this));
        resCard.setTitle(ratingsInfo.getUserName() + " on " + ratingsInfo.getComdate().toString());
        resCard.setDescription(ratingsInfo.getComment());
        txtStars.setText(String.valueOf(ratingsInfo.getStars()));

    }
}