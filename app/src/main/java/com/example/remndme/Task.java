package com.example.remndme;

public class Task {
    private String title;
    private boolean checked;
    private int importance;
    private String time;
    private String date;

    public Task(String title, boolean checked, int importance, String time, String date) {
        this.title = title;
        this.checked = checked;
        this.importance = importance;
        this.time = time;
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
