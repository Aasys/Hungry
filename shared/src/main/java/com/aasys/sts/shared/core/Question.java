package com.aasys.sts.shared.core;

/**
 * Created by aasys on 11/3/15.
 */
public abstract class Question {
    private String _prompt;

    public void setPrompt(String prompt) {
        _prompt = prompt;
    }

    public String getPrompt() {
        return _prompt;
    }

}
