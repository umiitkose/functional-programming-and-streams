package com.umiitkose.events.example;

import com.umiitkose.events.data.Events;
import com.umiitkose.events.model.Event;

public class Main {
    void main() {
        var events = new Events().getEventList();

        events.stream().filter(Event::isOnline)
                .forEach(e -> System.out.println(e.getEventName() + " - " + e.getEventDate()));
    }
}
