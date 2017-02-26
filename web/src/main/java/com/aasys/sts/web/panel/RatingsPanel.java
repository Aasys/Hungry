package com.aasys.sts.web.panel;

import com.aasys.sts.shared.query.RatingsInfo;
import com.aasys.sts.shared.query.RestaurantInfo;
import com.aasys.sts.web.RestaurantsService;
import com.aasys.sts.web.RestaurantsServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.MaterialColumn;

import java.util.List;

/**
 * Created by kb on 2/26/17.
 */
public class RatingsPanel extends Composite {
    private static RatingsPanelUiBinder uiBinder = GWT.create(RatingsPanelUiBinder.class);

    interface RatingsPanelUiBinder extends UiBinder<Widget, RatingsPanel> {
    }

    private final RestaurantsServiceAsync restaurantsService = GWT.create(RestaurantsService.class);

    @UiField
    MaterialColumn mCol;

    private final RestaurantInfo restaurantInfo;

    public RatingsPanel(RestaurantInfo _restaurantInfo) {
        restaurantInfo = _restaurantInfo;
        initWidget(uiBinder.createAndBindUi(this));
        populate();
    }

    private void populate() {
        AsyncCallback<List<RatingsInfo>> callback = new AsyncCallback<List<RatingsInfo>>() {
            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(List<RatingsInfo> ratingsInfos) {
                mCol.clear();
                for (RatingsInfo ri : ratingsInfos) {
                    mCol.add(new RatingsCard(ri));
                }
            }
        };

        try {
            restaurantsService.getRatings(restaurantInfo.getRestaurant(), callback);
        } catch (Exception e) {
//            /e.printStackTrace();
        }
    }

}
