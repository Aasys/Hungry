package com.aasys.sts.web.panel;

import com.aasys.sts.shared.core.Cuisines;
import com.aasys.sts.shared.core.Tastes;
import com.aasys.sts.shared.query.MenuInfo;
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
    private final RestaurantsServiceAsync restaurantsService = GWT.create(RestaurantsService.class);

    @UiField
    MaterialRow mRow;

    @UiField
    MaterialPanel tastePanel;


    @UiField
    MaterialButton btnOrder;

    private final RestaurantInfo restaurantInfo;

    private List<MaterialRadioButton> tasteButton = new LinkedList<>();

    private boolean tasteLoaded = false;

    private List<MenuItemPanel> menuItemPanels = new LinkedList<>();

    public OrderPanel(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
        initWidget(uiBinder.createAndBindUi(this));
        populate();

        btnOrder.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                double total = 0;
                int count = 0;
                String des = "";
                for (MenuItemPanel mip:menuItemPanels) {
                    if (mip.isAddedToBad()) {
                        count ++;
                        total+=mip.getMenuInfo().getMenus().getPrice();
                        des += mip.getMenuInfo().getMenus().getName() + ";";
                    }
                }
                if (count == 0) {
                    MaterialToast.alert("No items in bag!!");
                } else {
                    MaterialToast.alert("Todo!!");
                }

            }
        });
    }

    private void populate() {
        loadTastes();
        restaurantsService.getMenu(restaurantInfo.getRestaurant(), new AsyncCallback<List<MenuInfo>>() {
            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(List<MenuInfo> menuInfos) {
                for (MenuInfo mi:menuInfos) {
                    MenuItemPanel mip = new MenuItemPanel(mi);
                    mRow.add(mip);
                    menuItemPanels.add(mip);
                }
            }
        });
    }

    MaterialRadioButton allRadioTaste;



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

    private void populateTaste(String flavor) {
        for (MenuItemPanel mip:menuItemPanels) {
            mip.toggleVisiblity(flavor);
        }
    }

    private MaterialRadioButton getRadio(String text) {
        MaterialRadioButton radioButton =  new MaterialRadioButton();
        radioButton.getElement().getStyle().setPaddingRight(30, Style.Unit.PX);
        radioButton.getElement().getStyle().setPaddingLeft(5, Style.Unit.PX);
        radioButton.setText(text);
        return radioButton;
    }
}
