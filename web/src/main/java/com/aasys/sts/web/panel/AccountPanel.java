package com.aasys.sts.web.panel;

import com.aasys.sts.shared.core.User;
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
import gwt.material.design.client.ui.MaterialRow;

import java.util.List;

/**
 * Created by kb on 2/26/17.
 */
public class AccountPanel extends Composite {
    private static AccountPanelUiBinder uiBinder = GWT.create(AccountPanelUiBinder.class);

    interface AccountPanelUiBinder extends UiBinder<Widget, AccountPanel> {
    }

    private final RestaurantsServiceAsync restaurantsService = GWT.create(RestaurantsService.class);

    @UiField
    MaterialRow mRow;

    private final User user;

    public AccountPanel(User _user) {
        user = _user;
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
                mRow.clear();
                for (RatingsInfo ri : ratingsInfos) {
                    mRow.add(new RatingsCard(ri));
                }
            }
        };

        try {
            //restaurantsService.getRatings(restaurantInfo.getRestaurant(), callback);
        } catch (Exception e) {
//            /e.printStackTrace();
        }
    }

}
