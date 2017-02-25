package com.aasys.sts.web.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by aasys on 11/3/15.
 */
public class QuestionPanel extends Composite{
    private static QuestionPanelUiBinder uiBinder = GWT.create(QuestionPanelUiBinder.class);

    interface QuestionPanelUiBinder extends UiBinder<Widget, QuestionPanel> {
    }

    public QuestionPanel() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
