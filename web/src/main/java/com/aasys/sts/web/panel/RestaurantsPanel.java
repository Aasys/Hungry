package com.aasys.sts.web.panel;

import com.aasys.sts.shared.query.RestaurantInfo;
import com.aasys.sts.shared.util.StringUtil;
import com.aasys.sts.web.RestaurantsService;
import com.aasys.sts.web.RestaurantsServiceAsync;
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
 * Created by aasys on 2/25/2017.
 */
public class RestaurantsPanel extends Composite {
    private static ResturatnsPanelUiBinder uiBinder = GWT.create(ResturatnsPanelUiBinder.class);

    interface ResturatnsPanelUiBinder extends UiBinder<Widget, RestaurantsPanel> {
    }

    private final RestaurantsServiceAsync restaurantsService = GWT.create(RestaurantsService.class);

    @UiField
    MaterialColumn mCol;

    @UiField
    MaterialTextBox txtSearch;

    public RestaurantsPanel() {
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
        AsyncCallback<List<RestaurantInfo>> callback = new AsyncCallback<List<RestaurantInfo>>() {
            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(List<RestaurantInfo> restaurantInfos) {
                mCol.clear();
                for (RestaurantInfo resInfo : restaurantInfos) {
                    mCol.add(new RestaurantInfoPanel(resInfo));
                }
            }
        };

        try {
            if (StringUtil.isEmptyOrNull(txtSearch.getText())) {
                restaurantsService.getRestaurants(callback);
            } else {
                restaurantsService.getRestaurants(txtSearch.getText(), callback);
            }
        } catch (Exception e) {
//            /e.printStackTrace();
        }
    }

}
