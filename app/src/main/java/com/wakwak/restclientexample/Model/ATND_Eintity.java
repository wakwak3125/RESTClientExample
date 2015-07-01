package com.wakwak.restclientexample.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryo on 2015/06/30.
 */
public class ATND_Eintity {

    public List<Events> events;

    public class Events {
        public Events event;

        public int    event_id;
        public String title;

        public Events() {
        }

        public Events getEvent() {
            return event;
        }

        public void setEvent(Events event) {
            this.event = event;
        }

        public int getEvent_id() {
            return event_id;
        }

        public void setEvent_id(int event_id) {
            this.event_id = event_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


}
