package com.example.firebaselesson2.other;

public class Note {
    String timeCreate;
    String heading;
    int color;
    String text;

    public Note() {
    }

    public Note(String timeCreate, String heading, int color, String text) {
        this.timeCreate = timeCreate;
        this.heading = heading;
        this.color = color;
        this.text = text;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
