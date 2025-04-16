package com.umiitkose.streams.problem;

import com.umiitkose.events.data.Events;

import java.util.ArrayList;
import java.util.List;

public class SideEffect_1 {
    void main() {
        Events event = new Events();
        var data = event.getEventList();

         int count = 0;

        /*data.stream().filter(Event::isOnline).forEach((a) -> count += a.getAttendeesCount());*/

    }
}
