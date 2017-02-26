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
public class RestaurantsPanel extends Composite {
    private static ResturatnsPanelUiBinder uiBinder = GWT.create(ResturatnsPanelUiBinder.class);

    interface ResturatnsPanelUiBinder extends UiBinder<Widget, RestaurantsPanel> {
    }

    private final RestaurantsServiceAsync restaurantsService = GWT.create(RestaurantsService.class);
    private final CuisinesServiceAsync cuisinesService = GWT.create(CuisinesService.class);

    @UiField
    MaterialColumn mCol;

    @UiField
    MaterialTextBox txtSearch;

    @UiField
    MaterialPanel cuisinePanel;

    @UiField
    MaterialLink selectCuisine;

    @UiField
    MaterialPanel tastePanel;

    private Cuisines searchCuisine = new Cuisines();
    private List<MaterialRadioButton> cuisinesButton = new LinkedList<>();
    private List<MaterialRadioButton> tasteButton = new LinkedList<>();

    private boolean cuisineLoaded = false;
    private boolean tasteLoaded = false;

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
    MaterialRadioButton allRadio;
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

    private void loadCuisines() {
        //load cuisines
        if (!cuisineLoaded) {
            cuisineLoaded = true;
            cuisinePanel.clear();
            allRadio = getRadio("All");
            cuisinePanel.add(allRadio);
            allRadio.setChecked(true);
            allRadio.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    for (MaterialRadioButton r:cuisinesButton) {
                        r.setChecked(false);
                    }
                    populateCuisine(null);
                }
            });

            cuisinesService.getCuisines(new AsyncCallback<List<Cuisines>>() {
                @Override
                public void onFailure(Throwable throwable) {

                }

                @Override
                public void onSuccess(List<Cuisines> cuisines) {
                    for (Cuisines c : cuisines) {
                        final Cuisines cf = c;
                        final MaterialRadioButton opt = new MaterialRadioButton(c.getName());
                        opt.addClickHandler(new ClickHandler() {
                            @Override
                            public void onClick(ClickEvent clickEvent) {
                                allRadio.setChecked(false);
                                for (MaterialRadioButton r:cuisinesButton) {
                                    if (r != opt)
                                        r.setChecked(false);
                                }
                                populateCuisine(cf.getName());
                            }
                        });
                        opt.setText(c.getName());
                        cuisinePanel.add(opt);
                        cuisinesButton.add(opt);
                    }
                    cuisineLoaded = true;
                }
            });
        }
    }


    private void loadTastes() {
        //load cuisines
        if (!tasteLoaded) {
            tasteLoaded = true;
            tastePanel.clear();
            allRadioTaste = getRadio("All");
            tastePanel.add(allRadio);
            allRadioTaste.setChecked(true);
            allRadioTaste.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    for (MaterialRadioButton r:cuisinesButton) {
                        r.setChecked(false);
                    }
                    populateTaste(null);
                }
            });

            cuisinesService.getTastes(new AsyncCallback<List<Tastes>>() {
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
                                allRadio.setChecked(false);
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
                    cuisineLoaded = true;
                }
            });
        }
    }

    private void populateTaste(String flavor) {
        txtSearch.setText("");
        Tastes t = new Tastes();
        t.setFlavor(flavor);
        try {
            restaurantsService.getRestaurants(t, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populate() {

        loadCuisines();
        loadTastes();

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

    private void populateCuisine(String cuisine) {
        txtSearch.setText("");
        Cuisines c = new Cuisines();
        c.setName(cuisine);
        try {
            restaurantsService.getRestaurants(c, callback);
        } catch (Exception e) {
            e.printStackTrace();
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
