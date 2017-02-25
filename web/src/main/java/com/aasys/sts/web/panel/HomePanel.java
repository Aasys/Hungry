package com.aasys.sts.web.panel;

import com.aasys.sts.shared.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialContainer;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTopNav;

public class HomePanel extends Composite {

    private static HomePanelUiBinder uiBinder = GWT.create(HomePanelUiBinder.class);

    interface HomePanelUiBinder extends UiBinder<Widget, HomePanel> {}


    private final User _user;

    @UiField
    MaterialTopNav UI_topNav;

    @UiField
    MaterialLink UI_lblEmail;

    @UiField
    MaterialContainer UI_canvas;

    public HomePanel(User user) {
        initWidget(uiBinder.createAndBindUi(this));
        _user = user;
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        UI_topNav.setProfileName(_user.getFullName());
        UI_lblEmail.setText(_user.getEmail());
        addItems();
    }

    public void setToCanvas(Widget widget) {
        UI_canvas.addWidget(widget);
    }

    private void addItems() {
        MaterialContainer materialContainer = new MaterialContainer();
        for (int i =0; i <=10; i++) {
            MaterialRow materialRow = new MaterialRow();
            QuestionPanel questionPanel = new QuestionPanel();
            materialRow.add(questionPanel);
            materialContainer.add(materialRow);

        }
        setToCanvas(materialContainer);
    }
}
