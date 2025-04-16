package com.umiitkose.events.example;

import com.umiitkose.events.data.Events;
import com.umiitkose.events.data.model.Event;

import java.util.function.Supplier;

public class Main {
    void main() {
        var events = new Events().getEventList();

        events.stream().filter(Event::isOnline)
                .forEach(e -> System.out.println(e.getEventName() + " - " + e.getEventDate()));

        setEventName(getEventName()); //dönen değeri alıyor.Metotu değil.

        // Kullanımı:
        setEventNameGenerator(() -> "Event Name");
        // veya
        setEventNameGenerator(this::getEventName);
    }

    String getEventName() {
        return "Event Name";
    }

    void setEventName(String eventName) {
        System.out.println("Event Name: " + eventName);
    }

    void setEventNameGenerator(Supplier<String> eventNameGenerator) {
        String eventName = eventNameGenerator.get();
        System.out.println("Event Name: " + eventName);
    }

}

