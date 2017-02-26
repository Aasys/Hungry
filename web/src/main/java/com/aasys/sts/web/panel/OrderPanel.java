package com.aasys.sts.web.panel;

import com.aasys.sts.shared.core.Cuisines;
import com.aasys.sts.shared.core.Tastes;
import com.aasys.sts.shared.query.RestaurantInfo;
import com.aasys.sts.shared.util.StringUtil;
import com.aasys.sts.web.CuisinesService;
import com.aasys.sts.web.CuisinesServiceAsync;
import com.aasys.sts.web.RestaurantsService;
import com.aasys.sts.web.RestaurantsServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
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
public class OrderPanel extends Composite {
    private static OrderPanelUiBinder uiBinder = GWT.create(OrderPanelUiBinder.class);

    interface OrderPanelUiBinder extends UiBinder<Widget, OrderPanel> {
    }

    private final CuisinesServiceAsync cuisinesService = GWT.create(CuisinesService.class);

    @UiField
    MaterialColumn mCol;

    @UiField
    MaterialPanel tastePanel;

    private final RestaurantInfo restaurantInfo;

    private List<MaterialRadioButton> tasteButton = new LinkedList<>();

    private boolean tasteLoaded = false;

    public OrderPanel(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
        initWidget(uiBinder.createAndBindUi(this));
        populate();
    }

    private void populate() {
        loadTastes();
    }

    MaterialRadioButton allRadioTaste;

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



    private void loadTastes() {
        //load cuisines
        if (!tasteLoaded) {
            tasteLoaded = true;
            tastePanel.clear();
            allRadioTaste = getRadio("All");
            tastePanel.add(allRadioTaste);
            allRadioTaste.setChecked(true);
            allRadioTaste.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    for (MaterialRadioButton r:tasteButton) {
                        r.setChecked(false);
                    }
                    populateTaste(null);
                }
            });

            cuisinesService.getTastes(restaurantInfo, new AsyncCallback<List<Tastes>>() {
                @Override
                public void onFailure(Throwable throwable) {

                }

                @Override
                public void onSuccess(List<Tastes> tastes) {
                    for (Tastes t : tastes) {
                        final Tastes tf = t;
                        final MaterialRadioButton opt = new MaterialRadioButton(t.getFlavor());
                        opt.addClickHandler(new ClickHandler() {
                            @Override
                            public void onClick(ClickEvent clickEvent) {
                                allRadioTaste.setChecked(false);
                                for (MaterialRadioButton r:tasteButton) {
                                    if (r != opt)
                                        r.setChecked(false);
                                }
                                populateTaste(tf.getFlavor());
                            }
                        });
                        opt.setText(t.getFlavor());
                        tastePanel.add(opt);
                        tasteButton.add(opt);
                    }
                    tasteLoaded = true;
                }
            });
        }
    }

    private void populateTaste(String o) {

    }

    private MaterialRadioButton getRadio(String text) {
        MaterialRadioButton radioButton =  new MaterialRadioButton();
        radioButton.getElement().getStyle().setPaddingRight(30, Style.Unit.PX);
        radioButton.getElement().getStyle().setPaddingLeft(5, Style.Unit.PX);
        radioButton.setText(text);
        return radioButton;
    }
}
