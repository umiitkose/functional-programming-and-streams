package com.umiitkose.events.model;

public class Event {

    private String eventName;
    private String eventDate;
    private boolean isOnline;
    private int attendeesCount;
    private String eventDescription;

    public Event(String eventName, String eventDate, boolean isOnline, int attendeesCount, String eventDescription) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.isOnline = isOnline;
        this.attendeesCount = attendeesCount;
        this.eventDescription = eventDescription;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public int getAttendeesCount() {
        return attendeesCount;
    }

    public void setAttendeesCount(int attendeesCount) {
        this.attendeesCount = attendeesCount;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", isOnline=" + isOnline +
                ", attendeesCount=" + attendeesCount +
                ", eventDescription='" + eventDescription + '\'' +
                '}';
    }
}
