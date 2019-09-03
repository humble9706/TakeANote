package com.toborehumble.takeanote;

public class Note {
    private String title;
    private String body;
    private String date;
    private String time;
    private String timeAgo;
    private String created;

    int h = 0;
    int m = 0;
    int s = 0;

    public Note(){}

    public Note(String title, String body, String date) {
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public String getTitle() {
        return title.substring(0, 1).toUpperCase() + title.substring(1);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        if (body.length() > 10){
            return (body.substring(0, 1).toUpperCase() + body.substring(1, 9)) + "...";
        }
        return (body.substring(0, 1).toUpperCase() + body.substring(1)) + "...";
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeAgo() {
        return this.date;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", timeAgo='" + timeAgo + '\'' +
                ", created='" + created + '\'' +
                ", h=" + h +
                ", m=" + m +
                ", s=" + s +
                '}';
    }
}
