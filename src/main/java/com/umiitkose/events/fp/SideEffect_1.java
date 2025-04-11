package com.umiitkose.events.fp;

import com.umiitkose.events.data.Events;
import com.umiitkose.events.model.Event;

public class SideEffect_1 {
    void main() {
        Events event = new Events();
        var data = event.getEventList();

         int count = 0;

        /*data.stream().filter(Event::isOnline).forEach((a) -> count += a.getAttendeesCount());*/

    }
}
