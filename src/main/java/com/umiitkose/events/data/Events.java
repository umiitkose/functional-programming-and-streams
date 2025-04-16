package com.umiitkose.events.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.umiitkose.events.data.model.Event;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Events {
    private List<Event> eventList = new ArrayList<>();

    public List<Event> getEventList() {
        // JSON file path
        String filePath = "src/main/resources/tjc_events.json";

        try (FileReader reader = new FileReader(filePath)) {
            // Parse JSON file
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();

            // Get "data" array
            JsonArray events = root.getAsJsonArray("data");

            if (events != null) {
                for (int i = 0; i < events.size(); i++) {

                    JsonObject event = events.get(i).getAsJsonObject();

                    // Extract required fields
                    String eventName = event.get("name").getAsString();
                    String eventDate = event.get("start_date").getAsJsonObject().get("date").getAsString();
                    boolean isOnline = event.get("is_online").getAsBoolean();
                    int attendeesCount = event.has("users_count") && !event.get("users_count").isJsonNull()
                            ? event.get("users_count").getAsInt()
                            : 0;
                    String eventDescription = event.get("detail").getAsString();

                    eventList.add(new Event(eventName, eventDate, isOnline, attendeesCount, eventDescription));
                }
            } else {
                System.out.println("Etkinlik verisi bulunamadı.");
            }
        } catch (IOException e) {
            System.err.println("JSON dosyası okunurken bir hata oluştu: " + e.getMessage());
        }


        return eventList;
    }


}
