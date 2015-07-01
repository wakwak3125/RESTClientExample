package com.wakwak.restclientexample.Model;

/**
 * Created by Ryo on 2015/07/02.
 */
public class Event {

    public String title;
    public int    event_id;

    public Event() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }
}
