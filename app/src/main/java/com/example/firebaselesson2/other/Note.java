package com.example.firebaselesson2.other;

public class Note {
    String id;
    String heading;
    int color;
    String text;

    public Note() {
    }

    public Note(String id, String heading, int color, String text) {
        this.id = id;
        this.heading = heading;
        this.color = color;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
