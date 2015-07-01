package com.wakwak.restclientexample.Event;

/**
 * Created by Ryo on 2015/06/28.
 */
public class ATNDEvent {
    public boolean success;
    public String  title;

    public ATNDEvent(boolean success, String title) {
        this.success = success;
        this.title = title;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
