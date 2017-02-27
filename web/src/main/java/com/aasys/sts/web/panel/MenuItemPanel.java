package com.aasys.sts.web.panel;

import com.aasys.sts.shared.query.MenuInfo;
import com.aasys.sts.shared.query.PastOrdersInfo;
import com.aasys.sts.shared.util.StringUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialLink;

/**
 * Created by aasys on 2/25/2017.
 */
public class MenuItemPanel extends Composite {
    private static MenuItemPanelPanelUiBinder uiBinder = GWT.create(MenuItemPanelPanelUiBinder.class);

    interface MenuItemPanelPanelUiBinder extends UiBinder<Widget, MenuItemPanel> {
    }
    @UiField
    MaterialCard resCard;
    @UiField
    MaterialCheckBox chkOrder;

    private final MenuInfo menuInfo;

    public MenuItemPanel(MenuInfo _menuInfo) {
        menuInfo = _menuInfo;
        initWidget(uiBinder.createAndBindUi(this));
        resCard.setTitle(_menuInfo.getMenus().getName() + " - $" + String.valueOf(_menuInfo.getMenus().getPrice()));
        resCard.setDescription(_menuInfo.getMenus().getDescription());
    }

    public void toggleVisiblity(String flavor) {
        if (StringUtil.isEmptyOrNull(flavor))
            this.setVisible(true);
        else if (menuInfo.getFlavors() != null) {
            boolean hasFlavor = false;
            for (String f:menuInfo.getFlavors()) {
                if (f.equals(flavor)) {
                    hasFlavor = true;
                    break;
                }
            }
            this.setVisible(hasFlavor);
        } else
            this.setVisible(false);
    }

    public boolean isAddedToBad() {
        return chkOrder.isChecked();
    }

    public MenuInfo getMenuInfo() {
        return menuInfo;
    }
}

